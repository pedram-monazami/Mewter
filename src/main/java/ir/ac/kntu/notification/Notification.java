package ir.ac.kntu.notification;

import ir.ac.kntu.user.User;
import ir.ac.kntu.userinterface.PagePrinter;

import java.time.LocalDateTime;

public abstract class Notification {
    private final LocalDateTime timestamp;
    private boolean isRead;

    public Notification() {
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public abstract String getNotificationText();

    public abstract void handleSelection(PagePrinter printer, User user);
}
