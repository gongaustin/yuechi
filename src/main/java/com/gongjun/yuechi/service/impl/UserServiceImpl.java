package com.gongjun.yuechi.service.impl;

import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.mapper.UserMapper;
import com.gongjun.yuechi.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
