package com.aggregator;

import java.sql.*;

public class DataManager {
    private final String dbPath;

    public DataManager(String dbPath) {
        this.dbPath = dbPath;
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stmt = conn.createStatement()) {
            String createTable = "CREATE TABLE IF NOT EXISTS articles (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "source TEXT, " +
                    "headline TEXT, " +
                    "link TEXT, " +
                    "summary TEXT)";
            stmt.execute(createTable);
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    public void insertData(String source, String headline, String link, String summary) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO articles (source, headline, link, summary) VALUES (?, ?, ?, ?)")) {
            pstmt.setString(1, source);
            pstmt.setString(2, headline);
            pstmt.setString(3, link);
            pstmt.setString(4, summary);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }

    public void displayData() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM articles")) {

            while (rs.next()) {
                System.out.println("Source: " + rs.getString("source"));
                System.out.println("Headline: " + rs.getString("headline"));
                System.out.println("Link: " + rs.getString("link"));
                System.out.println("Summary: " + rs.getString("summary"));
                System.out.println("--------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error displaying data: " + e.getMessage());
        }
    }
}
