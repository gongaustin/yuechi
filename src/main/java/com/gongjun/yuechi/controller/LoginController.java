package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.core.utils.JWTUtil;
import com.gongjun.yuechi.core.utils.Md5;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author:GongJun
 * @Date:2019/8/29
 */
@Api(value = "登录")
@Controller
public class LoginController {
    @Autowired
    private IUserService service;

    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户", required = true, dataType = "String"), @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")})
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseBean login(String username, String password, Model model) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new ResponseBean(6001, "username or password empty", null);
        }

        User user = this.service.selectOne(new EntityWrapper<User>().where("username={0}", username));
        if (user == null || !user.getPassword().equals(Md5.md5Encode(password))) {
            return new ResponseBean(6001, "username or password error", null);
        }
        String token;
        try {
            token = JWTUtil.sign(user.getId(), user.getUsername());
        } catch (LockedAccountException lae) {
            return new ResponseBean(6001, "user is locked,please contact manager", null);
        } catch (AuthenticationException e) {
            return new ResponseBean(6001, "username or password error", null);
        } catch (Exception err) {
            return new ResponseBean(6001, err.getMessage(), null);
        }
        return new ResponseBean(200, "login success", token);

    }
}