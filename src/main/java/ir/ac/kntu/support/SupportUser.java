package ir.ac.kntu.support;

import java.util.ArrayList;
import java.util.List;

public class SupportUser {
    private static final List<SupportUser> SUPPORT_USERS = new ArrayList<>();
    private static final List<SupportTickets> SUPPORT_TICKETS = new ArrayList<>();
    private String username;
    private String password;
    private String name;
    private String lastname;
    private Access access;

    public SupportUser() {
        SUPPORT_USERS.add(this);
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

    public static List<SupportUser> getSupportUsers() {
        return SUPPORT_USERS;
    }

    public static List<SupportTickets> getSupportTickets() {
        return SUPPORT_TICKETS;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }
}
