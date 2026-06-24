/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Intern;
import utils.Validator;

/**
 *
 * @author Admin
 */
public class Controller {

    private List<Candidate> candidates = new ArrayList<>();

    public Controller() {
        initData();
    }

    private void initData() {
        candidates.add(new Experience(5, "Java Spring Boot", "C1", "An", "Nguyen Van", 1995, "Ha Noi", "0912345678", "annv@gmail.com", 0));
        candidates.add(new Experience(8, "Flutter Mobile", "C2", "Binh", "Tran Quang", 1992, "TP. HCM", "0987654321", "binhtq@gmail.com", 0));
        candidates.add(new Experience(4, "DevOps AWS", "C3", "Chi", "Le Thi", 1997, "Da Nang", "0933445566", "chilt@gmail.com", 0));

        candidates.add(new Fresher("2025", "Excellence", "FPT University", "C4", "Dung", "Pham Tien", 2003, "Ha Noi", "0944556677", "dungpt@fpt.edu.vn", 1));
        candidates.add(new Fresher("2025", "Good", "Bach Khoa University", "C5", "Huong", "Nguyen Thu", 2003, "TP. HCM", "0955667788", "huongnt@gmail.com", 1));
        candidates.add(new Fresher("2026", "Fair", "National Economics University", "C6", "Khanh", "Vu Hoang", 2004, "Ha Noi", "0966778899", "khanhvh@gmail.com", 1));

        candidates.add(new Intern("Software Engineering", "7", "FPT University", "C7", "Nam", "Dang Hoai", 2005, "Da Nang", "0977889900", "namdh@fpt.edu.vn", 2));
        candidates.add(new Intern("Data Science", "6", "University of Science", "C8", "Linh", "Mai Phuong", 2005, "TP. HCM", "0988990011", "linhmp@gmail.com", 2));
        candidates.add(new Intern("Information Assurance", "5", "FPT University", "C9", "Minh", "Trinh Nhat", 2006, "Ha Noi", "0999001122", "minhnt@fpt.edu.vn", 2));
    }

    public void run() {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\nCANDIDATE MANAGEMENT SYSTEM");
            System.out.println("1. Experience");
            System.out.println("2. Fresher");
            System.out.println("3. Internship");
            System.out.println("4. Searching");
            System.out.println("5. Exit");

            choice = Validator.getInt("Enter your choice: ", "Choice must be from 1 to 5", 1, 5);

            switch (choice) {
                case 1:
                    createCandidate(0);
                    break;
                case 2:
                    createCandidate(1);
                    break;
                case 3:
                    createCandidate(2);
                    break;
                case 4:
                    searchCandidate();
                    break;
            }
        }
    }

    private void createCandidate(int type) {
        boolean choose = true;
        while (choose) {
            System.out.println("\n----- Create Candidate -----");

            String id = Validator.getString("Enter Candidate ID: ", "Invalid ID format", "[A-Za-z0-9]+");
            boolean isDuplicate = false;
            for (int i = 0; i < candidates.size(); i++) {
                if (candidates.get(i).getId().equalsIgnoreCase(id)) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.println("Candidate ID is exist");
            } else {
                String firstName = Validator.getString("Enter First Name: ", "Invalid first name format!", "[A-Za-z\\s]+");
                String lastName = Validator.getString("Enter Last Name: ", "Invalid last name format!", "[A-Za-z\\s]+");
                int birthDate = Validator.getInt("Enter Birth Date: ", "Invalid birth date format", 1900, 2026);
                String address = Validator.getString("Enter Address: ", "Invalid address format", "[A-Za-z0-9\\s.,-]+");
                String phone = Validator.getString("Enter Phone Number: ", "Phone must be minimum 10 characters", "\\d{10,}");
                String email = Validator.getString("Enter Email: ", "Invalid email format", "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}$");

                if (type == 0) {
                    int expInYear = Validator.getInt("Enter Year of Experience: ", "year of experience must be from 0 to 100", 0, 100);
                    String proSkill = Validator.getString("Enter Professional Skill: ", "Invalid professional skill", "[A-Za-z0-9\\s]+");
                    candidates.add(new Experience(expInYear, proSkill, id, firstName, lastName, birthDate, address, phone, email, 0));
                } else if (type == 1) {
                    String gradDate = Validator.getString("Enter Graduation Date: ", "Invalid date format", "[A-Za-z0-9\\s/-]+");
                    String gradRank = Validator.getString("Enter Graduation Rank (Excellence, Good, Fair, Poor)", "Invalid rank", "(?i)(Excellence|Good|Fair|Poor)");
                    String education = Validator.getString("Enter University: ", "Invalid university format", "[A-Za-z0-9\\s]+");
                    candidates.add(new Fresher(gradDate, gradRank, education, id, firstName, lastName, birthDate, address, phone, email, 1));
                } else if (type == 2) {
                    String majors = Validator.getString("Enter Majors: ", "Invalid major format", "[A-Za-z\\s]+");
                    String semester = Validator.getString("Enter semester: ", "Invalid semester format", "[A-Za-z0-9\\s]+");
                    String uniName = Validator.getString("Enter University Name: ", "Invalid name format", "[A-Za-z0-9\\s]+");
                    candidates.add(new Intern(majors, semester, uniName, id, firstName, lastName, birthDate, address, phone, email, 2));
                }
                System.out.println("Candidate created successfully!");
            }

            String isFinish = Validator.getString("Do you want to continue (Y/N)?", "Please enter Y or N", "[YyNn]");
            if (isFinish.equalsIgnoreCase("N")) {
                choose = false;
            }
            displayCandidateName();
        }
    }

    private void searchCandidate() {
        if (candidates.isEmpty()) {
            System.out.println("The candidate list is empty!");
            return;
        }

        displayCandidateName();

        System.out.println("----- Search Candidate -----");
        String searchKeyword = Validator.getString("Input Candidate name (First name or Last name): ", "Invalid search name", "[A-Za-z\\s]+").toLowerCase();
        int searchType = Validator.getInt("Input type of candidate: ", "Type must be 0,1,2", 0, 2);
        System.out.println("\nThe candidates found: ");

        boolean isFound = false;
        for (int i = 0; i < candidates.size(); i++) {
            Candidate c = candidates.get(i);
            String firstName = c.getFirstName().toLowerCase();
            String lastName = c.getLastName().toLowerCase();

            if (c.getCandidateType() == searchType && (firstName.contains(searchKeyword) || lastName.contains(searchKeyword))) {
                System.out.printf("%-15s | %-5d | %-5s | %-10s | %-20s | %-5d\n",
                        c.getFirstName() + " " + c.getLastName(),
                        c.getBirthDate(),
                        c.getAddress(),
                        c.getPhone(),
                        c.getEmail(),
                        c.getCandidateType());
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("No candidates matches your search criteria!");
        }
    }

    private void displayCandidateName() {
        System.out.println("\nList of Candidate:");

        System.out.println("===========EXPERIENCE CANDIDATE============");
        for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).getCandidateType() == 0) {
                System.out.println(candidates.get(i).getFirstName() + " " + candidates.get(i).getLastName());
            }
        }
        System.out.println("==========FRESHER CANDIDATE==============");
        for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).getCandidateType() == 1) {
                System.out.println(candidates.get(i).getFirstName() + " " + candidates.get(i).getLastName());
            }
        }

        System.out.println("===========INTERN CANDIDATE==============");
        for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).getCandidateType() == 2) {
                System.out.println(candidates.get(i).getFirstName() + " " + candidates.get(i).getLastName());
            }
        }
    }
}
