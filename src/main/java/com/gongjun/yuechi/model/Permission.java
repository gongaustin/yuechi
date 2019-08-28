package com.gongjun.yuechi.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@TableName("upms_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;
    /**
     * 所属上级
     */
    private String pId;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型(1:目录,2:菜单,3:按钮)
     */
    private Integer type;
    /**
     * 权限值
     */
    private String permissionValue;
    /**
     * 路径
     */
    private String uri;
    /**
     * 图标
     */
    private String icon;
    /**
     * 状态(0:禁止,1:正常)
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date ctime;
    /**
     * 排序
     */
    private Integer orders;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Permission{" +
        ", id=" + id +
        ", pId=" + pId +
        ", name=" + name +
        ", type=" + type +
        ", permissionValue=" + permissionValue +
        ", uri=" + uri +
        ", icon=" + icon +
        ", status=" + status +
        ", ctime=" + ctime +
        ", orders=" + orders +
        "}";
    }
}
