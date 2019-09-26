package com.gongjun.yuechi.service;

import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.model.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
public interface IRoleService extends IService<Role> {

    List<Permission> getPermissionsByRoleId(String id,Integer type);

}
