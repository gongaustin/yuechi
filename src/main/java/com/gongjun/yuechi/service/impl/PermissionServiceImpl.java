package com.gongjun.yuechi.service.impl;

import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.mapper.PermissionMapper;
import com.gongjun.yuechi.service.IPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
