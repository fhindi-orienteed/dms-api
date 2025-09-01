package com.dms.base.service;
import com.dms.base.model.Package;
import com.dms.base.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    private final PackageRepository packageRepository;

    @Autowired
    public PackageService(PackageRepository packageRepository){
        this.packageRepository = packageRepository;
    }

    public List<Package> getAllPackages(){
        return packageRepository.findAll();
    }
    
    public Optional<Package> getPackageById(long id){
        return packageRepository.findById(id);
    }

    public Package createPackage(Package newPackage){
        return packageRepository.save(newPackage);
    }

  public Package updatePackage(long id, Package packageDetails) {
        Package existingPackage = packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found with id: " + id)); 
        existingPackage.setCreatedBy(packageDetails.getCreatedBy());
        existingPackage.setCompanyId(packageDetails.getCompanyId());
        existingPackage.setDriverId(packageDetails.getDriverId());
        existingPackage.setBranchId(packageDetails.getBranchId());
        existingPackage.setCarId(packageDetails.getCarId());
        existingPackage.setCustomerNote(packageDetails.getCustomerNote());
        existingPackage.setPackageDescription(packageDetails.getPackageDescription());
        existingPackage.setQuantity(packageDetails.getQuantity());
        existingPackage.setWeight(packageDetails.getWeight());
        existingPackage.setTrackingNumber(packageDetails.getTrackingNumber());
        existingPackage.setReferenceNumber(packageDetails.getReferenceNumber());
        existingPackage.setShippingStatus(packageDetails.getShippingStatus());
        existingPackage.setDeliveryStatus(packageDetails.getDeliveryStatus());
        existingPackage.setCollectionStatus(packageDetails.getCollectionStatus());
        existingPackage.setPaymentStatus(packageDetails.getPaymentStatus());
        existingPackage.setServiceType(packageDetails.getServiceType());
        existingPackage.setPackageType(packageDetails.getPackageType());
        existingPackage.setCustomerId(packageDetails.getCustomerId());
        existingPackage.setCustomerName(packageDetails.getCustomerName());
        existingPackage.setCustomerMobile1(packageDetails.getCustomerMobile1());
        existingPackage.setCustomerMobile2(packageDetails.getCustomerMobile2());
        existingPackage.setDeliveryArea(packageDetails.getDeliveryArea());
        existingPackage.setDeliveryCountry(packageDetails.getDeliveryCountry());
        existingPackage.setDeliveryCity(packageDetails.getDeliveryCity());
        existingPackage.setDeliveryAddress(packageDetails.getDeliveryAddress());
        existingPackage.setDeliveryLongitude(packageDetails.getDeliveryLongitude());
        existingPackage.setDeliveryLatitude(packageDetails.getDeliveryLatitude());
        existingPackage.setDeliveryDate(packageDetails.getDeliveryDate());
        existingPackage.setDeliveredOn(packageDetails.getDeliveredOn());
        existingPackage.setShippingAt(packageDetails.getShippingAt());
        existingPackage.setDeliveryNote(packageDetails.getDeliveryNote());
        existingPackage.setDeliveryCost(packageDetails.getDeliveryCost());
        existingPackage.setAllowOpen(packageDetails.isAllowOpen());
        existingPackage.setBreakable(packageDetails.isBreakable());
        existingPackage.setDangerous(packageDetails.isDangerous());
        existingPackage.setNeedPackaging(packageDetails.isNeedPackaging());
        existingPackage.setNeedCooling(packageDetails.isNeedCooling());
        existingPackage.setPaymentMethod(packageDetails.getPaymentMethod());
        existingPackage.setPaymentAmount(packageDetails.getPaymentAmount());
        existingPackage.setCollectionMethod(packageDetails.getCollectionMethod());
        existingPackage.setDeliveryRetries(packageDetails.getDeliveryRetries());
        return packageRepository.save(existingPackage);
    }
    
    public void deletePackage(long id){
        if(packageRepository.existsById(id)){
            packageRepository.deleteById(id);
        } else {
            throw new RuntimeException("Package not found with id: " + id);
        }
    }
}
