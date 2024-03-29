package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.core.utils.Md5;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.model.vo.UserVo;
import com.gongjun.yuechi.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @ApiOperation(value = "分页查询用户", notes = "分页查询用户")
    @RequiresAuthentication
    @GetMapping("/page")
    public ResponseBean selectUserPage(Page page,String keyword){

        EntityWrapper ew  = new EntityWrapper();

        if(StringUtils.isNotBlank(keyword)){
            ew.like("uu.realname",keyword)
                    .or("uu.position like{0}","%"+keyword+"%")
                    .or("ud.dept_name like{0}","%"+keyword+"%");
        }
        List<UserVo> users = this.service.selectUserVoPage(page,ew);
        if(!CollectionUtils.isEmpty(users)){
            users.forEach(e->{e.setPassword(null);e.setSalt(null);});
        }
        page.setRecords(users);
        return new ResponseBean(HttpStatus.OK.value(),"",page);
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @RequiresAuthentication
    @PostMapping(value = "/add",params = {"realname"})
    public ResponseBean addUser(User user){
        user.setUsername(IdWorker.getIdStr());//非管理员直接默认一个账号
        String passwordMD5 = Md5.md5Encode(user.getPassword()==null?"123456":user.getPassword());

        user.setPassword(passwordMD5);
        user.setSalt(passwordMD5);

        try {
            this.service.insert(user);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }

        return new ResponseBean(HttpStatus.OK.value(),"add success",null);
    }
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @RequiresAuthentication
    @PostMapping(value = "/delete",params = {"id"})
    public ResponseBean deleteUser(String id){

        try {
            this.service.deleteById(id);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"delete success",null);

    }

    @ApiOperation(value = "查询单个用户", notes = "查询单个用户")
    @RequiresAuthentication
    @GetMapping(value = "/findUserById",params = {"id"})
    public ResponseBean findUserById(String id){

        UserVo user;
        try {
            user = this.service.findUserById(id);
            if(null != user) user.setPassword(null); user.setSalt(null);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"select success",user);

    }

    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @RequiresAuthentication
    @PostMapping(value = "/edit",params = {"id"})
    public ResponseBean editUser(User user){
        try {
            this.service.updateById(user);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"edit success",user);
    }





}

