package com.dms.base.controller.web;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.PriceController;
import com.dms.base.dto.request.web.CreateNewPriceRequest;
import com.dms.base.dto.request.web.UpdatePriceRequest;
import com.dms.base.dto.response.PaginatedResponse;
import com.dms.base.dto.response.web.WebPriceResponse;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.model.Price;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/price")
@Tag(name = "Price API", description = "This class provides RESTful services to get, add, update, or delete price list used by DMS Web Portal.")
public class WebPriceController extends PriceController {

    @GetMapping("/list")
    @Operation(summary = "Get Price List", description = "Returns a list for all price list defined in the system includs default price list")
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
        return ResponseEntity.ok(priceMapper.mapToWebResponse(priceService.createNewPrice(request)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WebPriceResponse> updatePrice(@PathVariable long id,@RequestBody UpdatePriceRequest request){
        Optional<Price> exist_price = priceService.getPriceById(id);
        if(exist_price.isEmpty()){
            throw new ObjectNotFoundException("Price with ID " + id + " Not Found");
        }

        Price mqppedPrice = priceMapper.mapUpdateRequestToPrice(request);
        Price updatedPrice = priceService.updatePrice(id, mqppedPrice);
        WebPriceResponse response = priceMapper.mapToWebResponse(updatedPrice);
        response.setId(updatedPrice.getId());
        response.setIsDefault(updatedPrice.getIsDefault() == 1); 
        return ResponseEntity.ok(response);
    }
}
