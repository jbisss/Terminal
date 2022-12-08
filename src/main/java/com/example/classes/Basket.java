package com.example.classes;

import java.util.HashMap;

public class Basket {
    private int totalCost = 0;
    public final HashMap<String, Product> cartMap = new HashMap<>(20, (float) 0.8);
    public void changeTotalCost(int plusMinus, int changer) {
        totalCost += plusMinus*changer;
    }
    public int getTotalCost(){
        return this.totalCost;
    }
    public void resetTotalCost(){
        this.totalCost = 0;
    }
}
