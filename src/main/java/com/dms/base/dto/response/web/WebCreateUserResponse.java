// WebCreateUserResponse.java
package com.dms.base.dto.response.web;

public class WebCreateUserResponse {
    private WebUserResponse user;
    private WebDriverResponse driver;
    private String message;
    
    public WebUserResponse getUser() { return user; }
    public void setUser(WebUserResponse user) { this.user = user; }
    
    public WebDriverResponse getDriver() { return driver; }
    public void setDriver(WebDriverResponse driver) { this.driver = driver; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}