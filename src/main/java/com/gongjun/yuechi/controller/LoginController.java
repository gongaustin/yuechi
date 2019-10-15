package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gongjun.yuechi.core.annotation.MyLog;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.core.utils.JWTUtil;
import com.gongjun.yuechi.core.utils.Md5;
import com.gongjun.yuechi.model.Dept;
import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.service.IDeptService;
import com.gongjun.yuechi.service.IPermissionService;
import com.gongjun.yuechi.service.IUserPermissionService;
import com.gongjun.yuechi.service.IUserService;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author:GongJun
 * @Date:2019/8/29
 */
@Api(value = "登录",description = "登录接口")
@Controller
public class LoginController {
    @Autowired
    private IUserService service;

    @Autowired
    private IUserPermissionService upservice;

    @Autowired
    private IPermissionService pservice;

    @Autowired
    private IDeptService dservice;

    @ApiImplicitParams({@ApiImplicitParam(paramType="query",name = "username", value = "用户", required = true, dataType = "String"), @ApiImplicitParam(paramType="query",name = "password", value = "密码", required = true, dataType = "String")})
    @PostMapping(value = "/login")
    @ResponseBody
    @MyLog("登录")
    public ResponseBean login(String username, String password, Model model) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new ResponseBean(6001, "username or password empty", null);
        }

        User user = this.service.selectOne(new EntityWrapper<User>().where("username={0}", username));
        if (user == null || !user.getPassword().equals(Md5.md5Encode(password))) {
            return new ResponseBean(6001, "username or password error", null);
        }
        if(user.getStatus()==0){
            return new ResponseBean(6001, "user is locked,please contact manager", null);
        }
        String token;
        //查询权限值
        Wrapper ew = new EntityWrapper().and("uur.user_id={0}",user.getId());
        ew.and("up.type={0}",2);
        if(!"admin".equals(username)){
            ew.and("up.status={0}",1).and("ur.status={0}",1);
        }
        List<Permission> permissions = this.service.selectUserPermissionsByWrapper(ew);
        //查询部门
        Dept d = this.dservice.selectOne(new EntityWrapper<Dept>().where("id={0}",user.getDeptId()));
        try {
            token = JWTUtil.sign(user.getId(), user.getUsername());
        } catch (Exception err) {
            return new ResponseBean(6001, err.getMessage(), null);
        }
        return new ResponseBean(200, "login success", ImmutableMap.of("realname",user.getRealname(),"dept",d==null?"无":d.getDeptName(),"token",token,"permissions",Objects.requireNonNull(permissions == null ? null : permissions.stream().map(Permission::getPermissionValue).collect(Collectors.toList()))));

    }
}