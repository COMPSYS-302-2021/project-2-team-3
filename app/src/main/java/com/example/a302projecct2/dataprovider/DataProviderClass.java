package com.example.a302projecct2.dataprovider;

public class DataProviderClass {

    CategoryClass[] categories;
    ItemClass[][] allItems;

    public DataProviderClass() {
        //TODO: Assign values and create dataset for both categories and items
        categories = new CategoryClass[]{
                new CategoryClass("Japanese", "https://cdn.icon-icons.com/icons2/230/PNG/256/Japan_JP_JPN_392_Flag1_26102.png"),
                new CategoryClass("Indian", "https://cdn.icon-icons.com/icons2/2087/PNG/512/india_icon_127891.png"),
                new CategoryClass("Italian", "https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/256/Italy-Flag-icon.png")
        };
    }
}
