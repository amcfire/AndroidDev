package com.example.amcfire.wallmartlab;

public class electronic {
    //food basics
    private String id;
    private String title;
    private String color;
    private String description;
    private String image;
    private Double price;

    public electronic(String id, String title, String color, String description, String image, Double price) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
