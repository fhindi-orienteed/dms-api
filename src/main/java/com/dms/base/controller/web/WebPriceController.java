package com.dms.base.controller.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.PriceController;
import com.dms.base.dto.request.web.CreateNewPriceRequest;
import com.dms.base.dto.response.PaginatedResponse;
import com.dms.base.dto.response.web.WebPriceResponse;
import com.dms.base.model.Price;

@RestController
@RequestMapping("/v1/web/price")
public class WebPriceController extends PriceController {

    @GetMapping("/list")
    public ResponseEntity<PaginatedResponse<WebPriceResponse>> getPriceList(
            @PageableDefault(size = 10) Pageable pageable) {

        Page<Price> priceList = priceService.getPriceList(pageable);

        List<Price> content = priceList.getContent();

        PaginatedResponse<WebPriceResponse> response = new PaginatedResponse<>(
                priceList.getNumber(),
                priceList.getSize(),
                priceList.getTotalElements(),
                priceList.getTotalPages(),
                priceMapper.mapListToWebResponse(content));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/new")
    public ResponseEntity<WebPriceResponse> createPrice(@RequestBody CreateNewPriceRequest request) {
        priceService.createNewPrice(request);
        return ResponseEntity.ok(priceMapper.mapToWebResponse(null));
    }
}
