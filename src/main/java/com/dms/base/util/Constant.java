package com.dms.base.util;

public class Constant {
    public static final Integer USER_PASSORD_NOT_EXIRED = 0;
    public static final Integer USER_PASSORD_EXIRED = 1;

    public static final Integer USER_STATUS_ENEABLED = 1;
    public static final Integer USER_STATUS_DISABLED = 0;

    public enum RoleType {
        ROLE_ADMIN,
        ROLE_CSR,
        ROLE_DRIVER,
        ROLE_MERCHANT_ADMIN,
        ROLE_MERCHANT_USER
    }

    public enum UpdateRequestStatus {
        APPROVE,
        REJECT,
        PENDING,
        PENDING_REVIEW
    }
}
