package me.projects.SelfOKRs.dtos;

import me.projects.SelfOKRs.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

public class RegistrationForm {

    private String username;
    private String emailAddress;
    private String password;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegistrationForm() {}

    @Autowired
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

    public UserEntity toUser() {
        return new UserEntity(username, emailAddress, passwordEncoder.encode(password));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof RegistrationForm || o instanceof UserEntity)) return false;
        RegistrationForm that = (RegistrationForm) o;
        return Objects.equals(username, that.username) && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, emailAddress, password);
    }
}
