package com.echisan.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class WalletRecord {

    private Long id;

    private Long userId;

    private Byte behaviourType;

    private BigDecimal money;

    private Date createTime;

    private String remarks;

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

    public Byte getBehaviourType() {
        return behaviourType;
    }

    public void setBehaviourType(Byte behaviourType) {
        this.behaviourType = behaviourType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}