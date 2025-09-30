import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Message represents a chat message (public or private).
 * Immutable design ensures thread-safety.
 */
public final class Message {
    private final String from;
    private final String to; // null for room messages, username for private
    private final String text;
    private final LocalDateTime ts;
    private final boolean isPrivate;

    public Message(String from, String to, String text, boolean isPrivate) {
        if (from == null || from.trim().isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be null or empty");
        }
        if (text == null) {
            throw new IllegalArgumentException("Message text cannot be null");
        }
        this.from = from;
        this.to = to;
        this.text = text;
        this.isPrivate = isPrivate;
        this.ts = LocalDateTime.now();
    }

    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getText() { return text; }
    public boolean isPrivate() { return isPrivate; }
    public String timestamp() {
        return ts.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String toDisplayString() {
        if (isPrivate) {
            return String.format("[%s] (private) %s -> %s: %s", timestamp(), from, to, text);
        } else {
            return String.format("[%s] %s: %s", timestamp(), from, text);
        }
    }
}
