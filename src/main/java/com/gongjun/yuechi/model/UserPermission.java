package com.gongjun.yuechi.model;

import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户权限关联表
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@TableName("upms_user_permission")
public class UserPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 权限编号
     */
    private String permissionId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "UserPermission{" +
        ", id=" + id +
        ", userId=" + userId +
        ", permissionId=" + permissionId +
        "}";
    }
}
