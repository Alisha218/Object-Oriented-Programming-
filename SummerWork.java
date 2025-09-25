/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
  import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
package com.mycompany.summerwork;

/**
 *
 * @author Alisha
 */
public class SummerWork {


    private static Map<String, String> users = new HashMap<>();
    private static final int MAX_ATTEMPTS = 3;

    static {
        // Predefined users (in real app, use database)
        users.put("admin", "admin123");
        users.put("user1", "pass123");
        users.put("john", "doe123");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("        SIMPLE LOGIN SYSTEM");
            System.out.println("=".repeat(40));
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login(scanner);
                    break;
                case "2":
                    register(scanner);
                    break;
                case "3":
                    System.out.println("Thank you for using our system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void login(Scanner scanner) {
        int attempts = 0;
        
        while (attempts < MAX_ATTEMPTS) {
            System.out.println("\n--- Login ---");
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();
            
            System.out.print("Password: ");
            String password = scanner.nextLine().trim();

            if (authenticate(username, password)) {
                System.out.println("\n✅ Login successful! Welcome, " + username + "!");
                showUserMenu(scanner, username);
                return;
            } else {
                attempts++;
                if (attempts < MAX_ATTEMPTS) {
                    System.out.println("❌ Invalid credentials! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                }
            }
        }
        
        System.out.println("\n🚫 Maximum login attempts exceeded. Please try again later.");
    }

    private static void register(Scanner scanner) {
        System.out.println("\n--- Registration ---");
        
        System.out.print("Choose username: ");
        String username = scanner.nextLine().trim();
        
        if (users.containsKey(username)) {
            System.out.println("❌ Username already exists!");
            return;
        }
        
        if (username.length() < 3) {
            System.out.println("❌ Username must be at least 3 characters long!");
            return;
        }
        
        System.out.print("Choose password: ");
        String password = scanner.nextLine().trim();
        
        if (password.length() < 4) {
            System.out.println("❌ Password must be at least 4 characters long!");
            return;
        }
        
        users.put(username, password);
        System.out.println("✅ Registration successful! You can now login.");
    }

    private static boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private static void showUserMenu(Scanner scanner, String username) {
        while (true) {
            System.out.println("\n" + "=".repeat(30));
            System.out.println("        USER DASHBOARD");
            System.out.println("=".repeat(30));
            System.out.println("Logged in as: " + username);
            System.out.println("1. View Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Logout");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n--- Your Profile ---");
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + "*".repeat(users.get(username).length()));
                    break;
                case "2":
                    changePassword(scanner, username);
                    break;
                case "3":
                    System.out.println("Logging out... Goodbye, " + username + "!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void changePassword(Scanner scanner, String username) {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();
        
        if (!users.get(username).equals(currentPassword)) {
            System.out.println("❌ Current password is incorrect!");
            return;
        }
        
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        
        if (newPassword.length() < 4) {
            System.out.println("❌ Password must be at least 4 characters long!");
            return;
        }
        
        users.put(username, newPassword);
        System.out.println("✅ Password changed successfully!");
    }
}
