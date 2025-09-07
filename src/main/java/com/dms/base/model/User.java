package com.dms.base.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdDate;
    private String userName;
    private String password;
    private Integer status;
    private String role;
    private Integer passwordRetries;
    private Integer passwordExpired;
    private Integer passwordCreation;
    private Integer timeout;

    private Date lastSession;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getLastSession() {
        return lastSession;
    }

    public void setLastSession(Date lastSession) {
        this.lastSession = lastSession;
    }

    public Integer getPasswordRetries() {
        return passwordRetries;
    }

    public void setPasswordRetries(Integer passwordRetries) {
        this.passwordRetries = passwordRetries;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(Integer passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    public Integer getPasswordCreation() {
        return passwordCreation;
    }

    public void setPasswordCreation(Integer passwordCreation) {
        this.passwordCreation = passwordCreation;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

}
