package com.pctc.entity;

import java.util.Date;

public class Card {
    private Integer id;

    private String payPwd;

    private Integer userNumber;

    private String status;

    private Double balance;

    private Date createTime;

    private Date updateTime;

    private Boolean deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd == null ? null : payPwd.trim();
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

	public Card(String payPwd, Integer userNumber, String status, Double balance, Date createTime,
			Date updateTime, Boolean deleteFlag) {
		this.payPwd = payPwd;
		this.userNumber = userNumber;
		this.status = status;
		this.balance = balance;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.deleteFlag = deleteFlag;
	}

	public Card() {
		
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", payPwd=" + payPwd + ", userNumber=" + userNumber + ", status=" + status
				+ ", balance=" + balance + ", createTime=" + createTime + ", updateTime=" + updateTime + ", deleteFlag="
				+ deleteFlag + "]";
	}
    
	
    
}