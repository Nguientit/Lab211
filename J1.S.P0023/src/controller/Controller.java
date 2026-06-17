/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Fruit;
import model.Order;
import utils.Validator;

/**
 *
 * @author Admin
 */
public class Controller {

    private List<Fruit> fruits = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public Controller() {
        initFruitData();
    }

    private void initFruitData() {
        fruits.add(new Fruit("F1", "Coconut", 2.0, 10, "Viet Nam"));
        fruits.add(new Fruit("F2", "Orange", 3.0, 15, "Thailand"));
        fruits.add(new Fruit("F3", "Apple", 4.0, 12, "US"));
        fruits.add(new Fruit("F4", "Grape", 6.0, 20, "Japan"));
    }

    public void run() {
        int choice = 0;

        while (choice != 4) {
            choice = Validator.getInt("\nFRUIT SHOP SYSTEM\n"
                    + "  1. Create Fruit\n"
                    + "  2. View Order\n"
                    + "  3. Shopping(for buyer)\n"
                    + "  4. Exit\n"
                    + "Enter your choice: ", "Please select choice from 1 to 4", 1, 4);
            switch (choice) {
                case 1:
                    createFruit();
                    break;
                case 2:
                    viewOrder();
                    break;
                case 3:
                    shopping();
                    break;
            }
        }
    }

    private void createFruit() {
        String choose;
        do {
            System.out.println("\n-------- Create Product --------");
            String id = Validator.getString("Enter Fruit ID: ", "Invalid ID Format!", "[A-Za-z0-9\\s]+").toUpperCase();

            Fruit existFruit = null;
            for (int i = 0; i < fruits.size(); i++) {
                Fruit f = fruits.get(i);
                if (f.getId().equalsIgnoreCase(id)) {
                    existFruit = f;
                    break;
                }
            }
            if (existFruit != null) {
                System.out.println("Fruit ID is already exist. You can update stock quantity");
                int extraQuantity = Validator.getInt("Enter quantity: ", "Quantity must be >= 1", 1, Integer.MAX_VALUE);
                existFruit.setQuantity(existFruit.getQuantity() + extraQuantity);
                System.out.println("Stock update sucess.");
            } else {
                String name = Validator.getString("Enter Fruit name: ", "Invalid name format", "[A-Za-z\\s]+");
                double price = Validator.getDouble("Enter price: ", "Price must be >= 0.", 0.1, Double.MAX_VALUE);
                int quantity = Validator.getInt("Enter quantity: ", "quantity must be >= 1 ", 1, Integer.MAX_VALUE);
                String origin = Validator.getString("Enter origin: ", "Invalid orgigin format", "[A-Za-z\\s]+");

                fruits.add(new Fruit(id, name, price, quantity, origin));
                System.out.println("Fruit created successfully");
            }

            choose = Validator.getString("Do you want to continue(Y/N)? ", "Please enter Y or N", "[YyNn]");
        } while (choose.equalsIgnoreCase("Y"));
        displayFruitStock();
    }

    private void viewOrder() {
        if (orders.isEmpty()) {
            System.out.println("No order available");
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println("\nCustomer: " + order.getCustomerName());
            System.out.printf("%-5s | %-10s | %-10s | %-8s | %-8s\n",
                    "No.", "Product", "Quantity", "Price", "Amount");

            double totalOrderAmount = 0;
            List<Fruit> buyItems = order.getBuyItems();

            for (int j = 0; j < buyItems.size(); j++) {
                Fruit items = buyItems.get(j);
                double amount = items.getQuantity() * items.getPrice();
                totalOrderAmount += amount;
                System.out.printf("%-5d   %-10s   %-10d   %2.0f$   %6.0f$\n",
                        (j + 1),
                        items.getName(), 
                        items.getQuantity(),
                        items.getPrice(),
                        amount);
            }
            System.out.println("Total: " + (int) totalOrderAmount + "$");
        }
    }

    private void shopping() {
        boolean hasStock = false;
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getQuantity() > 0) {
                hasStock = true;
                break;
            }
        }

        if (!hasStock) {
            System.out.println("Shop is out of stock!");
            return;
        }

        List<Fruit> cart = new ArrayList<>();
        boolean isFinish = false;

        while (!isFinish) {
            displayFruitStock();
            int item = Validator.getInt("Select item to buy: ", "Invalid item number", 1, fruits.size());
            Fruit selectedFruits = fruits.get(item - 1);

            if (selectedFruits.getQuantity() == 0) {
                System.out.println("Fruits is out of stock!");
            } else {
                System.out.println("You selected: " + selectedFruits.getName());
                int quantityBuy = Validator.getInt("Please input quantity: ", "Invalid Quantity must between 1 and " + selectedFruits.getQuantity() + "!", 1, selectedFruits.getQuantity());
                selectedFruits.setQuantity(selectedFruits.getQuantity() - quantityBuy);

                Fruit fruitInCart = null;
                for (int i = 0; i < cart.size(); i++) {
                    Fruit f = cart.get(i);
                    if (f.getId().equalsIgnoreCase(selectedFruits.getId())) {
                        fruitInCart = f;
                        break;
                    }
                }

                if (fruitInCart != null) {
                    fruitInCart.setQuantity(fruitInCart.getQuantity() + quantityBuy);
                } else {
                    cart.add(new Fruit(selectedFruits.getId(), selectedFruits.getName(), selectedFruits.getPrice(), quantityBuy, selectedFruits.getOrigin()));
                }
                String orderChoice = Validator.getString("Do you want order now (Y/N): ", "Please enter Y or N", "[YyNn]");
                if (orderChoice.equalsIgnoreCase("Y")) {
                    isFinish = true;
                }
            }
        }

        System.out.printf("%-10s | %-10s | %-8s | %-8s\n", "Product", "Quantity", "Price", "Amount");

        double finalTotal = 0;
        for (int i = 0; i < cart.size(); i++) {
            Fruit f = cart.get(i);
            double amount = f.getQuantity() * f.getPrice();
            finalTotal += amount;

            System.out.printf("%-10s   %-10d   %2.0f$   %6.0f$\n",
                    f.getName(),
                    f.getQuantity(),
                    f.getPrice(),
                    amount);
        }

        System.out.println("Total: " + finalTotal);

        String customerName = Validator.getString("Input your name: ", "Invalid name format!", "[A-Za-z\\s]+");
        orders.add(new Order(customerName, cart));
        System.out.println("Order completed success");
    }

    private void displayFruitStock() {
        System.out.println("\nList of Fruit:");

        System.out.printf("|++ %-5s ++|++ %-10s ++|++ %-8s ++|++ %-5s ++|\n",
                "Item", "Fruit Name", "Origin", "Price");
        for (int i = 0; i < fruits.size(); i++) {
            Fruit f = fruits.get(i);
            if (f.getQuantity() > 0) {
                System.out.printf("|   %-5d   |   %-10s   |   %-8s   |   %4.0f$   |\n",
                        (i + 1),
                        f.getName(),
                        f.getOrigin(),
                        f.getPrice());
            }
        }
    }

}
