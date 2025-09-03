package com.dms.base.controller;

import com.dms.base.service.PackagesService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BasePackagesController {
    @Autowired
    protected PackagesService packagesService;
}