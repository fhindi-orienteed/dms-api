package com.dms.base.dto.response.mobile;

public class MobilePackageResponse {
    private long id;
    private String message;

    public MobilePackageResponse() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
