package ir.ac.kntu.notification;

import ir.ac.kntu.user.Post;
import ir.ac.kntu.user.User;
import ir.ac.kntu.userinterface.PagePrinter;

public class CommentNotification extends Notification {
    private String username;
    private String commentText;
    private Post post;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String getNotificationText() {
        return username + " commented on your post: \"" + commentText + "\"";
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
