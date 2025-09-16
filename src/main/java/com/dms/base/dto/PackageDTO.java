package com.dms.base.dto;
import java.util.Date;

public class PackageDTO {
    private String customerNote;
    private String packageDescription;
    private int quantity;
    private float weight;
    private String referenceNumber;
    private String serviceType;
    private String packageType;
    private String customerName;
    private String customerMobile1;
    private String customerMobile2;
    private String deliveryArea;
    private String deliveryCountry;
    private String deliveryCity;
    private String deliveryAddress;
    private Date deliveryDate;
    private String deliveryNote;
    private boolean allowOpen;
    private boolean breakable;
    private boolean dangerous;
    private boolean needPackaging;
    private boolean needCooling;


    public PackageDTO() {
    }


    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }


    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile1() {
        return customerMobile1;
    }

    public void setCustomerMobile1(String customerMobile1) {
        this.customerMobile1 = customerMobile1;
    }

    public String getCustomerMobile2() {
        return customerMobile2;
    }

    public void setCustomerMobile2(String customerMobile2) {
        this.customerMobile2 = customerMobile2;
    }

    public String getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(String deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }


    public boolean isAllowOpen() {
        return allowOpen;
    }

    public void setAllowOpen(boolean allowOpen) {
        this.allowOpen = allowOpen;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public boolean isDangerous() {
        return dangerous;
    }

    public void setDangerous(boolean dangerous) {
        this.dangerous = dangerous;
    }

    public boolean isNeedPackaging() {
        return needPackaging;
    }

    public void setNeedPackaging(boolean needPackaging) {
        this.needPackaging = needPackaging;
    }

    public boolean isNeedCooling() {
        return needCooling;
    }

    public void setNeedCooling(boolean needCooling) {
        this.needCooling = needCooling;
    }

}
