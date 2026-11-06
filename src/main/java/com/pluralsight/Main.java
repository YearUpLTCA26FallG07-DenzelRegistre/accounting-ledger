package com.pluralsight;


import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.time.LocalDate.parse;

public class Main {
    public static void main(String[] args) {

        showHomescreen();

    }
    public static ArrayList<Transaction> transactions = new ArrayList<>() {
    };

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


    }

    private static void showAddDeposit(){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DecimalFormat df = new DecimalFormat("0.00");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String description = "Direct deposit";
        String vendor = Console.promptForString("Who depositing: ");
        double amount = Console.promptForDouble("Enter the amount: ");

        try {

            FileWriter writer = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter =new BufferedWriter(writer);

            bufWriter.write("\n" + date + "|" + time.format(timeFormatter) + "|" + description + "|" + vendor + "|" + amount);

            bufWriter.close();
            System.out.println("Deposit successful");
        } catch (IOException e) {
            System.out.println("couldn't write");
        }

    }

    private static void showMakePayment(){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DecimalFormat df = new DecimalFormat("0.00");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String description = "Payment";
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
        String reportMenuText = """
            Reports
            1 - Month To Date
            2 - Previous Month
            3 - Year To Date
            4 - Previous Year
            5 - Search by Vendor
            0 - Back to Ledger
            """;

        int choice3;
        do {
            choice3 = Console.promptForInt(reportMenuText);
            switch (choice3){
                case 1 -> showMonthToDate();
                case 2 -> showPreviousMonth();
                case 3 -> showYearToDate();
                case 4 -> showPreviousYear();
                case 5 -> searchByVendor();
                case 0 -> System.out.println("Going back to ledger. ");
                default -> System.out.println("Invalid command, please try again. ");

            }
        } while (choice3 != 0);
    }
    private static void showAllEntries(){
        DateTimeFormatter timeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        transactions.clear();

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

                Transaction t = new Transaction(date , time , description , vendor , amount);


                transactions.add(t);


            }
            bufferedReader.close();

            for (Transaction t : transactions) {
                System.out.printf(
                        "%s  %s  %s  %s  $%.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
    private static void showDeposits(){

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

                if (amount > 0){
                    System.out.printf(
                            "%s | %s | %s | %s | $%.2f%n",
                            date, time, description, vendor, amount
                    );

                }

            }
            bufferedReader.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void showPayments(){
            try {
                BufferedReader bufferedReader =
                        new BufferedReader(new FileReader("transactions.csv"));

                String line;
                bufferedReader.readLine();

                while ((line = bufferedReader.readLine()) != null) {

                    String[] parts = line.split("\\|");

                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    if (amount < 0) {

                        System.out.printf(
                                "%s | %s | %s | %s | $%.2f%n",
                                date, time, description, vendor, amount
                        );
                    }
                }

                bufferedReader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }



    }
    private static void showMonthToDate() {
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);

    }

    private static void showPreviousMonth() {
        LocalDate today = LocalDate.now();
        LocalDate start = today.minusMonths(1).withDayOfMonth(1);
        LocalDate end = today.withDayOfMonth(1).minusDays(1);

    }

    private static void showYearToDate() {
        LocalDate today = LocalDate.now();
        LocalDate start = LocalDate.of(today.getYear(), 1, 1);

    }

    private static void showPreviousYear() {
        int lastYear = LocalDate.now().getYear() - 1;
        LocalDate start = LocalDate.of(lastYear, 1, 1);
        LocalDate end = LocalDate.of(lastYear, 12, 31);

    }
    private static void searchByVendor() {

        String searchVendor =
                Console.promptForString("Enter vendor name: ");

        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader("transactions.csv"));

            String line;
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {

                String[] parts = line.split("\\|");

                String vendor = parts[3];

                if (vendor.equalsIgnoreCase(searchVendor)) {

                    System.out.printf(
                            "%s | %s | %s | %s | $%.2f%n",
                            parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])
                    );
                }
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}