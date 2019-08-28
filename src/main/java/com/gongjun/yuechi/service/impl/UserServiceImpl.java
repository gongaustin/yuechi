package com.gongjun.yuechi.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gongjun.yuechi.core.bean.UserBean;
import com.gongjun.yuechi.mapper.UserMapper;
import com.gongjun.yuechi.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description:
 * @Author:GongJun
 * @Date:2019/1/22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserBean> implements IUserService {
    @Override
    public UserBean getUser(String username) {
        UserBean user = new UserBean();
        Map<String, String> detail = this.baseMapper.getData(username);
        user.setUsername(username);
        user.setPassword(detail.get("password"));
        user.setRole(detail.get("role"));
        user.setPermisision(detail.get("permission"));
        return user;
    }



}
