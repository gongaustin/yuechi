package com.gongjun.yuechi.service.impl;

import com.gongjun.yuechi.model.UserRole;
import com.gongjun.yuechi.mapper.UserRoleMapper;
import com.gongjun.yuechi.service.IUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
