package com.gongjun.yuechi.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 附件关系表
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@TableName("busi_attachment_relation")
public class AttachmentRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 附件ID
     */
    @TableField("attachment_id")
    private String attachmentId;
    /**
     * 所有者类型
     */
    @TableField("owner_type")
    private String ownerType;
    /**
     * 所有者ID
     */
    @TableField("owner_id")
    private String ownerId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "AttachmentRelation{" +
        ", id=" + id +
        ", attachmentId=" + attachmentId +
        ", ownerType=" + ownerType +
        ", ownerId=" + ownerId +
        "}";
    }
}
