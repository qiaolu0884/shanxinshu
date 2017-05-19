package com.shan.org.shan.pojo;

import java.io.Serializable;

public class Tree implements Serializable {
    private Long id;

    /**
     * 会员id
     */
    private Long userId;

    /**
     * 能量值
     */
    private Long energy;

    /**
     * 善心树龄 0-12 代表0个月，一个月，半年，一年。。。
     */
    private Long age;

    /**
     * 创建树的时间
     */
    private Long createTime;

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

    public Long getEnergy() {
        return energy;
    }

    public void setEnergy(Long energy) {
        this.energy = energy;
    }

    public String getAge() {
    	if(age == 1){
    		return "0个月";
    	} else if(age == 2){
    		return "一个月";
    	} else if(age == 3){
    		return "半年";
    	} else if(age == 4){
    		return "一年";
    	} else if(age == 5){
    		return "两年";
    	} else if(age == 6){
    		return "三年";
    	} else if(age == 7){
    		return "四年";
    	} else if(age == 8){
    		return "五年";
    	} else if(age == 9){
    		return "六年";
    	} else if(age == 10){
    		return "七年";
    	} else if(age == 11){
    		return "八年";
    	} else if(age == 12){
    		return "九年";
    	} else if(age == 13){
    		return "十年";
    	} else {
    		return "十年以上";
    	} 
    }

    public void setAge() {
        if(energy == 0){
        	this.age = 0l;
        } else if(energy > 0 && energy <= 80){
        	this.age = 1l;
        } else if(energy > 80 && energy <= 500){
        	this.age = 2l;
        } else if(energy > 500 && energy <= 1200){
        	this.age = 3l;
        } else if(energy > 1200 && energy <= 3000){
        	this.age = 4l;
        } else if(energy > 3000 && energy <= 5000){
        	this.age = 5l;
        } else if(energy > 5000 && energy <= 8000){
        	this.age = 6l;
        } else if(energy > 8000 && energy <= 12000){
        	this.age = 7l;
        } else if(energy > 12000 && energy <= 18000){
        	this.age = 8l;
        } else if(energy > 18000 && energy <= 25000){
        	this.age = 9l;
        } else if(energy > 25000 && energy <= 40000){
        	this.age = 10l;
        } else if(energy > 40000 && energy <= 60000){
        	this.age = 11l;
        } else if(energy > 60000 && energy <= 10000){
        	this.age = 12l;
        } else {
        	this.age = 13l;//十年以上
        }
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", energy=").append(energy);
        sb.append(", age=").append(age);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}