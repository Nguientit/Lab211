/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validator {
    
    private static final Scanner SCANNER = new Scanner(System.in);
    
    public static int checkInputInt(String prompt, double min) {
        int result = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(prompt);
                String input = SCANNER.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Input cannot empty");
                }

                result = Integer.parseInt(input);
                if (result < 0) {
                    System.out.println("Input cannot less than 0!");
                }else{
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input valid integer!");
            }
        }
        return result;
    }
}
