package com.gongjun.yuechi.service.impl;

import com.gongjun.yuechi.core.bean.UserBean;
import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.mapper.UserMapper;
import com.gongjun.yuechi.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户人员 服务实现类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<Permission> selectUserPermissionsById(String userid) {
        return userid==null?null:this.baseMapper.selectUserPermissionsById(userid);
    }

    @Override
    public UserBean getUser(String username) {
        UserBean ub = new UserBean();
        return null;
    }
}
