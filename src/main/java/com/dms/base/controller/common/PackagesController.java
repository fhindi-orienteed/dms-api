package com.dms.base.controller.common;

import com.dms.base.service.PackagesService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PackagesController {
    @Autowired
    protected PackagesService packagesService;
}