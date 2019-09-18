package com.gongjun.yuechi.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gongjun.yuechi.core.constant.TimeConstant;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 领导人表
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@TableName("busi_leader")
public class Leader implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 职务
     */
    private String position;
    /**
     * 联系电话
     */
    private String cellphone;
    /**
     * 负责内容
     */
    @TableField("res_content")
    private String resContent;

    /**
     * 证件头像(attachment表的ID)
     */
    private String photo;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = TimeConstant.TIME_ZONE, pattern = TimeConstant.DATETIME_FORMAT)
    @DateTimeFormat(pattern = TimeConstant.DATETIME_FORMAT)
    private Date ctime;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 备用字段
     */
    private String backup;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getResContent() {
        return resContent;
    }

    public void setResContent(String resContent) {
        this.resContent = resContent;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }

    @Override
    public String toString() {
        return "Leader{" +
        ", id=" + id +
        ", name=" + name +
        ", position=" + position +
        ", cellphone=" + cellphone +
        ", resContent=" + resContent +
        ", photo=" + photo +
        ", ctime=" + ctime +
        ", order=" + order +
        ", backup=" + backup +
        "}";
    }
}
