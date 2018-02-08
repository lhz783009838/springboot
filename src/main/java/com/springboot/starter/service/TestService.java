package com.springboot.starter.service;

import com.baomidou.mybatisplus.service.IService;
import com.springboot.starter.entity.TestEntity;

import java.util.List;

/**
 * @author baker
 */
public interface TestService extends IService<TestEntity> {

    /**
     * 测试
     * @param id id
     * @return list
     */
    List<TestEntity> findTestById(Long id);
}
