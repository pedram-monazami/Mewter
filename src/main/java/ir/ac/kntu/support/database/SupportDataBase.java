package ir.ac.kntu.support.database;

import ir.ac.kntu.support.SupportUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SupportDataBase {

    private static final List<SupportUser> userList = new ArrayList<>();

    public void addUser(SupportUser user) throws Exception {
        if (!checkIfUserExists(user)) {
            userList.add(user);
        } else {
            throw new Exception("User already exists!");
        }
    }

    // finds user in database based on username
    public SupportUser getUser(String username) throws Exception {
        for (SupportUser user : userList) {
            if (Objects.equals(username, user.getUsername())) {
                return user;
            }
        }
        throw new Exception("no user with this username exists in database");
    }

    private boolean checkIfUserExists(SupportUser user) {
        // loop over all users and check if a user with this username already exists
        for (SupportUser u : userList) {
            if (Objects.equals(u.getUsername(), user.getUsername())) {
                return true;
            }
        }
        // if no user has this username, it does not exist
        return false;
    }

    public List<SupportUser> getUserList() {
        return userList;
    }
}
