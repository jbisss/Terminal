package com.example.classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Product {
    private final String name;
    private final int cost;
    private int count;
    private final ImageView image;
    public Product(String name, String imagePath, int cost,int count){
        this.name = name;
        this.count = count;
        this.cost = cost;
        this.image = new ImageView(imagePath);
    }

    public void changeCount(int plusMinus, int changer) {
        this.count += plusMinus * changer;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName(){
        return name;
    }
    public int getCost(){
        return cost;
    }
    public Image getImage(){
        return image.getImage();
    }
    public int getCount() {
        return count;
    }
}
