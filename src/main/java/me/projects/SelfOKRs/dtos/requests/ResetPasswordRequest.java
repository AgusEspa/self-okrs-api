package me.projects.SelfOKRs.dtos.requests;

import javax.validation.constraints.Size;

public class ResetPasswordRequest {

    @Size(
            min = 8,
            max = 255,
            message = "Password must be at least 8 characters long"
    )
    private String newPassword;

    public ResetPasswordRequest() {
    }

    public ResetPasswordRequest(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
