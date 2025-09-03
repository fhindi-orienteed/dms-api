package com.dms.base.mapper;

import com.dms.base.dto.MobilePackageRequest;
import com.dms.base.model.Packages;
import org.springframework.stereotype.Component;

@Component
public class MobilePackageMapper {

        public Packages toPackageRequest(MobilePackageRequest request){
        if (request == null) {
            return null;
        }

        Packages pkg = new Packages();
        pkg.setCreatedBy(request.getCreatedBy());
        pkg.setCompanyId(request.getCompanyId());
        pkg.setDriverId(request.getDriverId());
        pkg.setBranchId(request.getBranchId());
        pkg.setCarId(request.getCarId());
        pkg.setCustomerNote(request.getCustomerNote());
        pkg.setPackageDescription(request.getPackageDescription());
        pkg.setQuantity(request.getQuantity());
        pkg.setWeight(request.getWeight());
        pkg.setTrackingNumber(request.getTrackingNumber());
        pkg.setReferenceNumber(request.getReferenceNumber());
        pkg.setShippingStatus(request.getShippingStatus());
        pkg.setDeliveryStatus(request.getDeliveryStatus());
        pkg.setCollectionStatus(request.getCollectionStatus());
        pkg.setPaymentStatus(request.getPaymentStatus());
        pkg.setServiceType(request.getServiceType());
        pkg.setPackageType(request.getPackageType());
        pkg.setCustomerId(request.getCustomerId());
        pkg.setCustomerName(request.getCustomerName());
        pkg.setCustomerMobile1(request.getCustomerMobile1());
        pkg.setCustomerMobile2(request.getCustomerMobile2());
        pkg.setDeliveryArea(request.getDeliveryArea());
        pkg.setDeliveryCountry(request.getDeliveryCountry());
        pkg.setDeliveryCity(request.getDeliveryCity());
        pkg.setDeliveryAddress(request.getDeliveryAddress());
        pkg.setDeliveryLongitude(request.getDeliveryLongitude());
        pkg.setDeliveryLatitude(request.getDeliveryLatitude());
        pkg.setDeliveryDate(request.getDeliveryDate());
        pkg.setDeliveredOn(request.getDeliveredOn());
        pkg.setShippingAt(request.getShippingAt());
        pkg.setDeliveryNote(request.getDeliveryNote());
        pkg.setDeliveryCost(request.getDeliveryCost());
        pkg.setAllowOpen(request.isAllowOpen());
        pkg.setBreakable(request.isBreakable());
        pkg.setDangerous(request.isDangerous());
        pkg.setNeedPackaging(request.isNeedPackaging());
        pkg.setNeedCooling(request.isNeedCooling());
        pkg.setPaymentMethod(request.getPaymentMethod());
        pkg.setPaymentAmount(request.getPaymentAmount());
        pkg.setCollectionMethod(request.getCollectionMethod());
        pkg.setDeliveryRetries(request.getDeliveryRetries());

        return pkg;
    }
}
