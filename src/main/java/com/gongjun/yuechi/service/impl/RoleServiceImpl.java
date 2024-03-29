package com.gongjun.yuechi.service.impl;

import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.model.Role;
import com.gongjun.yuechi.mapper.RoleMapper;
import com.gongjun.yuechi.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Permission> getPermissionsByRoleId(String id,Integer type) {
        return this.baseMapper.getPermissionsByRoleId(id,type);
    }
}
