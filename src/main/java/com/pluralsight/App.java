package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class App {
    private static Scanner inputScanner = new Scanner(System.in);
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        loadTransactions();
        runHomeScreen();
    }

    public static void runHomeScreen() {
        boolean running = true;

        do {
            printHomeScreen();
            String choice = inputScanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
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
        System.out.println("===== Home =====");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("Enter your choice: ");
    }

    public static void addDeposit() {
        System.out.println("\n===== Record Deposit =====");

        String description = promptForString(inputScanner, "Enter description: ");
        String vendor = promptForString(inputScanner, "Enter vendor: ");
        double amount = promptForDouble(inputScanner, "Enter amount(must be more than 0): ");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(newTransaction);

        try (PrintWriter writer = new PrintWriter(new FileWriter("transactions.csv", true))) {
            writer.println(newTransaction.toCSV());
            System.out.println("Deposit successfully recorded and saved to transactions.csv!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to transactions.csv: " + e.getMessage());
        }
        pressEnterToContinue();
    }

    public static void makePayment() {
        System.out.println("\n===== Record Payment =====");

        String description = promptForString(inputScanner, "Enter description: ");
        String vendor = promptForString(inputScanner, "Enter vendor: ");
        double positiveAmount = promptForDouble(inputScanner, "Enter amount (must be more than 0): ");
        double negativeAmount = -positiveAmount;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Transaction newTransaction = new Transaction(date, time, description, vendor, negativeAmount);
        transactions.add(newTransaction);

        try (PrintWriter writer = new PrintWriter(new FileWriter("transactions.csv", true))) {
            writer.println(newTransaction.toCSV());
            System.out.println("Payment successfully recorded and saved to transactions.csv!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to transactions.csv: " + e.getMessage());
        }
        pressEnterToContinue();
    }

    public static String promptForString(Scanner inputScanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = inputScanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be blank. Please try again.");
            } else if (input.contains("|")) {
                System.out.println("input can not contain a ('|') pipe character");
            } else {
                return input;
            }
        }
    }

    public static double promptForDouble(Scanner inputScanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = inputScanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                }
                System.out.println("Value must be greater than 0. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid number amount.");
            }
        }
    }

    public static void runLedgerScreen() {
        boolean isRunning = true;

        do {
            printLedgerScreen();
            String choice = inputScanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    printAllTransactions();
                    break;
                case "D":
                    printDeposits();
                    break;
                case "P":
                    printPayments();
                    break;
                case "R":
                    runReportsScreen();
                    break;
                case "H":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose A, D, P, R, or H");
            }
        } while (isRunning);
    }

    public static void printLedgerScreen() {
        System.out.println("===== Ledger =====");
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.print("Enter your choice: ");
    }

    public static void printAllTransactions() {
        System.out.println("===== All Transactions =====");
        printTransactionTable(transactions);
        pressEnterToContinue();

    }

    public static void printDeposits() {
        System.out.println("===== Deposits =====");
        ArrayList<Transaction> deposits = new ArrayList<>();
        for (Transaction transaction: transactions) {
            if (transaction.getAmount() > 0){
                deposits.add(transaction);
            }
        }
        printTransactionTable(deposits);
        pressEnterToContinue();
    }

    public static void printPayments() {
        System.out.println("===== Payments =====");
        ArrayList<Transaction> payments = new ArrayList<>();
        for (Transaction transaction: transactions) {
            if (transaction.getAmount() < 0){
                payments.add(transaction);
            }
        }
        printTransactionTable(payments);
        pressEnterToContinue();
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
        System.out.print("Enter your choice: ");
    }

    public static void loadTransactions() {
        transactions.clear();
        boolean isHeader = true;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    LocalDate date = LocalDate.parse(parts[0].trim(), dateFormatter);
                    LocalTime time = LocalTime.parse(parts[1].trim(), timeFormatter);
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing transactions.csv found.");
        }
    }

    public static ArrayList<Transaction> getTransactionsByMonth(int year, int month) {
        ArrayList<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == year && t.getDate().getMonthValue() == month) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public static ArrayList<Transaction> getTransactionsByYear(int year) {
        ArrayList<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == year) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public static ArrayList<Transaction> getTransactionsByVendor(String vendorName) {
        ArrayList<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                filtered.add(transaction);
            }
        }
        return filtered;
    }

    public static void pressEnterToContinue() {
        System.out.println();
        System.out.println("Press enter to go back to the menu...");
        inputScanner.nextLine();
    }

    public static void printTransactionTable(java.util.List<Transaction> transactionList) {
        if (transactionList.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.printf("%-12s | %-10s | %-25s | %-20s | %12s%n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("----------------------------------------------------------------------------------------");

        for (Transaction transaction : transactionList) {
            System.out.printf("%-12s | %-10s | %-25s | %-20s | $%11.2f%n",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
        }
    }
}
