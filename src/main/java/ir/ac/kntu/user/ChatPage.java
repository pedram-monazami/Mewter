package ir.ac.kntu.user;

import java.util.ArrayList;
import java.util.List;

public class ChatPage {
    private final List<User> users = new ArrayList<>();
    private final List<Message> messages = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void addUsers(User user1, User user2) {
        this.users.add(user1);
        this.users.add(user2);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public String getParticipants() {
        if (!users.isEmpty()) {
            return users.get(0).getUsername() + " and " + users.get(1).getUsername();
        }
        return "";
    }

    public void printMessages() {
        messages.forEach(message -> System.out.println(message.printable()));
    }
}
