/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Order {

    private String customerName;
    private List<Fruit> buyItems;

    public Order(String customerName, List<Fruit> buyItems) {
        this.customerName = customerName;
        this.buyItems = buyItems;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Fruit> getBuyItems() {
        return buyItems;
    }

    public void setBuyItems(List<Fruit> buyItems) {
        this.buyItems = buyItems;
    }

}
