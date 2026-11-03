package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        showHomescreen();


    }

    private static void showHomescreen(){

        String homeScreenMenuText = """
                Hey (name) what would you like to do?
                1 - Make Deposit
                2 - Make Payment(Debit)
                3 - Display ledger
                4 - Quit Application
                Please Enter your Command:
                """;

        int choice;

        do {
            choice = Console.promptForInt(homeScreenMenuText);

            switch (choice){
                case 1 -> showMakeDeposit();
                case 2 -> showMakePayment();
                case 3 -> showLedger();
                case 4 -> System.out.println("Goodbye!" + "name");
                default -> System.out.println("Invalid command, please try again. ");
            }
        } while (choice != 4);
        //Show account balance (extra)

    }

    private static void showMakeDeposit(){
        System.out.println("make Deposit");
    }

    private static void showMakePayment(){
        System.out.println("MakePayment");
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
                System.out.println(line);
                //String[] parts = line.split("\\|");

                //LocalDate date = LocalDate.parse(parts[0]);
                //LocalTime time = LocalTime.parse(parts[1]);
                //String description = parts[2];
                //String vendor = parts[3];
                //double amount = Double.parseDouble(parts[4]);

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
        System.out.println("Payments");
    }

}