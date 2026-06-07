/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Circle;
import model.Rectangle;
import model.Triangle;
import utils.DataInput;

/**
 *
 * @author Admin
 */

/**
 * Lớp Controller đóng vai trò là khối điều khiển logic trung gian, kết nối dữ liệu đầu vào và các thực thể dữ liệu.
 */
public class Controller {
    
    /**
     * Hàm private xử lý logic nhập liệu và kiểm tra cấu trúc hình học cho riêng đối tượng Tam giác.
     * Trả về một đối tượng Triangle hoàn chỉnh sau khi thu thập đủ dữ liệu sạch.
     */
    private void inputTriangle(Triangle triangle){
        double a = 0, b= 0, c = 0;
        boolean isValidTriangle = false;
        
        do{
            // Gọi lớp tiện ích DataInput để quét số thực cho từng cạnh, cấu hình biên từ mức dương tối thiểu đến tối đại
            a = DataInput.checkInputDouble("Please input side A of Triangle", Double.MIN_VALUE, Double.MAX_VALUE);
            b = DataInput.checkInputDouble("Please input side B of Triangle", Double.MIN_VALUE, Double.MAX_VALUE);
            c = DataInput.checkInputDouble("Please input side C of Triangle", Double.MIN_VALUE, Double.MAX_VALUE);
            
            //Kiểm tra điều kiện bất đẳng thức tam giác
            if((a + b > c) && (a + c > b) && ( b + c > a)){
                isValidTriangle = true;
            }else{
                System.out.println("Please input valid side of Triangle");
            }
        }while(!isValidTriangle);
        // Thoát khỏi vòng lặp an toàn, gọi từ khóa new để trả về một đối tượng Triangle mới
        
        triangle.setSideA(a);
        triangle.setSideB(b);
        triangle.setSideC(c);
    }
    
    private void inputCircle(Circle circle){
        // Gọi hàm tiện ích quét dữ liệu bán kính
        double radius = DataInput.checkInputDouble("Please input radius of Circle: ", Double.MIN_VALUE, Double.MAX_VALUE);
        // Trả về một đối tượng Circle mới được khởi tạo trên bộ nhớ Heap
        circle.setRadius(radius);
    }
    
    private void inputRectangle(Rectangle rectangle){
        double width = DataInput.checkInputDouble("Please input width of Rectangle: ", Double.MIN_VALUE, Double.MAX_VALUE);
        double length = DataInput.checkInputDouble("Please input length of Rectangle: ", Double.MIN_VALUE, Double.MAX_VALUE);
        rectangle.setWidth(width);
        rectangle.setLength(length);
    }
    
    /**
     * Phương thức public công khai dùng để thiết lập luồng chạy tuần tự cho toàn bộ bài toán.
     * Lớp Main bên ngoài sẽ tương tác với Controller qua phương thức này.
     */
    public void processCalculator(){
        System.out.println("=====Calculator Shape Program=====");
        
        //Gọi các hàm nhập liệu để thu thập và đóng gói dữ liệu vào các thực thể tương ứng
        Rectangle rectangle = new Rectangle(0, 0);
        Circle circle =  new Circle(0);
        Triangle triangle = new Triangle(0, 0, 0);
        
        inputRectangle(rectangle);
        inputCircle(circle);
        inputTriangle(triangle);
       
        System.out.println();
        
        //Gọi phương thức in kết quả của từng thực thế để thực hiện tính toán
        rectangle.printResult();
        circle.printResult();
        triangle.printResult();
    }
}
