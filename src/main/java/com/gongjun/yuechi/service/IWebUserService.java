package com.gongjun.yuechi.service;

import com.baomidou.mybatisplus.service.IService;
import com.gongjun.yuechi.core.bean.UserBean;

/**
 * @Description:
 * @Author:GongJun
 * @Date:2019/1/22
 */
public interface IWebUserService extends IService<UserBean> {
    UserBean getUser(String username);
}
