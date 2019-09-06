package com.gongjun.yuechi.model.vo;

import com.gongjun.yuechi.model.User;

/**
 * @Description:
 * @Author:GongJun
 * @Date:2019/9/6
 */
public class UserVo extends User {
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
