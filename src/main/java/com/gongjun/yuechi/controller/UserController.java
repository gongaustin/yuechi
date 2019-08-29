package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.service.IUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户人员 前端控制器
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Api(value = "/user", description = "用户人员前端控制器接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    /**
     * 分页查询用户
     * */
    @GetMapping("/page")
    public ResponseBean selectUserPage(Page<User> page){

        Page<User> users = this.service.selectPage(page);
        if(!CollectionUtils.isEmpty(users.getRecords())){
            users.getRecords().forEach(e->{e.setPassword(null);e.setSalt(null);});
        }
        return new ResponseBean(HttpStatus.OK.value(),"",users);
    }

}

