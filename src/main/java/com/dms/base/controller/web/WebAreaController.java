package com.dms.base.controller.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.AreaController;
import com.dms.base.dto.response.AreaResponse;
import com.dms.base.dto.request.web.CreateNewAreaRequest;
import com.dms.base.dto.response.PaginatedResponse;
import com.dms.base.model.Area;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/area")
@Tag(name = "Area API", description = "This class provides RESTful services to get, add, update, or delete area used by DMS Web Portal. You can filter list by country or city.")
public class WebAreaController extends AreaController {

    @GetMapping("/list")
    public ResponseEntity<PaginatedResponse<AreaResponse>> getAreas(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city) {

        Page<Area> areaList = areaService.getAreaList(pageable, country, city);

        List<Area> content = areaList.getContent();

        PaginatedResponse<AreaResponse> response = new PaginatedResponse<>(
                areaList.getNumber(),
                areaList.getSize(),
                areaList.getTotalElements(),
                areaList.getTotalPages(),
                areaMapper.mapToWebResponse(content));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/new")
    public ResponseEntity<AreaResponse> createNewArea(@RequestBody CreateNewAreaRequest req) {
        Area area = areaService.createNewArea(req.getName(), req.getCode(), req.getCountry(), req.getCity(),
                req.getLatitude(), req.getLongitude());
        return ResponseEntity.ok(areaMapper.mapToAreaResponse(area));
    }
}
