package com.dms.base.service;
import com.dms.base.model.Packages;
import com.dms.base.repository.PackagesRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PackagesService {
    private final PackagesRepository packagesRepository;

    public PackagesService(PackagesRepository packagesRepository){
        this.packagesRepository = packagesRepository;
    }

    public Optional<Packages> getPackageByUuid(String uuid){
        Optional<Packages> pkg = packagesRepository.findByUuid(uuid); 
        return pkg;
    }

    public Optional<Packages> getPackageById(Long id){
        Optional<Packages> pkg = packagesRepository.findById(id); 
        return pkg;
    }

    public Packages updatePackage(Long id, Packages updatedPackage) {
        Packages existingPackage = packagesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found with id " + id));

        existingPackage.setCreatedBy(updatedPackage.getCreatedBy());
        existingPackage.setCreatedAt(updatedPackage.getCreatedAt());
        existingPackage.setCompanyId(updatedPackage.getCompanyId());
        existingPackage.setDriverId(updatedPackage.getDriverId());
        existingPackage.setBranchId(updatedPackage.getBranchId());
        existingPackage.setCarId(updatedPackage.getCarId());
        existingPackage.setCustomerNote(updatedPackage.getCustomerNote());
        existingPackage.setPackageDescription(updatedPackage.getPackageDescription());
        existingPackage.setQuantity(updatedPackage.getQuantity());
        existingPackage.setWeight(updatedPackage.getWeight());
        existingPackage.setTrackingNumber(updatedPackage.getTrackingNumber());
        existingPackage.setReferenceNumber(updatedPackage.getReferenceNumber());
        existingPackage.setShippingStatus(updatedPackage.getShippingStatus());
        existingPackage.setDeliveryStatus(updatedPackage.getDeliveryStatus());
        existingPackage.setCollectionStatus(updatedPackage.getCollectionStatus());
        existingPackage.setPaymentStatus(updatedPackage.getPaymentStatus());
        existingPackage.setServiceType(updatedPackage.getServiceType());
        existingPackage.setPackageType(updatedPackage.getPackageType());
        existingPackage.setCustomerId(updatedPackage.getCustomerId());
        existingPackage.setCustomerName(updatedPackage.getCustomerName());
        existingPackage.setCustomerMobile1(updatedPackage.getCustomerMobile1());
        existingPackage.setCustomerMobile2(updatedPackage.getCustomerMobile2());
        existingPackage.setDeliveryArea(updatedPackage.getDeliveryArea());
        existingPackage.setDeliveryCountry(updatedPackage.getDeliveryCountry());
        existingPackage.setDeliveryCity(updatedPackage.getDeliveryCity());
        existingPackage.setDeliveryAddress(updatedPackage.getDeliveryAddress());
        existingPackage.setDeliveryLongitude(updatedPackage.getDeliveryLongitude());
        existingPackage.setDeliveryLatitude(updatedPackage.getDeliveryLatitude());
        existingPackage.setDeliveryDate(updatedPackage.getDeliveryDate());
        existingPackage.setDeliveredOn(updatedPackage.getDeliveredOn());
        existingPackage.setShippingAt(updatedPackage.getShippingAt());
        existingPackage.setDeliveryNote(updatedPackage.getDeliveryNote());
        existingPackage.setDeliveryCost(updatedPackage.getDeliveryCost());
        existingPackage.setAllowOpen(updatedPackage.isAllowOpen());
        existingPackage.setBreakable(updatedPackage.isBreakable());
        existingPackage.setDangerous(updatedPackage.isDangerous());
        existingPackage.setNeedPackaging(updatedPackage.isNeedPackaging());
        existingPackage.setNeedCooling(updatedPackage.isNeedCooling());
        existingPackage.setPaymentMethod(updatedPackage.getPaymentMethod());
        existingPackage.setPaymentAmount(updatedPackage.getPaymentAmount());
        existingPackage.setCollectionMethod(updatedPackage.getCollectionMethod());
        existingPackage.setDeliveryRetries(updatedPackage.getDeliveryRetries());
        
        return packagesRepository.save(existingPackage);
    }

    public Packages createNewPackage(Packages newPackages){
        return packagesRepository.save(newPackages);
    }
}
