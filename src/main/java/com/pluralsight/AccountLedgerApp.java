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
                    runLedgerScreen();
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

    public static void runLedgerScreen(){
        boolean subMenuRunning = true;

        do {
            printLedgerScreen();
            String choice = inputScanner.nextLine().trim().toUpperCase();

            switch (choice){
                case "A":
                    System.out.println("TODO All");
                    break;
                case "D":
                    System.out.println("TODO Deposits");
                    break;
                case "P":
                    System.out.println("TODO Payments");
                    break;
                case "R":
                    System.out.println("TODO Reports Screen");
                    break;
                case "H":
                    subMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose A, D, P, R, or H");
            }
        }while (subMenuRunning);
    }

    public static void printLedgerScreen(){
        System.out.println("===== Ledger =====");
        System.out.println("[A] All");
        System.out.println("[D] Deposits");
        System.out.println("[P] Payments");
        System.out.println("[R] Reports");
        System.out.println("[H] Home");
        System.out.println("Enter your choice");
    }
}
