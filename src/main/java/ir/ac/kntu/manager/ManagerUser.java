package ir.ac.kntu.manager;

import java.util.ArrayList;
import java.util.List;

public class ManagerUser {
    private static final List<ManagerUser> MANAGER_USERS = new ArrayList<>();
    private String username;
    private String password;
    private String name;
    private String lastname;
    private Level level;

    public ManagerUser() {
        MANAGER_USERS.add(this);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<ManagerUser> getManagerUsers() {
        return MANAGER_USERS;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }
}
