package boardinghouse;

import boardinghouse.model.Billing;
import boardinghouse.model.Room;
import boardinghouse.model.Tenant;

import java.util.Scanner;



public class Main {

    // private static final Scanner in = new Scanner (System.in);
    // private static final BoardingHouseManager manager = 
    // new BoardingHouseManager(new FileStorage(folder: "data"));

    public static void main(String[] args) {
       
    }
    public static void printMenu() {
        System.out.println("\n============BOARDING HOUSE MANAGEMENT SYSTEM============\n\n");

        System.out.println("===Room Management===\n");
        System.out.println("1. Add Room");
        System.out.println("2. List Room");
        System.out.println("3. Update Room");

        System.out.println("===Check-in Management===\n");
        System.out.println("4. Add Tenant");
        System.out.println("5. List Tenant");
        System.out.println("6. Remove Tenant");

        System.out.println("===Billing Management===\n");
        System.out.println("7. Record Payment");
        System.out.println("8. View Tenant Payment");
        System.out.println("9. Unpaid rent Report");

        System.out.println("===System===\n");
        System.out.println("0. Exit");


        
    }
}
