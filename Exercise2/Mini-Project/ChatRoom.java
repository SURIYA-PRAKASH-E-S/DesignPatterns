import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ChatRoom maintains observers (clients) and message history.
 * Uses thread-safe observer list (CopyOnWriteArrayList) so iteration-safe.
 */
public class ChatRoom {
    private static final Logger logger = Logger.getLogger(ChatRoom.class.getName());

    private final String roomId;
    private final Map<String, ClientHandler> users = Collections.synchronizedMap(new LinkedHashMap<>());
    private final List<Message> history = Collections.synchronizedList(new ArrayList<>());
    private final Map<String, List<Message>> privateHistories = Collections.synchronizedMap(new HashMap<>());

    public ChatRoom(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() { return roomId; }

    public synchronized boolean addUser(String username, ClientHandler handler) {
        if (users.containsKey(username)) return false;
        users.put(username, handler);
        broadcastSystem(String.format("%s joined the room", username));
        sendActiveUsersList();
        logger.info("User '" + username + "' joined ChatRoom " + roomId);
        return true;
    }

    public synchronized void removeUser(String username) {
        if (users.remove(username) != null) {
            broadcastSystem(String.format("%s left the room", username));
            sendActiveUsersList();
            logger.info("User '" + username + "' left ChatRoom " + roomId);
        }
    }

    public synchronized List<String> getActiveUsers() {
        return new ArrayList<>(users.keySet());
    }

    public synchronized void broadcast(Message msg) {
        history.add(msg);
        for (ClientHandler handler : users.values()) {
            handler.notifyMessage(msg);
        }
    }

    public synchronized void broadcastSystem(String text) {
        Message msg = new Message("SYSTEM", null, text, false);
        history.add(msg);
        for (ClientHandler handler : users.values()) {
            try {
                handler.getSender().send("[SYSTEM] " + text);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Failed to send system message to " + handler.getUsername(), e);
            }
        }
    }

    public synchronized void sendPrivate(String from, String to, String text) {
        Message msg = new Message(from, to, text, true);
        String key = getPrivateKey(from, to);
        privateHistories.computeIfAbsent(key, k -> Collections.synchronizedList(new ArrayList<>())).add(msg);

        ClientHandler target = users.get(to);
        ClientHandler sender = users.get(from);

        if (target != null) {
            try {
                target.notifyMessage(msg);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Failed private deliver to " + to, e);
            }
        }
        if (sender != null && sender != target) {
            try {
                sender.notifyMessage(msg);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Failed private echo to " + from, e);
            }
        }
    }

    private String getPrivateKey(String user1, String user2) {
        return (user1.compareTo(user2) < 0) ? user1 + ":" + user2 : user2 + ":" + user1;
    }

    public synchronized List<Message> getPrivateHistory(String user1, String user2) {
        String key = getPrivateKey(user1, user2);
        return new ArrayList<>(privateHistories.getOrDefault(key, Collections.emptyList()));
    }

    public synchronized List<Message> getHistory() {
        return new ArrayList<>(history);
    }

    private void sendActiveUsersList() {
        String list = String.join(",", getActiveUsers());
        for (ClientHandler handler : users.values()) {
            try {
                handler.getSender().send("[USERS] " + list);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Failed to send users list to " + handler.getUsername(), e);
            }
        }
    }
}
