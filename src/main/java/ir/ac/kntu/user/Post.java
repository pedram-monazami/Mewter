package ir.ac.kntu.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    public static long POST_ID = 0;
    private long id;
    private String text;
    private List<User> likers = new ArrayList<>();
    private List<String> comments = new ArrayList<>();
    private LocalDateTime postDate = LocalDateTime.now();

    public Post() {
        this.id = POST_ID;
        POST_ID++;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<User> getLikers() {
        return likers;
    }

    public void setLikers(List<User> likers) {
        this.likers = likers;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
