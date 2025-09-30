import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Singleton manager for chat rooms.
 */
public class ChatRoomManager {
    private static final Logger logger = Logger.getLogger(ChatRoomManager.class.getName());

    // Eager initialization ensures thread safety without explicit synchronization
    private static final ChatRoomManager instance = new ChatRoomManager();

    private final Map<String, ChatRoom> rooms = new ConcurrentHashMap<>();

    private ChatRoomManager() { }

    public static ChatRoomManager getInstance() {
        return instance;
    }

    public ChatRoom getOrCreateRoom(String roomId) {
        ChatRoom room = rooms.computeIfAbsent(roomId, ChatRoom::new);
        logger.fine("Accessed/created ChatRoom: " + roomId);
        return room;
    }

    public ChatRoom getRoom(String roomId) {
        return rooms.get(roomId);
    }
}
