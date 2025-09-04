package com.dms.base.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date createdDate;
    private String name;
    private String county;
    private String city;
    private String address;
    private String email;
    private String phone;
    private String mobile;
    private String facebook;
    private String integram;
    private String status;


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
    
    public String getName() { 
        return name; 
    }
    public void setName(String name) {
         this.name = name;
         }
    
    public String getCounty() { 
        return county; 
    }
    public void setCounty(String county) { 
        this.county = county; 
    }
    
    public String getCity() { 
        return city;
     }
    public void setCity(String city) {
         this.city = city;
         }
    
    public String getAddress() {
         return address; 
        }
    public void setAddress(String address) { 
        this.address = address; 
    }
    
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public String getPhone() { 
        return phone;
     }
    public void setPhone(String phone) { 
        this.phone = phone; 
    }
    
    public String getMobile() { 
        return mobile; 
    }
    public void setMobile(String mobile) {
         this.mobile = mobile;
         }
    
    public String getFacebook() { 
        return facebook; 
    }
    public void setFacebook(String facebook) { 
        this.facebook = facebook; 
    }
    
    public String getIntegram() { 
        return integram; 
    }
    public void setIntegram(String integram) {
         this.integram = integram; 
        }
    
    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) {
        this.status = status; 
    }
}