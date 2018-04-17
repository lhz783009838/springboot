package com.springboot.starter.controller;

import com.springboot.starter.common.handle.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author linhuanzhen
 */
@Controller
public class TestController extends BaseController {

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

}
