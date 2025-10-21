package com.dms.base.dto.response.web;

import com.dms.base.dto.response.web.WebDriverResponse;
import com.dms.base.dto.response.web.WebCompanyResponse;

public class WebUserProfileResponse {

    private String email;
    private String role;

    private String firstName;
    private String lastName;
    private String mobile;
    private String address;

    private WebCompanyResponse companyDetails;
    private WebDriverResponse driverDetails;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public WebCompanyResponse getCompanyDetails() { return companyDetails; }
    public void setCompanyDetails(WebCompanyResponse companyDetails) { this.companyDetails = companyDetails; }
    public WebDriverResponse getDriverDetails() { return driverDetails; }
    public void setDriverDetails(WebDriverResponse driverDetails) { this.driverDetails = driverDetails; }
   
}