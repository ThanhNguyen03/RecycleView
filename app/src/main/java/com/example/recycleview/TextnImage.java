package com.example.recycleview;

public class TextnImage {
    private String name;
    private String imageID;
    public TextnImage(){}
    public TextnImage(String name,String imageID){
        this.name = name;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
}
