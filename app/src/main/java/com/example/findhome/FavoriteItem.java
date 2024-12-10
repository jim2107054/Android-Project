package com.example.findhome;

public class FavoriteItem {
    private int imageResId;
    private String details;

    public FavoriteItem(int imageResId, String details) {
        this.imageResId = imageResId;
        this.details = details;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDetails() {
        return details;
    }
}
