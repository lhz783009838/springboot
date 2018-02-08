package com.springboot.starter.entity.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.springboot.starter.dao.TestDao;
import com.springboot.starter.entity.TestEntity;
import com.springboot.starter.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author baker
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestDao,TestEntity> implements TestService {

    @Override
    public List<TestEntity> findTestById(Long id) {
        return baseMapper.findTestById(id);
    }
}
