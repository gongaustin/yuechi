package com.gongjun.yuechi.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.UserBean;
import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.model.Role;
import com.gongjun.yuechi.model.User;
import com.baomidou.mybatisplus.service.IService;
import com.gongjun.yuechi.model.vo.UserVo;

import java.util.List;

/**
 * <p>
 * 用户人员 服务类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
public interface IUserService extends IService<User> {

    List<Permission> selectUserPermissionsByWrapper(Wrapper wrapper);

    UserBean getUser(String username);

    List<UserVo> selectUserVoPage(Page page,Wrapper wrapper);

    UserVo findUserById(String id);

    List<Role> getRolesByManagerId(String id);

}
