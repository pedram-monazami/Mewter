package ir.ac.kntu.user;

import java.time.LocalDateTime;

public class Message {
    private final String senderUsername;
    private final String content;
    private final LocalDateTime timestamp;

    public Message(String senderUsername, String content, LocalDateTime timestamp) {
        this.senderUsername = senderUsername;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String printable() {
        return String.format("[%s] %s: %s", timestamp, senderUsername, content);
    }
}
