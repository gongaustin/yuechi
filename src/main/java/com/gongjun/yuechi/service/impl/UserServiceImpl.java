package com.gongjun.yuechi.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.UserBean;
import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.model.Role;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.mapper.UserMapper;
import com.gongjun.yuechi.model.vo.UserVo;
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
    public List<Permission> selectUserPermissionsByWrapper(Wrapper wrapper) {
        return this.baseMapper.selectUserPermissionsByWrapper(wrapper);
    }

    @Override
    public UserBean getUser(String username) {
        UserBean ub = new UserBean();
        return null;
    }

    @Override
    public List<UserVo> selectUserVoPage(Page page, Wrapper wrapper) {
        return this.baseMapper.selectUserVoPage(page,wrapper);
    }

    @Override
    public UserVo findUserById(String id) {
        return this.baseMapper.findUserById(id);
    }

    @Override
    public List<Role> getRolesByManagerId(String id) {
        return this.baseMapper.getRolesByManagerId(id);
    }

    @Override
    public List<Permission> selectUserModulessByWrapper(String id) {
        return this.baseMapper.selectUserModulessByWrapper(id);
    }
}
