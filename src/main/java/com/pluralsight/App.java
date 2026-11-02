package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class App {
    private static Scanner inputScanner = new Scanner(System.in);
    private static ArrayList<Transaction> transactions;

    public static void main(String[] args) {
        runHomeScreen();
    }

    public static void runHomeScreen() {
        boolean running = true;

        do {
            printHomeScreen();
            String choice = inputScanner.nextLine().trim().toUpperCase();

            switch (choice) {
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
        } while (running);
    }

    public static void printHomeScreen() {
        System.out.println("===== Main Menu =====");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.println("Enter your choice: ");
    }

    public static void addDeposit(Scanner inputScanner) {
        System.out.println("\n===== Record Deposit =====");

        String description = promptForString(inputScanner, "Enter description: ");
        String vendor = promptForString(inputScanner, "Enter vendor: ");
        double amount = promptForDouble(inputScanner, "Enter amount(must be more than 0): ");
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String recordLine = String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor, amount);

        try (PrintWriter writer = new PrintWriter(new FileWriter("transaction.csv", true))){
            writer.println(recordLine);
            System.out.println("Deposit successfully recorded and saved to transactions.csv!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to transactions.csv: " + e.getMessage());
        }
    }

    public static String promptForString(Scanner inputScanner, String prompt) {
        String input;
        while (true) {
            System.out.println(prompt);
            input = inputScanner.nextLine().trim();
            if (!input.isEmpty()){
                return input;
            }
            System.out.println("Input cannot be blank. Please try again.");
        }
    }

    public static double promptForDouble(Scanner inputScanner, String prompt){
        while (true) {
            System.out.println(prompt);
            String input = inputScanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                }
                System.out.println("Value must be greater than 0. Please try again.");
            }catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid number amount.");
            }
        }
    }

    public static void runLedgerScreen() {
        boolean subLedgerMenuRunning = true;

        do {
            printLedgerScreen();
            String choice = inputScanner.nextLine().trim().toUpperCase();

            switch (choice) {
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
                    runReportsScreen();
                    break;
                case "H":
                    subLedgerMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose A, D, P, R, or H");
            }
        } while (subLedgerMenuRunning);
    }

    public static void printLedgerScreen() {
        System.out.println("===== Ledger =====");
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.println("Enter your choice: ");
    }

    public static void runReportsScreen() {
        boolean subReportsScreenRunning = true;

        do {
            printReportsScreen();
            String choice = inputScanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    System.out.println("TODO Month to Date");
                    break;
                case "2":
                    System.out.println("TODO Previous Month");
                    break;
                case "3":
                    System.out.println("TODO Year to Date");
                    break;
                case "4":
                    System.out.println("TODO Previous Year");
                    break;
                case "5":
                    System.out.println("TODO Search by Vendor");
                    break;
                case "0":
                    subReportsScreenRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, 3, 4, 5, or 0");
            }
        } while (subReportsScreenRunning);
    }

    public static void printReportsScreen() {
        System.out.println("===== Reports =====");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("0) Back");
        System.out.println("Enter your choice: ");
    }
}
