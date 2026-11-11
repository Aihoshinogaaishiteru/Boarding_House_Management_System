package boardinghouse;

import boardinghouse.model.Billing;
import boardinghouse.model.Room;
import boardinghouse.model.Tenant;
import boardinghouse.service.BoardingHouseManager;
import boardinghouse.service.FileStorage;


import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BoardingHouseManager manager =
            new BoardingHouseManager(new FileStorage("data"));
   

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose: ");
            try {
                switch (choice) {
                    case 1: addRoom(); break;
                    case 2: listRooms(); break; 
                    case 3: removeRoom(); break;
                    // case 4: checkIn(); break;
                    // case 5: listTenants(); break;
                    // case 6: checkOut(); break;
                    // case 7: recordPayment(); break;
                    // case 8: viewPayments(); break;
                    // case 9: unpaidReport(); break;
                    case 0: running = false; break;
                    default: System.out.println("Invalid choice.");
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
     // ---------- Rooms ----------
    private static void addRoom() {
        String no = readText("Room number: ");
        String type = readText("Type (e.g. Single, Double, Bedspace): ");
        double rate = readDouble("Monthly rate: ");
        int cap = readInt("Capacity (number of tenants): ");
        // manager.addRoom(no, type, rate, cap);
        System.out.println("Room added.");
    }
     private static void listRooms() {
        List<Room> rooms = manager.getRooms();
        if (rooms.isEmpty()) { System.out.println("No rooms yet."); return; }
        System.out.printf("%-8s %-12s %10s %10s%n", "Room", "Type", "Rate", "Occupancy");
        for (Room r : rooms) {
            String status = r.isFull() ? " (FULL)" : "";
            System.out.printf("%-8s %-12s %10.2f %7d/%d%s%n", r.getRoomNo(), r.getType(),
                    r.getRate(), r.getOccupantCount(), r.getCapacity(), status);
        }
    }
     private static void removeRoom() {
        manager.removeRoom(readText("Room number to remove: "));
        System.out.println("Room removed.");
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
    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Please enter a number."); }
        }
    }
}