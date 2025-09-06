package com.dms.base.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dms.base.model.Area;

public class AreaSpecification {

    public static Specification<Area> hasCountry(String country) {
        return (root, query, cb) -> country == null ? null : cb.equal(root.get("country"), country);
    }

    public static Specification<Area> hasCity(String city) {
        return (root, query, cb) -> city == null ? null : cb.equal(root.get("city"), city);
    }
}
