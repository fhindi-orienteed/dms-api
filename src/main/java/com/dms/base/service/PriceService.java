package com.dms.base.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dms.base.dto.request.web.CreateNewPriceRequest;
import com.dms.base.exception.ObjectNotFoundException;
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

    public Price createNewPrice(CreateNewPriceRequest request) {
        Price price = new Price();

        price.setCompanyId(request.getCompanyId());
        price.setBranchId(request.getBranchId());
        price.setCost(request.getCost());
        price.setCity(request.getCity());
        price.setArea(request.getArea());
        price.setCountry(request.getCountry());
        price.setEndDate(request.getEndDate());
        price.setStartDate(request.getStartDate());

        // don't allow to create default price. it's should be setup manualy in DB
        price.setIsDefault(0);

        price.setStatus(request.getStatus() ? 1 : 0);
        price.setName(request.getName());

        return priceRepository.save(price);
    }

    public Price getPrice(long id) {
        return priceRepository.findById(id).orElse(null);
    }

    public Price updatePrice(Long id, String name, String country, String city, String area, LocalDateTime startDate,
            LocalDateTime endDate, Double cost, Integer status) {
        Price price = getPrice(id);

        if (price == null) {
            throw new ObjectNotFoundException("Price with id" + id + "not found");
        }

        price.setName(name);
        price.setCountry(country);
        price.setCity(city);
        price.setArea(area);
        price.setStartDate(startDate);
        price.setEndDate(endDate);
        price.setCost(cost);
        price.setStatus(status);
        return priceRepository.save(price);
    }

   public void deletePriceById(Long priceId) {

        Price price = priceRepository.findById(priceId)
                .orElseThrow(() -> new ObjectNotFoundException("Price with id " + priceId + " not found"));

        price.setStatus(0);
        priceRepository.save(price);
    }
}
