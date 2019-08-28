package com.gongjun.yuechi.service.impl;

import com.gongjun.yuechi.model.UserPermission;
import com.gongjun.yuechi.mapper.UserPermissionMapper;
import com.gongjun.yuechi.service.IUserPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户权限关联表 服务实现类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements IUserPermissionService {

}
