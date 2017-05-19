package com.shan.org.shan.pojo.sys;

import java.io.Serializable;
import java.util.Date;

public class Sysuser implements Serializable {
	private Long sysId;

	/**
	 * 登录名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 真实名字
	 */
	private String name;

	/**
	 * 创建人
	 */
	private String creater;

	/**
	 * 手机号码
	 */
	private Long phone;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 状态 0-未激活 1-已激活
	 */
	private Long status;

	/**
	 * 创建时间
	 */
	private Long createTime;
	private Date createDate;
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 修改时间
	 */
	private Long updateTime;

	/**
	 * 上次登录时间
	 */
	private Long lastloginTime;

	/**
	 * 群组表外键
	 */
	private Sysgroup sysgroupId;
	
	private Long gid;
	
	

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public Sysgroup getSysgroupId() {
		return sysgroupId;
	}

	public void setSysgroupId(Sysgroup sysgroupId) {
		this.sysgroupId = sysgroupId;
	}

	private static final long serialVersionUID = 1L;

	public Long getSysId() {
		return sysId;
	}

	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater == null ? null : creater.trim();
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getLastloginTime() {
		return lastloginTime;
	}

	public void setLastloginTime(Long lastloginTime) {
		this.lastloginTime = lastloginTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", sysId=").append(sysId);
		sb.append(", username=").append(username);
		sb.append(", password=").append(password);
		sb.append(", name=").append(name);
		sb.append(", creater=").append(creater);
		sb.append(", phone=").append(phone);
		sb.append(", remarks=").append(remarks);
		sb.append(", status=").append(status);
		sb.append(", createTime=").append(createTime);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", lastloginTime=").append(lastloginTime);
		sb.append(", sysgroupId=").append(sysgroupId);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}