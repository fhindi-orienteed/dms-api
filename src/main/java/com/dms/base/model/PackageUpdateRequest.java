package com.dms.base.model;

import com.dms.base.util.Constant.Update_Request_Status;
import com.dms.base.dto.PackageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;

@Entity
@Table(name = "package_update_requests")
public class PackageUpdateRequest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private long package_id;
    
    private long submitBy;
    
    @Column(length = 2048)
    private String data; 
    
    private Update_Request_Status request_Status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPackage_id() {
        return package_id;
    }

    public void setPackage_id(long package_id) {
        this.package_id = package_id;
    }

    public long getSubmitBy() {
        return submitBy;
    }

    public void setSubmitBy(long submitBy) {
        this.submitBy = submitBy;
    }

   
    public void setData(PackageDTO data) {
        try {
            this.data = OBJECT_MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize PackageDTO to JSON", e);
        }
    }

    @Transient 
    public PackageDTO getData() {
        try {
            return data != null ? OBJECT_MAPPER.readValue(data, PackageDTO.class) : null;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON to PackageDTO", e);
        }
    }

    public String getDataJson() {
        return data;
    }

    public void setDataJson(String data) {
        this.data = data;
    }

    public Update_Request_Status getRequest_Status() {
        return request_Status;
    }

    public void setRequest_Status(Update_Request_Status request_Status) {
        this.request_Status = request_Status;
    }
}