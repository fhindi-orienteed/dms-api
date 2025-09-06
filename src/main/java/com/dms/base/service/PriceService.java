package com.dms.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dms.base.model.Price;
import com.dms.base.repository.PriceRepository;
import com.dms.base.specification.PriceSpecification;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Page<Price> getPriceList(Pageable pageable) {
        return priceRepository.findAll(pageable);
    }

    public Page<Price> getPriceList(Pageable pageable, String country, String city) {
        Specification<Price> spec = (root, query, cb) -> cb.conjunction();

        if (country != null && !country.isBlank()) {
            spec = spec.and(PriceSpecification.hasCountry(country));
        }
        if (city != null && !city.isBlank()) {
            spec = spec.and(PriceSpecification.hasCity(city));
        }

        return priceRepository.findAll(spec, pageable);
    }
}
