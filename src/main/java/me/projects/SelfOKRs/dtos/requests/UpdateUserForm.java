package me.projects.SelfOKRs.dtos.requests;

import me.projects.SelfOKRs.entities.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UpdateUserForm {

    @NotNull
    @Size(
            min = 3,
            max = 255,
            message = "Name must be longer than 2 characters"
    )
    private String username;

    @Email(message = "Not a valid email address")
    private String emailAddress;

    @NotNull
    @Size(
            min = 8,
            max = 255,
            message = "Password must be at least 8 characters long"
    )
    private String password;

    @NotNull
    private String oldPassword;

    private final PasswordEncoder passwordEncoder;

    public UpdateUserForm(String username, String emailAddress, String password, String oldPassword) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.oldPassword = oldPassword;
        this.passwordEncoder = new BCryptPasswordEncoder();
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public UserEntity toUser() {
        return new UserEntity(username, emailAddress, passwordEncoder.encode(password));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateUserForm that = (UpdateUserForm) o;
        return Objects.equals(username, that.username) && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(password, that.password) && Objects.equals(oldPassword, that.oldPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, emailAddress, password, oldPassword);
    }
}
