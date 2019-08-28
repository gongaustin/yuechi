package com.gongjun.yuechi.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

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

    @Override
    public String toString() {
        return "Leader{" +
        ", id=" + id +
        ", name=" + name +
        ", position=" + position +
        ", cellphone=" + cellphone +
        ", resContent=" + resContent +
        "}";
    }
}
