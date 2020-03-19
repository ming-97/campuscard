package com.pctc.entity;

import java.util.Date;

public class Role {
    private Integer rid;

    private String name;

    private String description;

    private Date createTime;

    private Date updateTime;

    private Boolean deleteFlag;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

	public Role(String name, String description, Date createTime, Date updateTime, Boolean deleteFlag) {

		this.name = name;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.deleteFlag = deleteFlag;
	}

	public Role() {

	}

	@Override
	public String toString() {
		return "Role [rid=" + rid + ", name=" + name + ", description=" + description + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", deleteFlag=" + deleteFlag + "]";
	}
    
	
    
}