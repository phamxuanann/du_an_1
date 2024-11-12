package com.example.abc_fpt.modal;

public class Onboadingitem {
    private  int image;
    private String title;
    private String description;

    public Onboadingitem(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }
    public Onboadingitem(){

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}