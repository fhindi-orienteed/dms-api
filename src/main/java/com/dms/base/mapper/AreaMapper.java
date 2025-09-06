package com.dms.base.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.AreaResponse;
import com.dms.base.model.Area;

@Component
public class AreaMapper {

    public List<AreaResponse> mapToWebResponse(List<Area> areaList) {
        return areaList.stream().map(area -> {
            AreaResponse a = new AreaResponse();
            a.setId(area.getId());
            a.setName(area.getName());
            a.setCode(area.getCode());
            a.setCity(area.getCity());
            a.setCountry(area.getCountry());
            return a;
        }).toList();
    }
}
