package com.example.lab5;

public class Breed {
    private String breed;
    private String id;

    public Breed() {

    }
    public Breed(String breed, String id) {
        this.breed = breed;
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
