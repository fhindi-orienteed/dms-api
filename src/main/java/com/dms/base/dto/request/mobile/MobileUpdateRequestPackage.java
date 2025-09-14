package com.dms.base.dto.request.mobile;

import com.dms.base.dto.PackageDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MobileUpdateRequestPackage {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private String data;
    
    public MobileUpdateRequestPackage() {
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