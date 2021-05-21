package com.example.a302projecct2.dataprovider;

public class CategoryClass {

    private String categoryName, imgFlag;

    public String getCategoryName() {
        return categoryName;
    }

    public String getImgFlag() {
        return imgFlag;
    }

    public CategoryClass(String categoryName, String imgFlag) {
        this.categoryName = categoryName;
        this.imgFlag = imgFlag;
    }
}
