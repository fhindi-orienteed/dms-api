package com.dms.base.util;


public class Constant {
    public static final Integer USER_PASSORD_NOT_EXIRED = 0;
    public static final Integer USER_PASSORD_EXIRED = 1;

    public static final Integer USER_STATUS_ENEABLED = 1;
    public static final Integer USER_STATUS_DISABLED = 0;
    public static final Integer NUMBER_OF_EMAIL_VERIFICATION_CHARCTERS = 6;
    public static final Boolean BOOlEAN_OF_EMAIL_VERIFICATION = false;
    public static final Integer NUMBER_OF_MOBILE_VERIFICATION_DIGITS = 4;
    public static final Boolean BOOlEAN_OF_MOBILE_VERIFICATION = true;
    public static final String  USER_ADDRESS = "P";
    public enum RoleType {
        ROLE_ADMIN,
        ROLE_BRANCH_ADMIN,
        ROLE_CSR,
        ROLE_DRIVER,
        ROLE_COMPANY_ADMIN,
        ROLE_COMPANY_USER,
    }

    public enum UpdateRequestStatus {
        APPROVE,
        REJECT,
        PENDING,
        PENDING_REVIEW
    }

    public enum ShippingStatus {
        NEW,
        SHIPPED,
        DELIVERED,
        RETURNED,
        CANCELED
    }

    public enum Channel {
        EMAIL,
        MOBILE,
        WHATSAPP
    }

    public enum VerificationCodeStatus {
        PENDING,
        VERIFIED,
        EXPIRED,
        CANCELLED
    }

    public enum AddressType {
        SHOPPING,
        PROFILE,
        BILLING
    }

}
