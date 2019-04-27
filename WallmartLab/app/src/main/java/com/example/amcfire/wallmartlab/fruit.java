package com.example.amcfire.wallmartlab;

public class fruit {
    //food basics
    private int id;
    private String FoodName;
    private String FoodUnit;
    private String description;
    private String image;
    private Double price;

    public fruit(int id, String foodName, String foodUnit, String description, String image, Double price) {
        this.id = id;
        FoodName = foodName;
        FoodUnit = foodUnit;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodUnit() {
        return FoodUnit;
    }

    public void setFoodUnit(String foodUnit) {
        FoodUnit = foodUnit;
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
