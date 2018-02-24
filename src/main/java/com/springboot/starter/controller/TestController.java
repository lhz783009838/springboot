package com.springboot.starter.controller;

import com.springboot.starter.common.handle.BaseController;
import com.springboot.starter.common.properties.DataSourceProperties;
import com.springboot.starter.common.response.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author baker
 */
@RestController
@RequestMapping(value = "/test")
public class TestController extends BaseController{

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @GetMapping(value = "/error")
    public String error() {
        int a = 1, b = 0;
        int c = a / b;
        return "success";
    }

    @GetMapping(value = "${api}/hello")
    public DataResult hello(){
        return DataResult.success();
    }
}
