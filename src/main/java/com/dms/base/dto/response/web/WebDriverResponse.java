package com.dms.base.dto.response.web;

public class WebDriverResponse extends WebUserResponse {
    private Long driverId;

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
