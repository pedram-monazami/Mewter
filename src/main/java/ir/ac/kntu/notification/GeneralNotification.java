package ir.ac.kntu.notification;

import ir.ac.kntu.user.User;
import ir.ac.kntu.userinterface.PagePrinter;

import java.util.HashSet;
import java.util.Set;

public class GeneralNotification extends Notification {
    private String message;
    private Set<String> readByUsers = new HashSet<>();
    private boolean isActive = true;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getReadByUsers() {
        return readByUsers;
    }

    public void addUserToRead(String username) {
        readByUsers.add(username);
    }

    @Override
    public String getNotificationText() {
        return "General notification: " + message.substring(0, Math.min(30, message.length())) + "...";
    }

    @Override
    public void handleSelection(PagePrinter printer, User user) {
        addUserToRead(user.getUsername());
        markAsRead();
        System.out.println("Notification: " + message);
        System.out.println("\nRedirecting to notifications panel...");
        printer.notifications(user);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
