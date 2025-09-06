package com.dms.base.controller.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.PriceController;
import com.dms.base.dto.response.PaginatedResponse;
import com.dms.base.dto.response.PriceResponse;
import com.dms.base.model.Price;

@RestController
@RequestMapping("/v1/web/price")
public class WebPriceController extends PriceController {

    @GetMapping("/list")
    public ResponseEntity<PaginatedResponse<PriceResponse>> getPriceList(
            @PageableDefault(size = 10) Pageable pageable) {

        Page<Price> priceList = priceService.getPriceList(pageable);

        List<Price> content = priceList.getContent();

        PaginatedResponse<PriceResponse> response = new PaginatedResponse<>(
                priceList.getNumber(),
                priceList.getSize(),
                priceList.getTotalElements(),
                priceList.getTotalPages(),
                priceMapper.mapToWebResponse(content));

        return ResponseEntity.ok(response);
    }
}
