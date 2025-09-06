package com.dms.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dms.base.model.Area;
import com.dms.base.repository.AreaRepository;

@Service
public class AreaService {
    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public Page<Area> getAreaList(Pageable pageable) {
        return areaRepository.findAll(pageable);
    }
}
