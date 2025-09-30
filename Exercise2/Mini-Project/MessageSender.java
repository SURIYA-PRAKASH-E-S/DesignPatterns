import java.io.IOException;

public interface MessageSender {
    void send(String text) throws IOException;
    void close() throws IOException;
}
