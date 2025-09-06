package com.dms.base.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dms.base.model.Price;

public class PriceSpecification {

    public static Specification<Price> hasCountry(String country) {
        return (root, query, cb) -> country == null ? null : cb.equal(root.get("country"), country);
    }

    public static Specification<Price> hasCity(String city) {
        return (root, query, cb) -> city == null ? null : cb.equal(root.get("city"), city);
    }
}
