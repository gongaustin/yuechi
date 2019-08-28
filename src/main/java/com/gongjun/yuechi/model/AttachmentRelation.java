package com.gongjun.yuechi.model;

import com.baomidou.mybatisplus.annotations.TableName;
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
    private String id;
    /**
     * 附件ID
     */
    private String attachmentId;
    /**
     * 所有者类型
     */
    private String ownerType;
    /**
     * 所有者ID
     */
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
