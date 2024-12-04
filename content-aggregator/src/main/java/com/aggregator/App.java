package com.aggregator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scraper scraper = new Scraper();
        DataManager dataManager = new DataManager("aggregated_data.db");

        System.out.println("Welcome to the Content Aggregator!");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Scrape a URL");
            System.out.println("2. View aggregated data");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the URL to scrape: ");
                    String url = scanner.nextLine();
                    System.out.println("Scraping data from: " + url);
                    scraper.scrape(url, dataManager);
                    break;
                case 2:
                    System.out.println("Aggregated Data:");
                    dataManager.displayData();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
