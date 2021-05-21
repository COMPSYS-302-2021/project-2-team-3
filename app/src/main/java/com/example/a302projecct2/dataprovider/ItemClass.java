package com.example.a302projecct2.dataprovider;

public class ItemClass {

    String itemName, itemDescription, itemPrice;
    String[] itemImages;

    public ItemClass(String itemName, String itemDescription, String itemPrice, String[] itemImages) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemImages = itemImages;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String[] getItemImages() {
        return itemImages;
    }
}
