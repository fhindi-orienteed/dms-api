package com.dms.base.controller.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.dms.base.mapper.PriceMapper;
import com.dms.base.service.PriceService;

public abstract class PriceController {
    @Autowired
    protected PriceService priceService;

    @Autowired
    protected PriceMapper priceMapper;
}
