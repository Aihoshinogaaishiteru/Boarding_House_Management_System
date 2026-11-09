package boardinghouse;

import boardinghouse.model.Billing;
import boardinghouse.model.Room;
import boardinghouse.model.Tenant;

import java.util.Scanner;



public class Main {
    public static void main(String[] args) {  boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose: ");
            try {
                switch (choice) {
                    case 1: addRoom(); break;
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
    private static void addRoom() {
        String no = readText("Room number: ");
        String type = readText("Type (e.g. Single, Double, Bedspace): ");
        double rate = readDouble("Monthly rate: ");
        int cap = readInt("Capacity (number of tenants): ");
        manager.addRoom(no, type, rate, cap);
        System.out.println("Room added.");
}
