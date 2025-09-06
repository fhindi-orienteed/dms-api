package com.dms.base.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.web.WebPriceResponse;
import com.dms.base.model.Price;

@Component
public class PriceMapper {

    public WebPriceResponse mapToWebResponse(Price price) {

        WebPriceResponse p = new WebPriceResponse();
        p.setId(price.getId());
        p.setName(price.getName());
        p.setCost(price.getCost());
        p.setArea(price.getArea());
        p.setStartDate(price.getStartDate());
        p.setEndDate(price.getEndDate());
        p.setCompanyId(price.getCompanyId());
        p.setBranchId(price.getBranchId());
        p.setCity(price.getCity());
        p.setCountry(price.getCountry());
        p.setIsDefault(price.getIsDefault() == 1);
        p.setStatus(price.getStatus() == 1);

        return p;
    }

    public List<WebPriceResponse> mapListToWebResponse(List<Price> priceList) {
        return priceList.stream().map(price -> mapToWebResponse(price)).toList();
    }
}
