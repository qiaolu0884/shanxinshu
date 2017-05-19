package com.shan.org.shan.pojo.sys;

import java.io.Serializable;

public class GroupPrivilege implements Serializable {
    /**
     * 权限id
     */
    private Long sysprivilegeId;

    /**
     * 分组id
     */
    private Long sysgroupId;

    private static final long serialVersionUID = 1L;

    public Long getSysprivilegeId() {
        return sysprivilegeId;
    }

    public void setSysprivilegeId(Long sysprivilegeId) {
        this.sysprivilegeId = sysprivilegeId;
    }

    public Long getSysgroupId() {
        return sysgroupId;
    }

    public void setSysgroupId(Long sysgroupId) {
        this.sysgroupId = sysgroupId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sysprivilegeId=").append(sysprivilegeId);
        sb.append(", sysgroupId=").append(sysgroupId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}