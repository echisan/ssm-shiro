package com.echisan.model.vo;

import com.echisan.model.po.Category;

public class UpdateGoodsCategoryVO {

    private Category category;
    private Boolean isSelect;

    public UpdateGoodsCategoryVO() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }
}
