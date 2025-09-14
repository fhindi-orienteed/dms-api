package com.dms.base.dto.request.mobile;

import com.dms.base.dto.PackageDTO;
import com.dms.base.util.Constant.Update_Request_Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MobileUpdateRequestPackage {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    private long package_id;
    private long submitBy;
    private Update_Request_Status request_Status;
    private String data;
    
    public MobileUpdateRequestPackage() {
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

    public Update_Request_Status getRequest_Status() {
        return request_Status;
    }

    public void setRequest_Status(Update_Request_Status request_Status) {
        this.request_Status = request_Status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @JsonProperty("data")
    public void setDataObject(PackageDTO dataObject) {
        try {
            this.data = OBJECT_MAPPER.writeValueAsString(dataObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize PackageDTO to JSON", e);
        }
    }

    @JsonIgnore
    public PackageDTO getDataObject() {
        try {
            return data != null ? OBJECT_MAPPER.readValue(data, PackageDTO.class) : null;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON to PackageDTO", e);
        }
    }
}