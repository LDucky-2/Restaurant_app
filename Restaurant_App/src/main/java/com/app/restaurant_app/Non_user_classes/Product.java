package com.app.restaurant_app.Non_user_classes;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import static com.app.restaurant_app.Utility.read_object;
import static com.app.restaurant_app.Utility.show_information_alert;

public class Product implements Serializable {
    private final String product_name;
    private final ArrayList<String> ingredients;
    private int price;
    private static int sales;

    public Product(String product_name, ArrayList<String> ingredients, int price) {
        this.product_name = product_name;
        this.ingredients = new ArrayList<String>(ingredients);
        this.price = price;

    }

    public int getSales() {
        return sales;
    }

    public String getProduct_name() {
        return product_name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        ingredients = ingredients;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public static Product verify_new_product_and_return(String product_name,ArrayList<String> ingredients, int price){
        try {
            ArrayList<Object> object_arraylist = read_object("data_files/product_data.bin");
            Product product;
            for (Object object : object_arraylist){
                product = (Product)object;
                if (product.getProduct_name().equals(product_name)){
                    show_information_alert("Product with this name already exists");
                    return null;
                }
            }
            product = new Product(product_name,ingredients,price);
            return product;
        } catch (Exception e) {
            show_information_alert("smth happened verify_new_product_and_return");
        }
        show_information_alert("smth happened here 1 verify_new_product_and_return");
        return null;
    }
}
