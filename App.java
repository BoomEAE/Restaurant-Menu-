package com.mycompany.restaurantmenu;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage mainStage) {
        //
        String[][] itemsAndPrices = { 
                     /*Beverage*/     {"Coffee", "2.50"},
                                      {"Tea", "2.00"},
                                      {"Soft Drink", "1.75"},
                                      {"Water", "2.95"},
                                      {"Milk", "1.5"},
                                      {"Juice", "2.5"},
                     /*Appetizer*/    {"Soup", "4.50"},
                                      {"Salad", "3.75"},
                                      {"Spring Rolls", "5.25"},
                                      {"Garlic Bread", "3.00"},
                                      {"Chips and Salsa", "6.95"},
                     /*Main Course*/  {"Steak", "15.00"},
                                      {"Grilled Chicken", "13.50"},
                                      {"Chicken Alfredo", "13.95"},
                                      {"Turkey Club", "11.90"},
                                      {"Shrimp Scampi", "18.99"},
                                      {"Pasta", "11.75"},
                                      {"Fish and Chips", "12.25"},
                     /*Dessert*/      {"Apple Pie", "5.95"},
                                      {"Carrot Cake", "4.50"},
                                      {"Mud Pie", "4.75"},
                                      {"Pudding", "3.25"},
                                      {"Apple Crisp", "5.98"}};
        
        //
        BorderPane root = new BorderPane();
        GridPane optionsPane = new GridPane();
        GridPane gridPane = new GridPane();
        HBox hbox = new HBox();
        
        //
        ComboBox<String> cb1 = new ComboBox();
        cb1.getItems().addAll("Coffee", "Tea", "Soft Drink", "Water", "Juice");
        ComboBox<String> cb2 = new ComboBox();
        cb2.getItems().addAll("Soup", "Salad", "Spring Rolls", "Garlic Bread", "Chips and Salsa");
        ComboBox<String> cb3 = new ComboBox();
        cb3.getItems().addAll("Steak", "Grilled Chicken", "Chicken Alfredo", "Turkey Club", "Shrimp Scampi", "Pasta", "Fish and Chips");
        ComboBox<String> cb4 = new ComboBox();
        cb4.getItems().addAll("Apple Pie", "Carrot Cake", "Mud Pie", "Pudding", "Apple Crisp");
        
        //
        Label label = new Label();
        Label beverageLabel = new Label("Beverage: ");
        Label appetizerLabel = new Label("Appetizer: ");
        Label mainCourseLabel = new Label("Main Course: ");
        Label dessertLabel = new Label("Dessert: ");
        Label tipSelected = new Label("Tips: 0%");
        Label subtotalLabel = new Label("Subtotal: 0");
        Label taxLabel = new Label("Tax: 0");
        Label tipsLabel = new Label("Tips: 0");
        Label totalLabel = new Label("Total: 0");
        
        //
        Slider slider = new Slider(0.0, 20.0, 0.0);
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(15.0);
        slider.setMajorTickUnit(10.0);
        slider.setMajorTickUnit(5.0);
        slider.setMinorTickCount(5);
        slider.setShowTickLabels(true);
        
        //
        Button calculateBtn = new Button("Calculate Bill");
        Button clearBtn = new Button("Clear Bill");
        
        //
        hbox.getChildren().addAll(calculateBtn, clearBtn);
        optionsPane.add(label, 0, 0);
        optionsPane.add(beverageLabel, 0, 1);
        optionsPane.add(cb1, 1, 1);
        optionsPane.add(appetizerLabel, 0, 2);
        optionsPane.add(cb2, 1, 2);
        optionsPane.add(mainCourseLabel, 0, 3);
        optionsPane.add(cb3, 1, 3);
        optionsPane.add(dessertLabel, 0, 4);
        optionsPane.add(cb4, 1, 4);
        gridPane.add(optionsPane, 0, 0);
        gridPane.add(slider, 0, 1);
        gridPane.add(tipSelected, 0, 2);
        gridPane.add(hbox, 0, 3);
        gridPane.add(subtotalLabel, 0, 4);
        gridPane.add(taxLabel, 0, 5);
        gridPane.add(tipsLabel, 0, 6);
        gridPane.add(totalLabel, 0, 7);
        root.setCenter(gridPane);
        
        //
        Scene scene = new Scene(root, 640, 480);
        mainStage.setScene(scene);
        mainStage.show();
        
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            tipSelected.setText("Tips: " + newValue + "%");
        });
        
        calculateBtn.setOnAction(event -> {
            double subTotal = 0.0;
            double tax = 0.0;
            double tips = 0.0;
            
            subTotal += getItemsFromList(cb1, itemsAndPrices);
            subTotal += getItemsFromList(cb2, itemsAndPrices);
            subTotal += getItemsFromList(cb3, itemsAndPrices);
            subTotal += getItemsFromList(cb4, itemsAndPrices);
            subtotalLabel.setText("Subtotal: " + subTotal);
            
            tax = subTotal * 0.15 - subTotal;
            taxLabel.setText("Tax: " + tax);
            
            tips = subTotal * 
            
            
        });
        
        clearBtn.setOnAction(event -> {
            cb1.setValue("");
            cb2.setValue("");
            cb3.setValue("");
            cb4.setValue("");
            slider.setValue(0.0);
            subtotalLabel.setText("Subtotal: 0");
            taxLabel.setText("Tax: 0");
            tipsLabel.setText("Tips: 0");
            totalLabel.setText("Total: 0");
        });
    }

    public static void main(String[] args) {
        launch();
    }
    
    //
    public static Double getItemsFromList(ComboBox<String> comboBox, String[][] itemsAndPrices){
        boolean isFieldEmpty = true;
        double price = 0.0;
        
        //
        for(int i = 0; i < itemsAndPrices.length; i++){
            for(int j = 0; j < itemsAndPrices[i].length; i++){
                if(comboBox.getValue().equals(itemsAndPrices[i][j])){
                    price = Double.parseDouble(itemsAndPrices[i][1]);
                    isFieldEmpty = false;
                }
            }
        }
        
        //If no value was selected, then return a price of 0.0$
        //else it returns the price of the item selected in the ComboBox
        if(isFieldEmpty){
            return price;
        }else{
            return price;
        }
    }

}