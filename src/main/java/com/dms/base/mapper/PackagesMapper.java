package com.dms.base.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.PublicPackageResponse;
import com.dms.base.dto.response.mobile.MobilePackageResponse;
import com.dms.base.model.Packages;

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

    public MobilePackageResponse toMobileResponse(Packages packg) {
        if (packg == null) {
            return null;
        }

        MobilePackageResponse response = new MobilePackageResponse();
        response.setUuid(packg.getUuid());
        response.setTrackingNumber(packg.getTrackingNumber());
        response.setReferenceNumber(packg.getReferenceNumber());

        return response;
    }

    public List<MobilePackageResponse> toMobileResponse(List<Packages> list) {
        return list.stream().map(packg -> toMobileResponse(packg)).toList();
    }
}
