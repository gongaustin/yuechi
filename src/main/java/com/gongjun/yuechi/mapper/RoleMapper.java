package com.gongjun.yuechi.mapper;

import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.model.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Permission> getPermissionsByRoleId(@Param("id") String id);
}
