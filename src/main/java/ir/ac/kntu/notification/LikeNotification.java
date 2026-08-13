package ir.ac.kntu.notification;

import ir.ac.kntu.user.Post;
import ir.ac.kntu.user.User;
import ir.ac.kntu.userinterface.PagePrinter;

public class LikeNotification extends Notification {
    private String username;
    private Post post;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String getNotificationText() {
        return username + " liked your post.";
    }

    @Override
    public void handleSelection(PagePrinter printer, User user) {
        this.markAsRead();
        System.out.println("Selected post:");
        System.out.println(post.getText());
        System.out.println("number of likes: " + post.getLikers().size());
        printer.editOrDeletePost(post, user);
    }
}
