package com.pluralsight;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class AccountLedgerApp {
    private static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        runHomeScreen();
    }

    public static void runHomeScreen() {
        boolean running = true;

        do{
            printHomeScreen();
            String choice = inputScanner.nextLine().trim().toUpperCase();

            switch (choice){
                case "D":
                    System.out.println("TODO Add Deposit");
                    break;
                case "P":
                    System.out.println("TODO Make Payment");
                    break;
                case "L":
                    System.out.println("TODO Ledger Screen");
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose D, P, L, or X");
            }
        }while (running);
    }

    public static void printHomeScreen(){
        System.out.println("===== Main Menu =====");
        System.out.println("[D] Add Deposit");
        System.out.println("[P] Make Payment");
        System.out.println("[L} Ledger");
        System.out.println("[E] Exit");
        System.out.println("Enter your choice: ");
    }


}
