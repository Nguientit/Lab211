/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Student;
import utils.Validator;

/**
 *
 * @author Admin
 */
public class Controller {

    private List<Student> students = new ArrayList<>();

    public Controller() {
        initData();
    }

    private void initData() {
        students.add(new Student("S1", "Nguyen Van A", "Summer 2026", "Java"));
        students.add(new Student("S1", "Nguyen Van A", "Summer 2026", "Java"));
        students.add(new Student("S2", "Nguyen Van B", "Spring 2026", ".Net"));
        students.add(new Student("S2", "Nguyen Van B", "Summer 2026", "Java"));
        students.add(new Student("S3", "Tran Thi C", "Fall 2025", "C/C++"));
        students.add(new Student("S4", "Le Hoang D", "Spring 2026", "Java"));
        students.add(new Student("S5", "Pham Minh E", "Summer 2026", ".Net"));
        students.add(new Student("S6", "Vu Hoang F", "Fall 2025", "C/C++"));
        students.add(new Student("S7", "Hoang Lan G", "Spring 2026", "Java"));
        students.add(new Student("S8", "Do Thanh H", "Summer 2026", ".Net"));
    }

    public void run() {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\nWELCOME TO STUDENT MANAGEMENT");
            System.out.println("1. Create");
            System.out.println("2. Find and Sort");
            System.out.println("3. Update/Delete");
            System.out.println("4. Report");
            System.out.println("5. Exit");

            choice = Validator.getInt("Enter your choice: ", "Please select choice from 1 to 5", 1, 5);

            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    findAndSort();
                    break;
                case 3:
                    updateOrDelete();
                    break;
                case 4:
                    generateReport();
                    break;
            }
        }
        System.out.println("Application closed. Goodbye!");
    }

    public void createStudent() {
        boolean isFinished = false;

        while (!isFinished) {
            System.out.println("\n--- Create Student ---");
            String id = Validator.getString("Enter Student ID: ", "Invalid ID Format!", "[A-Za-z0-9]+").toUpperCase();

            String existingName = "";
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId().equalsIgnoreCase(id)) {
                    existingName = students.get(i).getStudentName();
                    break;
                }
            }

            String name;
            if (!existingName.isEmpty()) {
                System.out.println("Existing student found with this ID. Name auto-filled: " + existingName);
                name = existingName;
            } else {
                name = Validator.getString("Enter Student Name: ", "Letters and spaces only!", "[A-Za-z\\s]+");
            }

            String semester = Validator.getString("Enter Semester: ", "Cannot be empty!", "[A-Za-z0-9\\s]+");
            String course = Validator.getString("Enter Course Name (Java, .Net, C/C++): ", "We only offer Java, .Net, or C/C++!", "(?i)(Java|\\.Net|C/C\\+\\+)");

            if (course.equalsIgnoreCase("Java")) {
                course = "Java";
            } else if (course.equalsIgnoreCase(".Net")) {
                course = ".Net";
            } else {
                course = "C/C++";
            }

            students.add(new Student(id, name, semester, course));
            System.out.println("Student added successfully!");

            if (students.size() >= 10) {
                String continueChoice = Validator.getString("Do you want to continue (Y/N)? ", "Please enter Y or N", "[YyNn]");
                if (continueChoice.equalsIgnoreCase("N")) {
                    isFinished = true;
                }
            }
        }
    }

    public void findAndSort() {
        if (students.isEmpty()) {
            System.out.println("The student list is empty!");
            return;
        }

        System.out.println("\n--- Find and Sort Student ---");
        String keyword = Validator.getString("Enter student name to search: ", "Invalid name format!", "[A-Za-z\\s]+").toLowerCase();
        List<Student> searchResult = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getStudentName().toLowerCase().contains(keyword)) {
                searchResult.add(s);
            }
        }

        if (searchResult.isEmpty()) {
            System.out.println("No student match your search criteria!");
            return;
        }

        Collections.sort(searchResult, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getStudentName().compareToIgnoreCase(o2.getStudentName());
            }
        });

        System.out.println("\n---- Sorted Search Result ----");
        System.out.printf("| %-5s | %-20s | %-15s | %-15s |\n", "No.", "Student Name", "Semester", "Course Name");
        for (int i = 0; i < searchResult.size(); i++) {
            Student s = searchResult.get(i);
            System.out.printf("| %-5d | %-20s | %-15s | %-15s |\n",
                    (i + 1),
                    s.getStudentName(),
                    s.getSemester(),
                    s.getCourseName());
        }
    }

    public void updateOrDelete() {
        if (students.isEmpty()) {
            System.out.println("The student list is empty!");
            return;
        }
        System.out.println("\n--- Update or Delete Student ---");
        String id = Validator.getString("Enter Student id to find: ", "Invalid ID format!", "[A-Za-z0-9]+");

        List<Student> foundRecords = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(id)) {
                foundRecords.add(students.get(i));
            }
        }

        if (foundRecords.isEmpty()) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        System.out.println("\nFound records for Id: " + id + ":");
        System.out.printf("| %-5s | %-20s | %-15s | %-15s |\n", "No.", "Student Name", "Semester", "Course Name");
        for (int i = 0; i < foundRecords.size(); i++) {
            Student s = foundRecords.get(i);
            System.out.printf("| %-5d | %-20s | %-15s | %-15s |\n",
                    (i + 1),
                    s.getStudentName(),
                    s.getSemester(),
                    s.getCourseName());
        }

        int targetIndex = Validator.getInt("Select record number to update/delete: ", "Invalid record number!", 1, foundRecords.size());
        Student selectedStudent = foundRecords.get(targetIndex - 1);

        String action = Validator.getString("Do you want to update (U) or delete (D) students?", "Please enter U or D!", "[UuDd]");

        if (action.equalsIgnoreCase("D")) {
            students.remove(selectedStudent);
            System.out.println("Delete student success!");
        } else {
            System.out.println("--- Leave input empty if you do not want to change the value ---");
            String newName = Validator.getStringUpdate("Enter new Student Name (Current " + selectedStudent.getStudentName() + "):", "Invalid name format!", "[A-Za-z\\s]+");
            String newSemester = Validator.getStringUpdate("Enter new Semester (Current " + selectedStudent.getSemester() + "): ", "Invalid semester format", "[A-Za-z0-9\\s]+");
            String newCourse = Validator.getStringUpdate("Enter new Course Name (Current " + selectedStudent.getCourseName() + "): ", "We only offer Java, .Net, or C/C++!", "(?i)(Java|\\.Net|C/C\\+\\+)");

            if (!newName.isEmpty()) {
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId().equalsIgnoreCase(selectedStudent.getId())) {
                        students.get(i).setStudentName(newName);
                    }
                }
            }

            if (!newSemester.isEmpty()) {
                selectedStudent.setSemester(newSemester);
            }

            if (!newCourse.isEmpty()) {
                if (newCourse.equalsIgnoreCase("Java")) {
                    newCourse = "Java";
                } else if (newCourse.equalsIgnoreCase(".Net")) {
                    newCourse = ".Net";
                } else {
                    newCourse = "C/C++";
                }
                selectedStudent.setCourseName(newCourse);
            }
            System.out.println("Update student success!");
        }
    }

    public void generateReport() {
        if (students.isEmpty()) {
            System.out.println("The student list is empty!");
            return;
        }
        System.out.println("\n---- Student Course Report ----");
        System.out.printf("| %-5s | %-20s | %-12s | %-12s |\n", "No.", "Student Name", "Course Name", "Total Course");
        List<Student> reported = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            Student s1 = students.get(i);
            boolean isPrinted = false;
            for (int j = 0; j < reported.size(); j++) {
                Student s2 = reported.get(j);
                if (s1.getStudentName().equalsIgnoreCase(s2.getStudentName())
                        && s1.getCourseName().equalsIgnoreCase(s2.getCourseName())) {
                    isPrinted = true;
                    break;
                }
            }
            if (!isPrinted) {
                int totalCourse = 0;
                for (int k = 0; k < students.size(); k++) {
                    Student s3 = students.get(k);
                    if (s1.getStudentName().equalsIgnoreCase(s3.getStudentName())
                            && s1.getCourseName().equalsIgnoreCase(s3.getCourseName())) {
                        totalCourse++;
                    }
                }
                System.out.printf("| %-5s | %-20s | %-12s | %-12d |\n",
                        (i + 1),
                        s1.getStudentName(),
                        s1.getCourseName(),
                        totalCourse);
                reported.add(s1);
            }
        }
    }
}
