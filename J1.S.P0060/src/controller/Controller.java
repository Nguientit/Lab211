/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Person;
import utils.Validator;

/**
 *
 * @author Admin
 */
public class Controller {

    public void ProcessShopping(){
        System.out.println("===== Shopping Program =====");
        int numBills = Validator.checkInputInt("input number of bills: ", 1);
        
        int[] bills = new int[numBills];
        for(int i = 0; i < numBills; i++){
            bills[i] = Validator.checkInputInt("input value of bill "+ (i+1) +": ", 0);
        }
        
        int walletAmount = Validator.checkInputInt("input value of wallet: ", 0);
        
        Person person = new Person(walletAmount);
        
        int totalBill = person.calcTotal(bills);
        System.out.println("\nthis is total of bill: " + totalBill);
        
        boolean canPay = person.getWallet().payMoney(totalBill);
        
        if(canPay){
            System.out.println("You can buy it.");
        }else{
            System.out.println("You cant buy it.");
        }
    }
}
