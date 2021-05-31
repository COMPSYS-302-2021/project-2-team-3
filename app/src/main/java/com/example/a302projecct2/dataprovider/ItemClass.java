package com.example.a302projecct2.dataprovider;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemClass implements Parcelable {

    String itemName, itemDescription, itemPrice;
    String[] itemImages;

    public ItemClass(String itemName, String itemDescription, String itemPrice, String[] itemImages) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemImages = itemImages;
    }

    protected ItemClass(Parcel in) {
        itemName = in.readString();
        itemDescription = in.readString();
        itemPrice = in.readString();
        itemImages = in.createStringArray();
    }



    public static final Creator<ItemClass> CREATOR = new Creator<ItemClass>() {
        @Override
        public ItemClass createFromParcel(Parcel in) {
            return new ItemClass(in);
        }

        @Override
        public ItemClass[] newArray(int size) {
            return new ItemClass[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(itemDescription);
        dest.writeString(itemPrice);
        dest.writeStringArray(itemImages);
    }
}
