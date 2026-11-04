package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transactions {
    LocalDate date;
    LocalTime time;
    private String description;
    private String vendor;
    private double amount;


    public Transactions(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.vendor = vendor;
        this.amount = amount;
        return;
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
