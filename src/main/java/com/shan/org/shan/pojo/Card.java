package com.shan.org.shan.pojo;

import java.io.Serializable;

public class Card implements Serializable {
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 银行卡号
     */
    private Long card;

    /**
     * 电话
     */
    private Long phone;

    /**
     * 身份证信息
     */
    private Long identificationCard;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 发卡银行
     */
    private String bank;

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

    public Long getCard() {
        return card;
    }

    public void setCard(Long card) {
        this.card = card;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(Long identificationCard) {
        this.identificationCard = identificationCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", card=").append(card);
        sb.append(", phone=").append(phone);
        sb.append(", identificationCard=").append(identificationCard);
        sb.append(", name=").append(name);
        sb.append(", createTime=").append(createTime);
        sb.append(", bank=").append(bank);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}