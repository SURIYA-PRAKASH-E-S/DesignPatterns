import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Console chat client.
 * Usage:
 *   java ChatClient <host> <port>
 * Example:
 *   java ChatClient localhost 9090
 *
 * After connecting, use commands:
 *   JOIN <roomId> <username>
 *   MSG <text>
 *   PM <toUsername> <text>
 *   USERS
 *   HISTORY
 *   QUIT
 *
 * For convenience the client supports short actions:
 *   /jn <roomId> <username>
 *   /msg <text>
 *   /pm <to> <text>
 *   /u
 *   /h
 *   /pmh <user>
 *   /q
 */
public class ChatClient {
    private static final Logger logger = Logger.getLogger(ChatClient.class.getName());

    public static void main(String[] args) {
        String host = "localhost";
        int port = 9090;
        if (args.length >= 1) host = args[0];
        if (args.length >= 2) {
            try { port = Integer.parseInt(args[1]); } catch (NumberFormatException ignored) {}
        }

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
             Scanner sc = new Scanner(System.in)) {

            // thread to read server messages
            Thread reader = new Thread(() -> {
                try {
                    String s;
                    while ((s = in.readLine()) != null) {
                        System.out.println(s);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                    logger.log(Level.INFO, "Server connection closed", e);
                }
            });
            reader.setDaemon(true);
            reader.start();

            System.out.println("Connected to chat server " + host + ":" + port);
            System.out.println("Type commands: /jn /msg /pm /u /h /pmh /q");

            while (true) {
                String line;
                try {
                    if (!sc.hasNextLine()) break;
                    line = sc.nextLine();
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Input error", e);
                    break;
                }

                if (line == null) break;
                line = line.trim();
                if (line.isEmpty()) continue;

                String outLine = null;
                if (line.startsWith("/jn ")) {
                    outLine = "JOIN " + line.substring(4).trim();
                } else if (line.startsWith("/msg ")) {
                    outLine = "MSG " + line.substring(5).trim();
                } else if (line.startsWith("/pm ")) {
                    String rest = line.substring(4).trim();
                    int firstSpace = rest.indexOf(' ');
                    if (firstSpace <= 0) {
                        System.out.println("Usage: /pm <user> <message>");
                        continue;
                    }
                    String to = rest.substring(0, firstSpace);
                    String msg = rest.substring(firstSpace + 1);
                    outLine = "PM " + to + " " + msg;
                } else if (line.equals("/u")) {
                    outLine = "USERS";
                } else if (line.equals("/h")) {
                    outLine = "HISTORY";
                } else if (line.startsWith("/pmh ")) {
                    String target = line.length() > 5 ? line.substring(5).trim() : "";
                    if (target.isEmpty()) {
                        System.out.println("Usage: /pmh  <user>");
                        continue;
                    }
                    outLine = "PMH " + target;
                } else if (line.equals("/q")) {
                    outLine = "QUIT";
                    writeLine(out, outLine);
                    break;
                } else {
                    outLine = "MSG " + line;
                }
                writeLine(out, outLine);
            }

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Failed to connect to server", ex);
            System.err.println("Failed to connect to server: " + ex.getMessage());
        }
    }

    private static void writeLine(BufferedWriter out, String text) throws IOException {
        synchronized (out) {
            out.write(text);
            out.write("\n");
            out.flush();
        }
    }
}
