package ir.ac.kntu.user;

import ir.ac.kntu.notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private List<User> followers = new ArrayList<>();
    private List<User> following = new ArrayList<>();
    private List<ChatPage> chatPages = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();
    private String birthday;
    private String bio;
    private boolean isPrivate;
    private List<User> followRequests = new ArrayList<>();

    public void addChatPage(ChatPage chatPage) {
        chatPages.add(chatPage);
    }

    public List<ChatPage> getChatPages() {
        return chatPages;
    }

    public void setChatPages(List<ChatPage> chatPages) {
        this.chatPages = chatPages;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public List<User> getFollowRequests() {
        return followRequests;
    }

    public void setFollowRequests(List<User> followRequests) {
        this.followRequests = followRequests;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }
}
