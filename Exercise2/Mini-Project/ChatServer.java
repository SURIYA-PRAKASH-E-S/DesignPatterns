import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ChatServer: listens for TCP client connections and spawns ClientHandler threads.
 *
 * Simple usage:
 *   java ChatServer 9090
 * If no port provided, default 9090 used.
 */
public class ChatServer {
    private final int port;
    private final ExecutorService pool = Executors.newCachedThreadPool();

    // ADDED: centralized logger
    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("ChatServer started on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                try {
                    ClientHandler handler = new ClientHandler(clientSocket);
                    pool.submit(handler);
                    logger.info("Accepted new client connection: " + clientSocket.getRemoteSocketAddress());
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Failed to create handler for client", e);
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server socket failure on port " + port, e);
            throw e; // rethrow so main() can handle
        } finally {
            shutdown();
        }
    }

    private void shutdown() {
        logger.info("Shutting down server thread pool...");
        pool.shutdown();
        try {
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                logger.warning("Forcing shutdown of client handler threads");
                pool.shutdownNow();
            }
        } catch (InterruptedException ignored) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        int port = 9090;
        if (args.length >= 1) {
            try { port = Integer.parseInt(args[0]); } catch (NumberFormatException ignored) {}
        }
        ChatServer server = new ChatServer(port);
        try {
            server.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server failure: " + e.getMessage(), e);
        }
    }
}
