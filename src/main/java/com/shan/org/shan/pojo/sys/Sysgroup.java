package com.shan.org.shan.pojo.sys;

import java.io.Serializable;
import java.util.List;

public class Sysgroup implements Serializable {
	
	
    private Long gid;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 状态 0-停用 1-启用
     */
    private Long status;

    /**
     * 描述
     */
    private String remarks;
    
    /**
     * 用户集合
     */
    private List<Sysuser> sysusers;
    
    /**
     * 权限集合
     */
    private List<GroupPrivilege> groupPrivileges;
    
    //权限id集合
    private List<String> groupPrivilegeIds;
    
	public List<String> getGroupPrivilegeIds() {
		return groupPrivilegeIds;
	}

	public void setGroupPrivilegeIds(List<String> groupPrivilegeIds) {
		this.groupPrivilegeIds = groupPrivilegeIds;
	}

	public List<GroupPrivilege> getGroupPrivileges() {
		return groupPrivileges;
	}

	public void setGroupPrivileges(List<GroupPrivilege> groupPrivileges) {
		this.groupPrivileges = groupPrivileges;
	}

	public List<Sysuser> getSysusers() {
		return sysusers;
	}

	public void setSysusers(List<Sysuser> sysusers) {
		this.sysusers = sysusers;
	}

	private static final long serialVersionUID = 1L;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gid=").append(gid);
        sb.append(", groupName=").append(groupName);
        sb.append(", status=").append(status);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}
}