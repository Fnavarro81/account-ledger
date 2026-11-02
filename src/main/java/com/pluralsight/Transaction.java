package com.pluralsight;

public class Transaction {

    private String localDate;
    private String localTime;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String localDate, String localTime, String description, String vendor, double amount) {
        this.localDate = localDate;
        this.localTime = localTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
