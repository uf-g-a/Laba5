package com.example.lab5;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Cats {

    private ArrayList<String> imageURLs;
    private ArrayList<Bitmap> image;
    private ArrayList<Breed> breeds;
    private Breed bread;


    private static Cats instance;

    private Cats(){}

    public static Cats getInstance() {
        if (instance == null) {
            instance = new Cats();
            instance.imageURLs = new ArrayList<>();
            instance.image = new ArrayList<>();
            instance.breeds = new ArrayList<>();
            instance.bread = new Breed("Порода", "0");
            instance.breeds.add(instance.bread);
        }
        return instance;
    }

    public ArrayList<String> getImageURLs() {
        return imageURLs;
    }

    public ArrayList<Bitmap> getImage() {
        return image;
    }

    public ArrayList<Breed> getBreeds() {
        return breeds;
    }

    public Breed getBread() {
        return bread;
    }

    public void setBread(Breed bread) {
        this.bread = bread;
    }
}
