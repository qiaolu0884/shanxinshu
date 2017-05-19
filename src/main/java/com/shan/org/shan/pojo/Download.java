package com.shan.org.shan.pojo;

import java.io.Serializable;

public class Download implements Serializable {
    private Long id;

    /**
     * 后台用户ID
     */
    private Long sysuserId;
    
    private String username;
    
    private String message;

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
     * 导出记录
     */
    private String status;

    /**
     * 导出的条数
     */
    private Integer count;

    /**
     * 导出时间
     */
    private String createTime;

    private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSysuserId() {
		return sysuserId;
	}

	public void setSysuserId(Long sysuserId) {
		this.sysuserId = sysuserId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Download [id=" + id + ", sysuserId=" + sysuserId + ", status=" + status + ", count=" + count
				+ ", createTime=" + createTime + "]";
	}

   

  


}