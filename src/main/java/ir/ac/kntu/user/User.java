package ir.ac.kntu.user;

import ir.ac.kntu.support.SupportTickets;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;
    private Profile profile = new Profile();
    private List<SupportTickets> tickets = new ArrayList<>();

    public User(String username, String password, String email, String name, String lastname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<SupportTickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<SupportTickets> tickets) {
        this.tickets = tickets;
    }
}
