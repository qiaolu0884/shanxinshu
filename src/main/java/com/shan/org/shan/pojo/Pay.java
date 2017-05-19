package com.shan.org.shan.pojo;

import java.io.Serializable;

public class Pay implements Serializable {
    /**
     * 支付信息主键ID
     */
    private Long id;

    /**
     * 支付流水号
     */
    private Long payNumber;

    /**
     * 支付时间
     */
    private Long createTime;

    /**
     * 订单id，支付哪一张订单，金额从订单获取
     */
    private Long orderId;

    /**
     * 银行卡信息
     */
    private Long cartId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(Long payNumber) {
        this.payNumber = payNumber;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", payNumber=").append(payNumber);
        sb.append(", createTime=").append(createTime);
        sb.append(", orderId=").append(orderId);
        sb.append(", cartId=").append(cartId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}