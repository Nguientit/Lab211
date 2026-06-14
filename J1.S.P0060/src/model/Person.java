/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Person {
    private Wallet wallet;

    public Person(int walletAmount) {
        this.wallet = new Wallet(walletAmount);
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
    
    public int calcTotal(int[] bills){
        int total = 0;
        for(int i = 0; i < bills.length; i++){
            total += bills[i];
        }
        return total;
    }
    
}
