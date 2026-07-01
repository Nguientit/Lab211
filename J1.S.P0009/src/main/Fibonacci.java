/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

/**
 *
 * @author Admin
 */
public class Fibonacci {

//    private static long[] cache = new long[45];
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        System.out.println("The 45 sequence Fibonacci: ");
//        for (int i = 0; i < 45; i++) {
//            if (i == 44) {
//                System.out.print(findFibonacci(i));
//            } else {
//                System.out.print(findFibonacci(i) + ", ");
//            }
//        }
//        System.out.println();
//    }
//
//    public static long findFibonacci(int n) {
//        if (n == 0) {
//            return 0;
//        }
//        if (n == 1) {
//            return 1;
//        }
//        if (cache[n] != 0) {
//            return cache[n];
//        }
//        cache[n] = findFibonacci(n - 1) + findFibonacci(n - 2);
//        return cache[n];
//    }
    public static void main(String[] args) {
        System.out.println("The 45 sequence Fibonacci: ");
        printFibonacci(455, 0, 1);
        System.out.println();
    }

    public static void printFibonacci(int count, long a, long b) {
        if (count == 0) {
            return;
        }
        if (count == 1) {
            System.out.println(a);
        } else {
            System.out.println(a + ", ");
        }
        printFibonacci(count - 1, b, a + b);
    }
}
 