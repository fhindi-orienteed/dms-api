package com.dms.base.controller.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.PackagesController;
import com.dms.base.model.Packages;
import com.dms.base.util.Constant.ShippingStatus;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/packages")
@Tag(name = "Packages API", description = "These services provide APIs related to packages management for DMS Web portal.")
public class WebPackagesController extends PackagesController {
    @GetMapping("/summary")
    public ResponseEntity<?> getPackageSummaryByStatus() {
        List<Packages> packages = packagesService.getAllPackages();
        Map<String, List<Packages>> packagesByStatus = packages.stream()
                .collect(Collectors.groupingBy(Packages::getShippingStatus));
        Map<String, Object> response = new HashMap<>();
        for (ShippingStatus status : ShippingStatus.values()) {
            String statusName = status.name();
            List<Packages> statusPackages = packagesByStatus.getOrDefault(statusName, Collections.emptyList());
            long count = statusPackages.size();
            double totalAmount = statusPackages.stream().mapToDouble(p -> p.getPaymentAmount()).sum();
            Map<String, Object> statusDetails = new HashMap<>();
            statusDetails.put("count", count);
            statusDetails.put("collectionAmount", totalAmount);
            response.put(statusName, statusDetails);
        }

        return ResponseEntity.ok(response);
    }
}
