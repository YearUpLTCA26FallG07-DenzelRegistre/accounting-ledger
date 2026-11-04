package com.pluralsight;


import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        showHomescreen();
        ArrayList<Transaction> transactions = new ArrayList<>();



    }

    private static void showHomescreen(){

        String homeScreenMenuText = """
                What would you like to do?
                1 - Add Deposit
                2 - Make Payment(Debit)
                3 - Display ledger
                4 - Quit Application
                Please Enter your Command:
                """;

        int choice;

        do {
            choice = Console.promptForInt(homeScreenMenuText);

            switch (choice){
                case 1 -> showAddDeposit();
                case 2 -> showMakePayment();
                case 3 -> showLedger();
                case 4 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid command, please try again. ");
            }
        } while (choice != 4);
        //Show account balance (extra)

    }

    private static void showAddDeposit(){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DecimalFormat df = new DecimalFormat("0.00");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String description = "Deposit";
        String vendor = Console.promptForString("Who depositing: ");
        double amount = Console.promptForDouble("Enter the amount: ");

        try {

            FileWriter writer = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter =new BufferedWriter(writer);

            bufWriter.write("\n" + date + "|" + time.format(timeFormatter) + "|" + description + "|" + vendor + "|" + amount);

            bufWriter.close();
        } catch (IOException e) {
            System.out.println("couldn't write");
        }

    }

    private static void showMakePayment(){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DecimalFormat df = new DecimalFormat("0.00");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String description = Console.promptForString("What the payment for?: ");
        String vendor = Console.promptForString("Who you paying? : ");
        double amount = Console.promptForDouble("Enter the amount: ");

        try {

            FileWriter writer = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(writer);

            bufWriter.write("\n" + date + "|" + time.format(timeFormatter) + "|" + description + "|" + vendor + "|" +df.format(amount));

            bufWriter.close();
            System.out.println("\n Payment successful");
        } catch (IOException e) {
            System.out.println("Couldn't send payment");
        }

    }
    private static void showLedger(){

        String ledgerMenuText = """
                What do you want to do in the ledger?
                1 - Display all entries
                2 - Display deposits
                3 - Display payments
                4 - Display reports
                5 - Go back to Main menu
                """;
        int choice2;
        do {
            choice2 = Console.promptForInt(ledgerMenuText);

            switch (choice2){
                case 1 -> showAllEntries();
                case 2 -> showDeposits();
                case 3 -> showPayments();
                case 4 -> showReports();
                case 5 -> {
                    break;
                }
                default -> System.out.println("Invalid command, please try again. ");
            }
        } while (choice2 != 5);


    }
    private static void showReports(){
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine())!= null){

                String[] parts = line.split("\\|");

                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                System.out.println(line);
                //Transactions T = new Transactions(date, time, description, vendor, amount);
                //date|time|description|vendor|amount
                //2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
                //2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00

            }
            bufferedReader.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void showAllEntries(){
        System.out.println("all entries");
    }
    private static void showDeposits(){
        System.out.println("deposits");
    }
    private static void showPayments(){
        System.out.println("For Payments");


    }

}