package com.echisan.model.bo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author E73AN
 */
public class GoodsJsonBO {

    private String name;
    private BigDecimal price;
    private String description;
    private List<String> imageUrls;

    public GoodsJsonBO() {
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public String toString() {
        return "GoodsJsonBO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imageUrls=" + imageUrls +
                '}';
    }
}
