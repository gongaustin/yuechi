package com.gongjun.yuechi.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gongjun.yuechi.core.bean.UserBean;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Description:
 * @Author:GongJun
 * @Date:2019/1/22
 */
@Repository
public interface UserMapper extends BaseMapper<UserBean> {
    Map<String,String> getData(String username);
}
