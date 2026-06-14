/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validator {

    private static Scanner SCANNER = new Scanner(System.in);

    public static int getInt(String messageInfo, String messageOutOfRange, int min, int max) {
        int result = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(messageInfo);
                String input = SCANNER.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Input cannot empty!");
                } else {
                    result = Integer.parseInt(input);
                    if (result >= min && result <= max) {
                        isValid = true;
                    } else {
                        System.out.println(messageOutOfRange);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter valid number!");
            }
        }
        return result;
    }

    public static double getDouble(String messageInfo, String messageOutOfRange, double min, double max) {
        double result = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(messageInfo);
                String input = SCANNER.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Input cannot empty!");
                } else {
                    result = Double.parseDouble(input);
                    if (result >= min && result <= max) {
                        isValid = true;
                    } else {
                        System.out.println(messageOutOfRange);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter valid number!");
            }
        }
        return result;
    }

    public static String getString(String messageInfo, String messageError, String REGEX) {
        String result = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.print(messageInfo);
            String input = SCANNER.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot empty!");
            } else {
                if (input.matches(REGEX)) {
                    result = input;
                    isValid = true;
                } else {
                    System.out.println(messageError);
                }
            }
        }
        return result;
    }

//    public static Date getDate(String messageInfo, String messageOutOfRange, Date min, Date max) {
//
//    }
}
