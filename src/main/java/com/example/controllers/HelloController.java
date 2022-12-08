package com.example.controllers;

import com.example.classes.Check;
import com.example.classes.ObjectsContainer;
import com.example.classes.Product;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloController {
    public VBox productShopVBox;
    public VBox productBasketVBox;
    public ImageView productImage;
    public TextField fieldCost;
    public TextField fieldCount;
    public TextField fieldInputCountAdd;
    public TextField totalShopCost;
    public Button addToCartButton;
    public TextField fieldName;
    public TextField fieldNameBasket;
    public TextField fieldCountBasket;
    public TextField fieldInputCountDelete;
    public Button deleteCartButton;
    public TextField fieldTotalBasketCost;
    public TextField fieldCostBasket;
    public TextField fieldCardNumber;
    public Button approveButton;
    public PasswordField fieldPin;
    public TextField fieldDate;
    public TextArea checkList;
    public ScrollPane basketScroll;
    public AnchorPane mainField;
    public Button clearButton;

    public void initialize(){
        fieldInputCountAdd.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        if(!fieldInputCountAdd.getText().equals("") && Integer.parseInt(fieldInputCountAdd.getText()) >= 0){
                            if(Integer.parseInt(fieldCount.getText()) >= Integer.parseInt(fieldInputCountAdd.getText())) {
                                totalShopCost.setText(Integer.toString(Integer.parseInt(newValue)
                                        * Integer.parseInt(fieldCost.getText())));
                                addToCartButton.setDisable(false);
                            } else {
                                totalShopCost.setText("Too much");
                                addToCartButton.setDisable(true);
                            }
                        } else {
                            addToCartButton.setDisable(true);
                            totalShopCost.setText("");
                        }
                    } catch (Exception exception) {
                        System.out.println("Invalid input!");
                    }
                });
        fieldInputCountDelete.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        if(!fieldInputCountDelete.getText().equals("") && Integer.parseInt(fieldInputCountDelete.getText()) >= 0){
                            if(Integer.parseInt(fieldCountBasket.getText()) >= Integer.parseInt(fieldInputCountDelete.getText())) {
                                fieldTotalBasketCost.setText(Integer.toString(ObjectsContainer.basket.getTotalCost() - Integer.parseInt(newValue)
                                        * ObjectsContainer.basket.cartMap.get(fieldNameBasket.getText()).getCost()));
                                deleteCartButton.setDisable(false);
                            } else {
                                fieldTotalBasketCost.setText("Too much");
                                deleteCartButton.setDisable(true);
                            }
                        } else {
                            deleteCartButton.setDisable(true);
                            fieldTotalBasketCost.setText(Integer.toString(ObjectsContainer.basket.getTotalCost()));
                        }
                    } catch (Exception exception) {
                        System.out.println("Invalid input!");
                    }
                });
        ArrayList<Button> buttons = new ArrayList<>();
        for(String product : ObjectsContainer.shopMap.keySet()){
            Button newButton = new Button(ObjectsContainer.shopMap.get(product).getName());
            newButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                productImage.setImage(ObjectsContainer.shopMap.get(product).getImage());
                fieldCost.setText(Integer.toString(ObjectsContainer.shopMap.get(product).getCost()));
                fieldCount.setText(Integer.toString(ObjectsContainer.shopMap.get(product).getCount()));
                fieldName.setText(ObjectsContainer.shopMap.get(product).getName());
            });
            newButton.setPrefWidth(productShopVBox.getPrefWidth());
            buttons.add(newButton);
        }
        productShopVBox.getChildren().addAll(buttons);
    }

    public void addToCart(){
        ObjectsContainer.basket.changeTotalCost(1, Integer.parseInt(fieldInputCountAdd.getText()) *
                Integer.parseInt(fieldCost.getText()));
        fieldTotalBasketCost.setText(Integer.toString(ObjectsContainer.basket.getTotalCost()));
        if (ObjectsContainer.basket.cartMap.containsKey(fieldName.getText())){
            ObjectsContainer.basket.cartMap.get(fieldName.getText()).changeCount(1,
                    Integer.parseInt(fieldInputCountAdd.getText()));
        } else {
            ObjectsContainer.basket.cartMap.put(fieldName.getText(), new Product(fieldName.getText(), "apples.jpg",
                    Integer.parseInt(fieldCost.getText()), Integer.parseInt(fieldInputCountAdd.getText())));
            Button newButton = new Button(ObjectsContainer.shopMap.get(fieldName.getText()).getName());
            newButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                fieldNameBasket.setText(ObjectsContainer.basket.cartMap.get(newButton.getText()).getName());
                fieldCountBasket.setText(Integer.toString(ObjectsContainer.basket.cartMap.get(newButton.getText()).getCount()));
                fieldCostBasket.setText(Integer.toString(ObjectsContainer.basket.cartMap.get(newButton.getText()).getCost()));
                ObjectsContainer.deleteCandidate = newButton;
            });
            newButton.setPrefWidth(productBasketVBox.getPrefWidth());
            productBasketVBox.getChildren().add(newButton);
        }
        ObjectsContainer.shopMap.get(fieldName.getText()).changeCount(-1,
                Integer.parseInt(fieldInputCountAdd.getText()));
        fieldCount.setText(Integer.toString(ObjectsContainer.shopMap.get(fieldName.getText()).getCount()));
        fieldInputCountAdd.setText("");
    }

    public void deleteFromCart(){
        ObjectsContainer.basket.cartMap.get(fieldNameBasket.getText()).changeCount(-1,
                Integer.parseInt(fieldInputCountDelete.getText()));
        ObjectsContainer.basket.changeTotalCost(-1, Integer.parseInt(fieldInputCountDelete.getText()) *
                ObjectsContainer.basket.cartMap.get(fieldNameBasket.getText()).getCost());
        ObjectsContainer.shopMap.get(fieldNameBasket.getText()).changeCount(1,
                Integer.parseInt(fieldInputCountDelete.getText()));
        fieldCount.setText(Integer.toString(ObjectsContainer.shopMap.get(fieldNameBasket.getText()).getCount()));
        int countBasket = Integer.parseInt(fieldCountBasket.getText());
        fieldCountBasket.setText(Integer.toString(ObjectsContainer.basket.cartMap.get(fieldNameBasket.getText()).getCount()));
        try {
            if (Integer.parseInt(fieldInputCountDelete.getText()) == countBasket) {
                productBasketVBox.getChildren().remove(ObjectsContainer.deleteCandidate);
                ObjectsContainer.basket.cartMap.remove(fieldNameBasket.getText());
            }
        } catch (ClassCastException exception) {
            System.out.println("Error!");
        }
        fieldInputCountDelete.setText("");
    }

    public void approveCard(){
        int cardNumber = 0;
        int cardPassword = 0;
        int moneyRequired = ObjectsContainer.basket.getTotalCost();
        try {
            cardNumber = Integer.parseInt(fieldCardNumber.getText());
            cardPassword = Integer.parseInt(fieldPin.getText());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        String cardDate = fieldDate.getText();
        if (ObjectsContainer.card.getCardNumber() == cardNumber && ObjectsContainer.card.getPassword() == cardPassword
                && ObjectsContainer.card.getMoney() >= moneyRequired && ObjectsContainer.card.getDate().equals(cardDate)) {
            Check check = new Check();
            check.makeReport(0);
            ObjectsContainer.card.setMoney(ObjectsContainer.card.getMoney() - moneyRequired);
            ObjectsContainer.basket.resetTotalCost();
        } else {
            if (ObjectsContainer.card.getCardNumber() != cardNumber) {
                Check check = new Check();
                check.makeReport(1);
            }
            if (!ObjectsContainer.card.getDate().equals(cardDate)) {
                Check check = new Check();
                check.makeReport(2);
            }
            if (ObjectsContainer.card.getMoney() < moneyRequired) {
                Check check = new Check();
                check.makeReport(3);
            }
            if (ObjectsContainer.card.getPassword() != cardPassword) {
                Check check = new Check();
                check.makeReport(4);
            }
        }
        fieldPin.setText("");
        fieldDate.setText("");
        fieldCardNumber.setText("");
        fieldTotalBasketCost.setText("");
        fieldNameBasket.setText("");
        fieldCountBasket.setText("");
        fieldCostBasket.setText("");
        for (String product : ObjectsContainer.basket.cartMap.keySet()) {
            ObjectsContainer.basket.cartMap.get(product).setCount(0);
        }
        ObjectsContainer.basket.cartMap.clear();
        StringBuilder entireReport = new StringBuilder();
        for (Check report : Check.checksList) {
            entireReport.append(report.getReport());
        }
        checkList.setText(entireReport.toString());
        productBasketVBox.getChildren().clear();
    }

    public void clearCheck(){
        checkList.setText("");
        Stage stage = (Stage) clearButton.getScene().getWindow();
        stage.close();
    }
}