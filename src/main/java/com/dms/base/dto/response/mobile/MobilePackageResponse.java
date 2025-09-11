package com.dms.base.dto.response.mobile;

import com.dms.base.dto.response.common.PackageResponse;

public class MobilePackageResponse extends PackageResponse {
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
