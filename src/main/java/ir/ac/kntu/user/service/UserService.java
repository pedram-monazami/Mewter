package ir.ac.kntu.user.service;

import ir.ac.kntu.user.Post;
import ir.ac.kntu.user.User;

public interface UserService {

    boolean addUser(User user) throws Exception;

    User getUser(String username) throws Exception;

    boolean followUser(User toBeFollowedUser, User wantToFollowUser) throws Exception;

    boolean unfollowUser(User toBeUnfollowedUser, User wantToUnfollowUser) throws Exception;

    boolean likePost(Post post, User liker) throws Exception;

    boolean makePost(String text, User user) throws Exception;

    boolean commentOnPost(Post post, String comment) throws Exception;

    boolean deletePost(Post post, User user) throws Exception;

    void updatePost(String newText, Post post) throws Exception;

    void removeCommentOnPost(Post post, int commentIndex) throws Exception;

    Post getPost(long postId) throws Exception;

    User login(String username, String password) throws Exception;

    boolean isUsernameDuplicated(String username) throws Exception;

}
