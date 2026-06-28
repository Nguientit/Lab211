package utils;

import java.util.Scanner;

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
                    System.out.println("Input cannot be empty!");
                } else {
                    result = Integer.parseInt(input);
                    if (result >= min && result <= max) {
                        isValid = true;
                    } else {
                        System.out.println(messageOutOfRange);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer number!");
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
                System.out.println("Input cannot be empty!");
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

    public static String getStringUpdate(String messageInfo, String messageError, String REGEX) {
        while (true) {
            System.out.print(messageInfo);
            String input = SCANNER.nextLine().trim();
            if (input.isEmpty()) {
                return "";
            }
            if (input.matches(REGEX)) {
                return input;
            }
            System.out.println(messageError);
        }
    }

}
