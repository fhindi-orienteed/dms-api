package com.dms.base.mapper;

import com.dms.base.dto.PublicPackageResponse;
import com.dms.base.model.Packages;
import org.springframework.stereotype.Component;

@Component
public class PackagesMapper {
      public PublicPackageResponse toPublicResponse(Packages packageEntity) {
        if (packageEntity == null) {
            return null;
        }
        
        PublicPackageResponse response = new PublicPackageResponse();
        response.setUuid(packageEntity.getUuid());
        response.setTrackingNumber(packageEntity.getTrackingNumber());
        response.setReferenceNumber(packageEntity.getReferenceNumber());
        response.setShippingStatus(packageEntity.getShippingStatus());
        response.setDeliveryStatus(packageEntity.getDeliveryStatus());
        response.setPackageType(packageEntity.getPackageType());
        response.setCustomerName(packageEntity.getCustomerName());
        response.setDeliveryArea(packageEntity.getDeliveryArea());
        response.setDeliveryCountry(packageEntity.getDeliveryCountry());
        response.setDeliveryCity(packageEntity.getDeliveryCity());
        response.setDeliveryAddress(packageEntity.getDeliveryAddress());
        response.setDeliveryDate(packageEntity.getDeliveryDate());
        response.setDeliveredOn(packageEntity.getDeliveredOn());
        response.setShippingAt(packageEntity.getShippingAt());
        response.setPackageDescription(packageEntity.getPackageDescription());
        response.setQuantity(packageEntity.getQuantity());
        response.setWeight(packageEntity.getWeight());
        response.setCustomerNote(packageEntity.getCustomerNote());
        response.setDeliveryNote(packageEntity.getDeliveryNote());
        response.setAllowOpen(packageEntity.isAllowOpen());
        response.setBreakable(packageEntity.isBreakable());
        response.setDangerous(packageEntity.isDangerous());
        
        return response;
    }
}
