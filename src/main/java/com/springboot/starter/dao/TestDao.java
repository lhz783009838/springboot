package com.springboot.starter.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.starter.entity.TestEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author baker
 */
public interface TestDao extends BaseMapper<TestEntity> {

    /**
     * 根据ID查找集合
     * @param id id
     * @return 集合
     */
    List<TestEntity> findTestById(@Param("id") Long id);
}
