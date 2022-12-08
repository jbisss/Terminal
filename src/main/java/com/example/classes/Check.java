package com.example.classes;

import java.util.ArrayList;

public class Check {
    public static ArrayList<Check> checksList = new ArrayList<>();
    private static int count = 0;
    private final int id;
    private String report;
    public Check(){
        count++;
        this.id = count;
    }
    public void makeReport(int statusCode) {
        StringBuilder result = new StringBuilder();
        result.append("#").append(this.id).append("\n");
        if (statusCode == 0) {
            result.append("The operation is successful!\nYour summary basket:\n");
            for (String product : ObjectsContainer.basket.cartMap.keySet()) {
                int cost = ObjectsContainer.basket.cartMap.get(product).getCost();
                int count = ObjectsContainer.basket.cartMap.get(product).getCount();
                result.append(ObjectsContainer.basket.cartMap.get(product).getName()).append(": ").append(cost).
                        append("*").append(count).append("=").append(cost*count).append("\n");
            }
            result.append("Summary: ").append(ObjectsContainer.basket.getTotalCost());
        }
        if (statusCode == 1) {
            result.append("The card number is incorrect!!!");
        }
        if (statusCode == 2) {
            result.append("The card validity is incorrect!!!");
        }
        if (statusCode == 3) {
            result.append("There is no enough money on card!!!");
        }
        if (statusCode == 4) {
            result.append("Incorrect password!!!");
        }
        result.append("\n\n");
        this.report = result.toString();
        checksList.add(this);
    }

    public String getReport() {
        return report;
    }
}
