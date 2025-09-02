package com.dms.base.dto;
import java.util.Date;

public class PublicPackageResponse {
    private String uuid;
    private String trackingNumber;
    private String referenceNumber;

    // Status information
    private String shippingStatus;
    private String deliveryStatus;
    private String collectionStatus;
    private String paymentStatus;

    // Service details
    private String serviceType;
    private String packageType;

    // customer info
    private String customerName;

    // Delivery location
    private String deliveryArea;
    private String deliveryCountry;
    private String deliveryCity;
    private String deliveryAddress;

    // Timelines
    private Date deliveryDate;
    private Date deliveredOn;
    private Date shippingAt;

    // Package details
    private String packageDescription;
    private int quantity;
    private float weight;
    
    // Notes    
    private String customerNote;
    private String deliveryNote;

    // Payment information
    private String paymentMethod;
    private float paymentAmount;
    private float deliveryCost;

    // Handling flags
    private boolean allowOpen;
    private boolean breakable;
    private boolean dangerous;
    private int deliveryRetries;
   
    public PublicPackageResponse() {
    }
    
    public String getUuid() {
        return uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public String getTrackingNumber() {
        return trackingNumber;
    }
    
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    
    public String getReferenceNumber() {
        return referenceNumber;
    }
    
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    
    public String getShippingStatus() {
        return shippingStatus;
    }
    
    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
    
    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    public String getCollectionStatus() {
        return collectionStatus;
    }
    
    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
    
    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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
    
    public Date getDeliveredOn() {
        return deliveredOn;
    }
    
    public void setDeliveredOn(Date deliveredOn) {
        this.deliveredOn = deliveredOn;
    }
    
    public Date getShippingAt() {
        return shippingAt;
    }
    
    public void setShippingAt(Date shippingAt) {
        this.shippingAt = shippingAt;
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
    
    public String getCustomerNote() {
        return customerNote;
    }
    
    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }
    
    public String getDeliveryNote() {
        return deliveryNote;
    }
    
    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public float getPaymentAmount() {
        return paymentAmount;
    }
    
    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    
    public float getDeliveryCost() {
        return deliveryCost;
    }
    
    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
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
    
    public int getDeliveryRetries() {
        return deliveryRetries;
    }
    
    public void setDeliveryRetries(int deliveryRetries) {
        this.deliveryRetries = deliveryRetries;
    }
}