package com.gongjun.yuechi.model.vo;

import com.gongjun.yuechi.model.News;

/**
 * @Description:
 * @Author:GongJun
 * @Date:2019/9/24
 */
public class NewsVo extends News {
    private String authorName;
    private String deptName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
