package com.dms.base.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.UserController;

@RestController
@RequestMapping("/v1/web/users")
public class WebUserController extends UserController {

}
