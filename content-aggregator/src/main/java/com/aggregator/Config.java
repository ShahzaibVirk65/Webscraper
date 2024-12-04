package com.aggregator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private final Properties properties;

    public Config() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }

    public String[] getScraperUrls() {
        return properties.getProperty("scraper.urls", "").split(",");
    }

    public String getDatabasePath() {
        return properties.getProperty("db.path", "aggregated_data.db");
    }
}
