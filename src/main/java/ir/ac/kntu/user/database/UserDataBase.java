package ir.ac.kntu.user.database;

import ir.ac.kntu.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDataBase {

    private static final List<User> userList = new ArrayList<>();

    public void addUser(User user) throws Exception {
        if (!checkIfUserExists(user)) {
            userList.add(user);
        } else {
            throw new Exception("User already exists!");
        }
    }

    // finds user in database based on username
    public User getUser(String username) throws Exception {
        for (User user : userList) {
            if (Objects.equals(username, user.getUsername())) {
                return user;
            }
        }
        throw new Exception("no user with this username exists in database");
    }

    private boolean checkIfUserExists(User user) {
        // loop over all users and check if a user with this username already exists
        for (User u : userList) {
            if (Objects.equals(u.getUsername(), user.getUsername())) {
                return true;
            }
        }
        // if no user has this username, it does not exist
        return false;
    }

    public List<User> getUserList() {
        return userList;
    }
}
