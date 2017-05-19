package com.shan.org.shan.pojo.sys;

import java.io.Serializable;

public class SysuserGroup implements Serializable {
    /**
     * 客服id
     */
    private Long sysuserId;

    /**
     * 分组id
     */
    private Long sysgroupId;

    /**
     * 状态：0-不可用，1-可用
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    public Long getSysuserId() {
        return sysuserId;
    }

    public void setSysuserId(Long sysuserId) {
        this.sysuserId = sysuserId;
    }

    public Long getSysgroupId() {
        return sysgroupId;
    }

    public void setSysgroupId(Long sysgroupId) {
        this.sysgroupId = sysgroupId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sysuserId=").append(sysuserId);
        sb.append(", sysgroupId=").append(sysgroupId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}