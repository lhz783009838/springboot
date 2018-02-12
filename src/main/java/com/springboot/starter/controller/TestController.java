package com.springboot.starter.controller;

import com.springboot.starter.common.handle.BaseController;
import com.springboot.starter.common.properties.DataSourceProperties;
import com.springboot.starter.common.properties.DruidProperties;
import com.springboot.starter.common.response.DataResult;
import com.springboot.starter.entity.TestEntity;
import com.springboot.starter.service.TestService;
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

    @Autowired
    private TestService testService;

    @GetMapping(value = "/getSource")
    public DruidProperties getSource() {
        return dataSourceProperties.getDruid();
    }

    @PostMapping(value = "add")
    public String add(@RequestBody TestEntity entity) {
        testService.insert(entity);
        return "success";
    }

    @GetMapping(value = "/list/{id}")
    public List<TestEntity> findTestById(@PathVariable Long id) {
        return testService.findTestById(id);
    }

    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable Long id) {
        TestEntity testEntity = testService.selectById(id);
        testEntity.setInsertMsg(null);
        testService.updateById(testEntity);
        return "success";
    }

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
