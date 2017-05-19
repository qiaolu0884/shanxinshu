package com.shan.org.shan.pojo;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 善心汇返回的用户ID
     */
    private Long userId;

    /**
     * 登录名
     */
    private String username;

    /**
     * 真实名字
     */
    private String name;

    /**
     * 状态：0-未激活 1-已激活 2-已冻结
     */
    private Long status;

    /**
     * 会员级别
     */
    private String userGrade;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * token令牌
     */
    private String token;
    
    /**
     * 善心树
     */
    private Tree tree;
    
	private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade == null ? null : userGrade.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
    
    public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", userGrade=").append(userGrade);
        sb.append(", createTime=").append(createTime);
        sb.append(", token=").append(token);
        sb.append(", tree=").append(tree.toString());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}