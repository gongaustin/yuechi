package com.gongjun.tools.service;

import com.baomidou.mybatisplus.service.IService;
import com.gongjun.tools.core.bean.UserBean;

/**
 * @Description:
 * @Author:GongJun
 * @Date:2019/1/22
 */
public interface IUserService extends IService<UserBean> {
    UserBean getUser(String username);
}
