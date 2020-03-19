package com.pctc.entity;

import java.util.Date;

public class User {
    private Integer uid;

    private String username;

    private Integer userNumber;

    private String sex;

    private String idNumber;

    private String password;

    private String email;

    private Date createTime;

    private Date updateTime;

    private Boolean deleteFlag;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

	public User(String username, Integer userNumber, String sex, String idNumber, String password, String email,
			Date createTime, Date updateTime, Boolean deleteFlag) {

		this.username = username;
		this.userNumber = userNumber;
		this.sex = sex;
		this.idNumber = idNumber;
		this.password = password;
		this.email = email;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.deleteFlag = deleteFlag;
	}

	public User() {

	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", userNumber=" + userNumber + ", sex=" + sex
				+ ", idNumber=" + idNumber + ", password=" + password + ", email=" + email + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", deleteFlag=" + deleteFlag + "]";
	}
    
    
}