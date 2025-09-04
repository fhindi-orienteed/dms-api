package com.dms.base.dto.request;

import jakarta.validation.constraints.Email;

public class LoginRequest {

    @Email(message = "Username must be a valid email")
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
