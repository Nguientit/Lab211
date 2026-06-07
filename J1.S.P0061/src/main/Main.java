/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.Controller;

/**
 *
 * @author Admin
 */

/**
 * Lớp Main chứa hàm main(), đóng vai trò là điểm khởi chạy hệ thống (Entry Point) của ứng dụng Java.
 */
public class Main {
 
    public static void main(String[] args) {
        /**
         * Khởi tạo đối tượng điều phối luồng Controller trên bộ nhớ Heap.
         * Biến 'controller' đóng vai trò biến tham chiếu kiểu dữ liệu Controller nằm trên vùng nhớ Stack.
         */
        Controller controller = new Controller();
        
        /**
         * Kích hoạt phương thức công khai duy nhất của Controller để vận hành luồng xử lý toàn bộ bài toán.
         * Tách biệt hoàn toàn phần chạy giao diện kích hoạt và phần tính toán nghiệp vụ lõi bên trong.
         */
        controller.processCalculator();
    }
    
}
