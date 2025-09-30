import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles a single client connection. Acts as:
 *  - Adapter: adapts Socket Input/Output to MessageSender/receiver
 *  - Observer: receives Message objects via notifyMessage()
 *
 * Protocol (client -> server):
 *  JOIN <roomId> <username>      -> join or create room
 *  MSG <text>                    -> broadcast to room
 *  PM <toUsername> <text>        -> private message
 *  USERS                         -> request active users list
 *  HISTORY                       -> request history
 *  QUIT                          -> disconnect
 *
 * Short commands supported:
 *  /jn <roomId> <username>       -> join or create room
 *  /pm <toUsername> <text>       -> private message
 *  /u                            -> request active users list
 *  /h                            -> request public history
 *  /pmh <user>                   -> request private message history with <user>
 *  /q                            -> disconnect
 *
 * Server -> client messages:
 *  text lines (plain). System lines prefixed with [SYSTEM], user list lines prefixed with [USERS]
 */
public class ClientHandler implements Runnable {
    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final ChatRoomManager mgr = ChatRoomManager.getInstance();

    private volatile String username;
    private volatile ChatRoom room;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
    }

    public String getUsername() { return username; }

    public MessageSender getSender() {
        return new MessageSender() {
            @Override
            public void send(String text) throws IOException {
                synchronized (out) {
                    out.write(text);
                    out.write("\n");
                    out.flush();
                }
            }
            @Override public void close() throws IOException { out.close(); }
        };
    }

    @Override
    public void run() {
        try {
            logger.info("Client connected: " + socket.getRemoteSocketAddress());
            getSender().send("WELCOME SimpleChatServer.Please /jn <roomId> <username>");
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                handleCommand(line);
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException in client handler", e);
        } finally {
            try { closeAndCleanup(); } catch (IOException ignored) {}
            logger.info("Client disconnected: " + socket.getRemoteSocketAddress());
        }
    }

    private void handleCommand(String line) throws IOException {
        String[] parts = line.split(" ", 3);
        String cmd = parts[0].toUpperCase();

        // ADDED: short command mapping
        if (cmd.equalsIgnoreCase("/JN")) cmd = "JOIN";
        else if (cmd.equalsIgnoreCase("/PM")) cmd = "PM";
        else if (cmd.equalsIgnoreCase("/U")) cmd = "USERS";
        else if (cmd.equalsIgnoreCase("/H")) cmd = "HISTORY";
        else if (cmd.equalsIgnoreCase("/PMH")) cmd = "PMH";
        else if (cmd.equalsIgnoreCase("/Q")) cmd = "QUIT";

        switch (cmd) {
            case "JOIN":
                if (parts.length < 3) {
                    getSender().send("ERROR Usage: JOIN <roomId> <username>");
                    return;
                }
                handleJoin(parts[1], parts[2]);
                break;
            case "MSG":
                if (room == null) {
                    getSender().send("ERROR join a room first");
                    return;
                }
                String msgText = parts.length >= 2 ? line.substring(4) : "";
                room.broadcast(new Message(username, null, msgText, false));
                break;
            case "PM":
                if (room == null) {
                    getSender().send("ERROR join a room first");
                    return;
                }
                if (parts.length < 3) {
                    getSender().send("ERROR Usage: PM <toUsername> <text>");
                    return;
                }
                String toUser = parts[1];
                String pmText = parts[2];
                room.sendPrivate(username, toUser, pmText);
                break;
            case "USERS":
                if (room == null) {
                    getSender().send("ERROR join a room first");
                    return;
                }
                List<String> active = room.getActiveUsers();
                getSender().send("[USERS] " + String.join(",", active));
                break;
            case "HISTORY":
                if (room == null) {
                    getSender().send("ERROR join a room first");
                    return;
                }
                for (Message m : room.getHistory()) {
                    getSender().send(m.toDisplayString());
                }
                break;
            case "PMH":
                if (room == null) {
                    getSender().send("ERROR join a room first");
                    return;
                }
                if (parts.length < 2) {
                    getSender().send("ERROR Usage: /pmh <username>");
                    return;
                }
                String otherUser = parts[1];
                List<Message> pmHistory = room.getPrivateHistory(username, otherUser);
                if (pmHistory.isEmpty()) {
                    getSender().send("[SYSTEM] No private history with " + otherUser);
                } else {
                    getSender().send("=== Private History with " + otherUser + " start ===");
                    for (Message m : pmHistory) {
                        getSender().send(m.toDisplayString());
                    }
                    getSender().send("=== Private History end ===");
                }
                break;
            case "QUIT":
                closeAndCleanup();
                break;
            default:
                getSender().send("ERROR Unknown command: " + cmd);
        }
    }

    private void handleJoin(String roomId, String username) throws IOException {
        if (this.room != null && this.username != null) {
            this.room.removeUser(this.username);
        }
        ChatRoom chatRoom = mgr.getOrCreateRoom(roomId);
        boolean ok = chatRoom.addUser(username, this);
        if (!ok) {
            getSender().send("ERROR username already in use in room");
            return;
        }
        this.room = chatRoom;
        this.username = username;
        logger.info("User '" + username + "' joined room: " + roomId);
        getSender().send("JOINED " + roomId);
        getSender().send("=== History start ===");
        for (Message m : chatRoom.getHistory()) {
            getSender().send(m.toDisplayString());
        }
        getSender().send("=== History end ===");
    }

    public void notifyMessage(Message msg) {
        try {
            if (msg.isPrivate()) {
                if (msg.getTo().equals(username) || msg.getFrom().equals(username)) {
                    getSender().send(msg.toDisplayString());
                }
            } else {
                getSender().send(msg.toDisplayString());
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to notify " + username, e);
        }
    }

    private void closeAndCleanup() throws IOException {
        if (username != null && room != null) {
            room.removeUser(username);
            username = null;
        }
        try { socket.close(); } catch (Exception ignored) {}
    }
}
