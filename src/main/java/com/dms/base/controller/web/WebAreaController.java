package com.dms.base.controller.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.AreaController;
import com.dms.base.dto.response.AreaResponse;
import com.dms.base.dto.response.PaginatedResponse;
import com.dms.base.model.Area;

@RestController
@RequestMapping("/v1/web/area")
public class WebAreaController extends AreaController {

    @GetMapping("/list")
    public ResponseEntity<PaginatedResponse<AreaResponse>> getAreas(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city) {

        Page<Area> areaList = areaService.getAreaList(pageable, country, city);

        List<Area> content = areaList.getContent();
        System.out.println(content.size());
        PaginatedResponse<AreaResponse> response = new PaginatedResponse<>(
                areaList.getNumber(),
                areaList.getSize(),
                areaList.getTotalElements(),
                areaList.getTotalPages(),
                areaMapper.mapToWebResponse(content));

        return ResponseEntity.ok(response);
    }
}
