package com.gongjun.yuechi.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 科室
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@TableName("upms_dept")
public class Dept implements Serializable {

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
     * 科室编号
     */
    private String deptNo;
    /**
     * 科室名称
     */
    private String deptName;
    /**
     * 科室负责人ID
     */
    private String headerId;
    /**
     * 科室负责人
     */
    private String header;
    /**
     * 科室描述
     */
    private String description;
    /**
     * 科室电话
     */
    private String phone;
    /**
     * 科室属性
     */
    private Integer nature;
    /**
     * 科室位置
     */
    private String addr;
    /**
     * 创建时间
     */
    private Date ctime;
    /**
     * 是否启用（0：停用；1：启用）
     */
    private Integer status;
    /**
     * 科室等级
     */
    private Integer degree;
    /**
     * 是否重点科室（0：不是；1：是）
     */
    private Integer isImportant;
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

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(Integer isImportant) {
        this.isImportant = isImportant;
    }

    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }

    @Override
    public String toString() {
        return "Dept{" +
        ", id=" + id +
        ", pId=" + pId +
        ", deptNo=" + deptNo +
        ", deptName=" + deptName +
        ", headerId=" + headerId +
        ", header=" + header +
        ", description=" + description +
        ", phone=" + phone +
        ", nature=" + nature +
        ", addr=" + addr +
        ", ctime=" + ctime +
        ", status=" + status +
        ", degree=" + degree +
        ", isImportant=" + isImportant +
        ", backup=" + backup +
        "}";
    }
}
