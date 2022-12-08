package com.example.classes;

import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.Random;

public class ObjectsContainer {
    public static HashMap<String, Product> shopMap = new HashMap<>(20, (float) 0.8);
    public static Button deleteCandidate;
    public static Basket basket = new Basket();
    public static Card card = new Card(1234, 10000,512, "12.12.2017");
    static {
        Random randCost = new Random();
        Random randCount = new Random();
        shopMap.put("Apples", new Product("Apples", "apples.jpg",
                randCost.nextInt(25), randCount.nextInt(100)));
        shopMap.put("Avocado", new Product("Avocado", "avocado.jpg",
                randCost.nextInt(60), randCount.nextInt(100)));
        shopMap.put("Bananas", new Product("Bananas", "bananas.jpg",
                randCost.nextInt(35), randCount.nextInt(100)));
        shopMap.put("Cheese", new Product("Cheese", "cheese.jpg",
                randCost.nextInt(150), randCount.nextInt(100)));
        shopMap.put("Cocoon", new Product("Cocoon", "cocoon.jpg",
                randCost.nextInt(50), randCount.nextInt(100)));
        shopMap.put("Energos", new Product("Energos", "energos.jpg",
                randCost.nextInt(200), randCount.nextInt(100)));
        shopMap.put("Lemon", new Product("Lemon", "lemon.jpg",
                randCost.nextInt(80), randCount.nextInt(100)));
        shopMap.put("Oranges", new Product("Oranges", "oranges.jpg",
                randCost.nextInt(45), randCount.nextInt(100)));
        shopMap.put("Vegetables", new Product("Vegetables", "pack_1.jpg",
                randCost.nextInt(200), randCount.nextInt(100)));
        shopMap.put("Mushrooms", new Product("Mushrooms", "pack_2.jpg",
                randCost.nextInt(200), randCount.nextInt(100)));
        shopMap.put("RedBull", new Product("RedBull", "redbull.jpg",
                randCost.nextInt(180), randCount.nextInt(100)));
        shopMap.put("Tornado", new Product("Tornado", "tornado.jpg",
                randCost.nextInt(120), randCount.nextInt(100)));
    }
}
