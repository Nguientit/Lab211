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
/**
 * Lớp DataInput chứa các phương thức hỗ trợ nhập liệu và kiểm tra dữ liệu đầu
 * vào (Validation).
 */
public class DataInput {

    /**
     * Khai báo đối tượng đọc dữ liệu từ bàn phím. 
     * private: Đóng gói không cho lớp ngoài gọi trực tiếp. 
     * static: Dùng chung duy nhất một vùng nhớ xuyên suốt chương trình để chống xung đột luồng và rò rỉ tài nguyên bàn phím.
     * final: Khóa cứng tham chiếu vùng nhớ, chống việc gán lại đối tượng Scanner khác.
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Phương thức tĩnh kiểm tra nhập số thực double trong một khoảng [min, max] cố định.
     * static: Cho phép gọi trực tiếp thông qua tên lớp (DataInput.checkInputDouble) mà không cần khởi tạo đối tượng.
     */
    public static double checkInputDouble(String messageInfo, double min, double max) {
        //vòng lặp vô hạn,ép người dùng phải nhập đúng dữ liệu mới thoát ra bằng lệnh return
        double result = 0;
        boolean isValid = false;
        
        while (!isValid) {
            try {
                System.out.println(messageInfo); // in ra dòng thông báo hướng dẫn người dùng nhập liệu
                String input = sc.nextLine().trim(); // Đọc toàn bộ chuỗi ký tự trên dòng và cắt bỏ khoảng trắng thừa ở hai đầu (.trim())
                
                // Kiểm tra xem chuỗi nhập vào có rỗng hoặc chỉ có khoảng trắng hay không
                if (input.isEmpty()) {
                    System.out.println("Input cannot empty");
                }

                // Thực hiện ép kiểu dữ liệu từ dạng Chuỗi ký tự (String) sang Số thực (double)
                result = Double.parseDouble(input);
                // Kiểm tra điều kiện biên logic hình học (kích thước không được phép âm)
                if (result < 0) {
                    System.out.println("Input cannot less than 0!");
                }
                if (result >= min && result <= max) {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                // Khối catch bắt giữ ngoại lệ NumberFormatException sinh ra khi người dùng nhập chữ hoặc ký tự lạ vào hàm parseDouble
                System.out.println("Input valid double!");
            }
        }
        return result;
    }
}
