package com.dms.base.dto.response.web;

import java.time.LocalDateTime;

public class WebBranchResponse {

    private Long id;
    private String name;
    private String address;
    private Long companyId;
    private String status;
    private LocalDateTime createdAt;

    public WebBranchResponse() {}

    public WebBranchResponse(Long id, String name, String address, Long companyId, String status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.companyId = companyId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
