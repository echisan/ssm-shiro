package com.echisan.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private Long id;

    private String name;

    private BigDecimal price;

    private Date createTime;

    private Date lastChangeTime;

    private Long userId;

    private Long viewNumber;

    private Byte isSell;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(Date lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Long viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Byte getIsSell() {
        return isSell;
    }

    public void setIsSell(Byte isSell) {
        this.isSell = isSell;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createTime=" + createTime +
                ", lastChangeTime=" + lastChangeTime +
                ", userId=" + userId +
                ", viewNumber=" + viewNumber +
                ", isSell=" + isSell +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Goods)){
            return false;
        }
        Goods goods = (Goods) obj;
        return this.id.equals(goods.id);
    }
}