package com.dms.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dms.base.model.Area;
import com.dms.base.repository.AreaRepository;
import com.dms.base.specification.AreaSpecification;

@Service
public class AreaService {
    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public Page<Area> getAreaList(Pageable pageable) {
        return areaRepository.findAll(pageable);
    }

    public Page<Area> getAreaList(Pageable pageable, String country, String city) {
        Specification<Area> spec = (root, query, cb) -> cb.conjunction();

        if (country != null && !country.isBlank()) {
            spec = spec.and(AreaSpecification.hasCountry(country));
        }
        if (city != null && !city.isBlank()) {
            spec = spec.and(AreaSpecification.hasCity(city));
        }

        return areaRepository.findAll(spec, pageable);
    }

    public Area createNewArea(Area newArea){
        return areaRepository.save(newArea);
    }
}
