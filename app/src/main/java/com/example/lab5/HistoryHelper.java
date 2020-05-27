package com.example.lab5;

import android.graphics.Bitmap;

public class HistoryHelper {
    private int like;
    private Bitmap image;

    public HistoryHelper(int like, Bitmap image) {
        this.like = like;
        this.image = image;
    }

    public int getLike() {
        return like;
    }

    public Bitmap getImage() {
        return image;
    }
}
