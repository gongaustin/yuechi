package com.gongjun.yuechi.controller;


import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.Dept;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.service.IDeptService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 科室 前端控制器
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Api(value = "/dept", description = "部门科室前端控制器接口")
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private IDeptService service;

    @GetMapping("")
    public ResponseBean test(String s){
        List<Dept> userList = this.service.selectList(null);
        userList.get(0).setCtime(new Date());
        this.service.updateById(userList.get(0));





        return new ResponseBean(200,"",userList);
    }

}

