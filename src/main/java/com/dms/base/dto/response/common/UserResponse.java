package com.dms.base.dto.response.common;

import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private LocalDateTime createdDate;
    private String userName;
    private Integer status;
    private String role;
    private LocalDateTime lastSession;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getLastSession() {
        return lastSession;
    }

    public void setLastSession(LocalDateTime lastSession) {
        this.lastSession = lastSession;
    }

}
