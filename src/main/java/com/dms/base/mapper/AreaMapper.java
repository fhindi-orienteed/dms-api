package com.dms.base.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.AreaResponse;
import com.dms.base.dto.request.web.CreateNewAreaRequest;
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

    public Area mapRequestToArea(CreateNewAreaRequest newAreaRequest){
        Area area = new Area();
        area.setName(newAreaRequest.getName());
        area.setCode(newAreaRequest.getCode());
        area.setCity(newAreaRequest.getCity());
        area.setCountry(newAreaRequest.getCountry());
        area.setLatitude(newAreaRequest.getLatitude());
        area.setLongitude(newAreaRequest.getLongitude());

        return area;
    }

    public AreaResponse mapToAreaResponse(Area area){
        AreaResponse response = new AreaResponse();
        response.setId(area.getId());
        response.setName(area.getName());
        response.setCode(area.getCode());
        response.setCountry(area.getCountry());
        response.setCity(area.getCity());
        response.setLatitude(area.getLatitude());
        response.setLongitude(area.getLongitude());

        return response;
    }
}
