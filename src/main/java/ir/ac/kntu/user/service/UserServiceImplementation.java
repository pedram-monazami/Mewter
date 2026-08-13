package ir.ac.kntu.user.service;

import ir.ac.kntu.user.Post;
import ir.ac.kntu.user.Profile;
import ir.ac.kntu.user.User;
import ir.ac.kntu.user.database.UserDataBase;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImplementation implements UserService {

    private final UserDataBase dataBase = new UserDataBase();

    @Override
    public boolean addUser(User user) throws Exception {
        if (isUserValid(user)) {
            try {
                dataBase.addUser(user);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            throw new IllegalArgumentException("Invalid user");
        }
    }

    public User getUser(String username) throws Exception {
        return dataBase.getUser(username);
    }


    @Override
    public boolean followUser(User toBeFollowedUser, User wantToFollowUser) {
        wantToFollowUser.getProfile().getFollowing().add(toBeFollowedUser);
        return toBeFollowedUser.getProfile().getFollowers().add(wantToFollowUser);
    }

    @Override
    public boolean unfollowUser(User toBeUnfollowedUser, User wantToUnfollowUser) {
        wantToUnfollowUser.getProfile().getFollowing().remove(toBeUnfollowedUser);
        return toBeUnfollowedUser.getProfile().getFollowers().remove(wantToUnfollowUser);
    }

    @Override
    public boolean likePost(Post post, User liker){
        return post.getLikers().add(liker);
    }

    @Override
    public boolean makePost(String text, User user) {
        Post post = new Post();
        post.setText(text);
        return user.getProfile().getPosts().add(post);
    }

    @Override
    public boolean deletePost(Post post, User user) {
        return user.getProfile().getPosts().remove(post);
    }

    @Override
    public void updatePost(String newText, Post post)  {
        post.setText(newText);
    }

    @Override
    public boolean commentOnPost(Post post, String comment) {
        return post.getComments().add(comment);
    }

    @Override
    public void removeCommentOnPost(Post post, int commentIndex) {
        post.getComments().remove(commentIndex);
    }

    @Override
    public Post getPost(long postId) throws Exception {
        for (User u : dataBase.getUserList()) {
            Profile profile = u.getProfile();
            List<Post> posts = profile.getPosts();
            for (Post p : posts) {
                if (p.getId() == postId) {
                    return p;
                }
            }
        }
        throw new Exception("no such post found");
    }

    @Override
    public User login(String username, String password) throws Exception {
        User currentUser = getUser(username);
        if (currentUser.getPassword().equals(password)) {
            return currentUser;
        }
        throw new Exception("wrong password");
    }

    @Override
    public boolean isUsernameDuplicated(String username) throws Exception {
        for (User u : dataBase.getUserList()) {
            if (u.getUsername().equals(username)) return true;
        }
        return false;
    }

    public boolean isUserValid(User user) throws Exception {
        return isEmailValid(user.getEmail()) && isPasswordValid(user.getPassword());
    }

    public boolean isEmailValid(String email) throws Exception {
        if (email == null) return false;
        Pattern pattern = Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) return true;
        throw new Exception("email format is not valid!");
    }

    public boolean isPasswordValid(String password) throws Exception {
        if (password == null) return false;
        Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) return true;
        throw new Exception("password needs to be atleast 8 characters, have a special character and a number and capital and a small letter");
    }


}
