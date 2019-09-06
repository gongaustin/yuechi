package com.gongjun.yuechi.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.model.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gongjun.yuechi.model.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户人员 Mapper 接口
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Permission> selectUserPermissionsByWrapper(@Param("ew") Wrapper wrapper);

    List<UserVo> selectUserVoPage(Page page, @Param("ew") Wrapper wrapper);

    UserVo findUserById(@Param("id") String id);


}
