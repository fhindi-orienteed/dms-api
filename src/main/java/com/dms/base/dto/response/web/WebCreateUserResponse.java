package com.dms.base.dto.response.web;

import com.dms.base.model.Driver;
import com.dms.base.model.User;

public class WebCreateUserResponse {
    private User user;
    private Driver driver;
    private String message;

    public WebCreateUserResponse() { 
    
    }

    public void setUser(User user){
        this.user = user; 
    }

    public User getUser(){
        return user;
    }

    public void setDriver(Driver driver){
        this.driver = driver; 
    }

    public Driver getDriver(){
        return driver;
    }

    public void setMessage(String message){
        this.message = message; 
    }

    public String getMessage(){
        return message;
    }
    
    
}
