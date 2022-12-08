package com.example.classes;

public class Card {
    private final int password;
    private final int cardNumber;
    private int money;
    private final String date;
    public Card(int password,int money, int cardNumber, String date){
        this.password = password;
        this.cardNumber = cardNumber;
        this.money = money;
        this.date = date;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
    public int getPassword() {
        return password;
    }
    public int getCardNumber() {
        return cardNumber;
    }
    public String getDate() {
        return date;
    }
}
