package ir.ac.kntu.manager.database;

import ir.ac.kntu.manager.ManagerUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagerDataBase {

    private static final List<ManagerUser> userList = new ArrayList<>();

    public void addUser(ManagerUser user) throws Exception {
        if (!checkIfUserExists(user)) {
            userList.add(user);
        } else {
            throw new Exception("User already exists!");
        }
    }

    // finds user in database based on username
    public ManagerUser getUser(String username) throws Exception {
        for (ManagerUser user : userList) {
            if (Objects.equals(username, user.getUsername())) {
                return user;
            }
        }
        throw new Exception("no user with this username exists in database");
    }

    private boolean checkIfUserExists(ManagerUser user) {
        // loop over all users and check if a user with this username already exists
        for (ManagerUser u : userList) {
            if (Objects.equals(u.getUsername(), user.getUsername())) {
                return true;
            }
        }
        // if no user has this username, it does not exist
        return false;
    }

    public List<ManagerUser> getUserList() {
        return userList;
    }
}
