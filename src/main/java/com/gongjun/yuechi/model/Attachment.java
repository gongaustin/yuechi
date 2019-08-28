package com.gongjun.yuechi.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@TableName("busi_attachment")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;
    /**
     * 附件名称
     */
    private String fileName;
    /**
     * 附件后缀名
     */
    private String ext;
    /**
     * 附件地址
     */
    private String url;
    /**
     * 创建时间
     */
    private Date ctime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Attachment{" +
        ", id=" + id +
        ", fileName=" + fileName +
        ", ext=" + ext +
        ", url=" + url +
        ", ctime=" + ctime +
        "}";
    }
}
