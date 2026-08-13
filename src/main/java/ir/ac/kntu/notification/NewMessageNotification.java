package ir.ac.kntu.notification;

import ir.ac.kntu.user.ChatPage;
import ir.ac.kntu.user.User;
import ir.ac.kntu.userinterface.PagePrinter;

public class NewMessageNotification extends Notification {
    private String senderUsername;
    private ChatPage chatPage;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public ChatPage getChatPage() {
        return chatPage;
    }

    public void setChatPage(ChatPage chatPage) {
        this.chatPage = chatPage;
    }

    @Override
    public String getNotificationText() {
        return "New message from " + senderUsername;
    }

    @Override
    public void handleSelection(PagePrinter printer, User user) {
        this.markAsRead();
        System.out.println("Selected chat:");
        printer.chatPageViewer(user, this.chatPage);
    }
}
