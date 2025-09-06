package com.dms.base.dto.response.web;

import com.dms.base.dto.PriceDTO;

public class WebPriceResponse extends PriceDTO {
    private Long id;
    private Boolean isDefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

}
