package me.projects.SelfOKRs.dtos;

public class UserDetails {

    private String username;

    private String emailAddress;

    public UserDetails(String username, String emailAddress) {
        this.username = username;
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
