package com.gongjun.yuechi.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gongjun.yuechi.mapper.TestMapper;
import com.gongjun.yuechi.model.Test;
import com.gongjun.yuechi.service.ITestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 定义业务层的bean采用@Service注解
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {
    @Resource
    private TestMapper mapper;

    public void ss() {
        int a = this.mapper.selectList(null) == null ? 0 : this.mapper.selectCount(null);
        System.out.println("a=" + a);
    }
}
