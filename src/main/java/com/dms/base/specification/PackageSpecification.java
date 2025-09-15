package com.dms.base.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dms.base.model.Packages;

public class PackageSpecification {

    public static Specification<Packages> hasCustomerMobile(String customerMobile) {
        return (root, query, cb) -> customerMobile == null ? null
                : cb.equal(root.get("customerMobile1"), customerMobile);
    }

    public static Specification<Packages> hasCustomerName(String customerName) {
        return (root, query, cb) -> customerName == null ? null : cb.equal(root.get("customerName"), customerName);
    }

    public static Specification<Packages> hasPackageUUID(String uuid) {
        return (root, query, cb) -> uuid == null ? null : cb.equal(root.get("uuid"), uuid);
    }
}
