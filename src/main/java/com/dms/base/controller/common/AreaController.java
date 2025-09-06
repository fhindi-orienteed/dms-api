package com.dms.base.controller.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.dms.base.mapper.AreaMapper;
import com.dms.base.service.AreaService;

public abstract class AreaController {

    @Autowired
    protected AreaService areaService;

    @Autowired
    protected AreaMapper areaMapper;
}