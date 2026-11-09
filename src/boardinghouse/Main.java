package boardinghouse;

import boardinghouse.model.Billing;
import boardinghouse.model.Room;
import boardinghouse.model.Tenant;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    // private static final BoardingHouseManager manager = 
    // new BoardingHouseManager(new FileStorage(folder: "data"));

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose: ");
            try {
                switch (choice) {
                    // case 1: addRoom(); break;
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    

    public static void printMenu() {
        System.out.println("\n============BOARDING HOUSE MANAGEMENT SYSTEM============\n");

        System.out.println("\n===Room Management===");
        System.out.println("1. Add Room");
        System.out.println("2. List Room");
        System.out.println("3. Update Room");

        System.out.println("\n===Check-in Management===");
        System.out.println("4. Add Tenant");
        System.out.println("5. List Tenant");
        System.out.println("6. Remove Tenant");

        System.out.println("\n===Billing Management===");
        System.out.println("7. Record Payment");
        System.out.println("8. View Tenant Payment");
        System.out.println("9. Unpaid rent Report");

        System.out.println("\n===System===");
        System.out.println("0. Exit");

    }

    // ---------- Input helpers ----------
    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim().replace(",", " "); // commas would break the CSV
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Please enter a whole number."); }
        }
    }
}