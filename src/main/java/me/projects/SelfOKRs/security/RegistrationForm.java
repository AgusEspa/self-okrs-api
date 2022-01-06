package me.projects.SelfOKRs.security;

import me.projects.SelfOKRs.entities.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationForm {

    private String username;
    private String emailAddress;
    private String password;

    public RegistrationForm() {}

    public RegistrationForm(String username, String emailAddress, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        return new UserEntity(username, emailAddress, passwordEncoder.encode(password));
    }
}
