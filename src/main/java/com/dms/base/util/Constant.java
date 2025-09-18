package com.dms.base.util;

public class Constant {
    public static final Integer USER_PASSORD_NOT_EXIRED = 0;
    public static final Integer USER_PASSORD_EXIRED = 1;

    public static final Integer USER_STATUS_ENEABLED = 1;
    public static final Integer USER_STATUS_DISABLED = 0;

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
}
