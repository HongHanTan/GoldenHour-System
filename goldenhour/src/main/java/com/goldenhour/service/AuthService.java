package com.goldenhour.service;

import com.goldenhour.categories.Employee;
import com.goldenhour.storage.CSVHandler;
import java.util.List;

public class AuthService {
    private static Employee currentUser;

    public static boolean login(String id, String password) {
        List<Employee> employees = CSVHandler.readEmployees();
        for (Employee emp : employees) {
            if (emp.getId().equals(id) && emp.getPassword().equals(password)) {
                currentUser = emp;
                System.out.println("\nLogin Successful!");
                System.out.println("Welcome, " + emp.getName() + " (" + emp.getId().substring(0, 3) + ")");
                return true;
            }
        }
        System.out.println("\nLogin Failed: Invalid User ID or Password");
        return false;
    }

    public static void logout() {
        if (currentUser != null) {
            System.out.println("\n" + currentUser.getName() + " has logged out.");
            currentUser = null;
        } else {
            System.out.println("\nNo user is currently logged in.");
        }
    }

    public static Employee getCurrentUser() {
        return currentUser;
    }
}
