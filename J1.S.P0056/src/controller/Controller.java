/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.SalaryHistory;
import model.SalaryStatus;
import model.Worker;
import utils.Validator;

/**
 *
 * @author Admin
 */
public class Controller {

    private List<Worker> list = new ArrayList<>();

    public void run() {
        int choice = 0;
        while (choice != 5) {
            choice = Validator.getInt("\n========= Worker Manager ==========\n"
                    + "1. Add Worker\n"
                    + "2. Up salary\n"
                    + "3. Down salary\n"
                    + "4. Display Information salary\n"
                    + "5. Exit\n"
                    + "Enter your choice: ", "Choice must be between 1 and 5!", 1, 5);
            switch (choice) {
                case 1:
                    addWorker();
                    break;
                case 2:
                    changeSalary(SalaryStatus.UP);
                    break;
                case 3:
                    changeSalary(SalaryStatus.DOWN);
                    break;
                case 4:
                    showHistorySalary();
                    break;
            }
        }
    }

    private void addWorker() {
        System.out.println("---------- Add Worker ----------");
        String id = Validator.getString("Enter Code: ", "Invalid Code format!", "[Ww]\\d+").toUpperCase();

        for (Worker w : list) {
            if (w.getId().equalsIgnoreCase(id)) {
                System.out.println("Error: Worker with id: " + id + " already exist.");
                return;
            }
        }
        String name = Validator.getString("Enter Name: ", "Invalid Name!", "[A-Za-z\\s]+");
        int age = Validator.getInt("Enter Age: ", "Age must be in range 18 and 50", 18, 50);
        double salary = Validator.getDouble("Enter Salary: ", "Salary must be greater than 0!", 0.1, Double.MAX_VALUE);
        String workLocation = Validator.getString("Enter work location: ", "Invalid location!", "[A-Za-z0-9\\s]+");
        list.add(new Worker(id, name, age, salary, workLocation));
        System.out.println("Add sucess");
    }

    private void changeSalary(SalaryStatus status) {
        if (list.isEmpty()) {
            System.out.println("The worker list is empty!");
            return;
        }
        System.out.println("--------- Up/Down Salary ---------");
        String code = Validator.getString("Enter Code: ", "Invalid id", "[Ww]\\d+").toUpperCase();

        Worker worker = null;
        for (Worker w : list) {
            if (w.getId().equalsIgnoreCase(code)) {
                worker = w;
                break;
            }
        }

        if (worker == null) {
            System.out.println("Worker code not found");
            return;
        }

        double salary = Validator.getDouble("Enter salary: ", "Salary must be greater than 0!", 0, Double.MAX_VALUE);
        double currentSalary = worker.getSalary();

        if (status == SalaryStatus.DOWN && salary > currentSalary) {
            System.out.println("Salary decrease can not greater than current salary!");
            return;
        }

        double newSalary = (status == SalaryStatus.UP) ? (currentSalary + salary) : (currentSalary - salary);

        worker.setSalary(newSalary);
        worker.addHistoryList(new SalaryHistory(newSalary, status, new Date()));
        System.out.println("Update success");
    }

    private void showHistorySalary() {
        if (list.isEmpty()) {
            System.out.println("Salary history is empty");
            return;
        }
        list.sort((w1, w2) -> w1.getId().compareToIgnoreCase(w2.getId()));

        System.out.println("------------------------ Salary Information ------------------------");
        System.out.printf("%-8s %-12s %-10s %-10s %-10s %-12s\n", "Code", "Name", "Age", "Salary", "Status", "Date");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Worker w : list) {
            for (SalaryHistory sh : w.getHistoryList()) {
                System.out.printf("%-8s %-12s %-10d %-10.0f %-10s %-12s\n",
                        w.getId(),
                        w.getName(),
                        w.getAge(),
                        sh.getSalaryUpdate(),
                        sh.getStatus().name(),
                        dateFormat.format(sh.getDate()));
            }

        }
    }
}
