package com.echisan.model.bo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author E73AN
 * 一个物品的包装类
 * 包含商品的自身的信息以及分类信息
 */
public class GoodsBO {

    private String name;
    private BigDecimal price;
    private String description;
    private List<Short> categoryIds;

    public GoodsBO() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Short> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Short> categoryIds) {
        this.categoryIds = categoryIds;
    }

    @Override
    public String toString() {
        return "GoodsBO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", categoryIds=" + categoryIds +
                '}';
    }
}
