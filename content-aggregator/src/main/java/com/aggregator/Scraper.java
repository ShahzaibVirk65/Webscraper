package com.aggregator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class Scraper {
    public void scrape(String url, DataManager dataManager) {
        try {
            // Connect and fetch the webpage
            Document doc = Jsoup.connect(url).get();
            String title = doc.title();

            // Select relevant sections (adjust selector for specific websites)
            Elements articles = doc.select("article, div, section");

            for (Element article : articles) {
                // Extract headline (fallbacks for h1, h2, or h3)
                String headline = article.select("h1, h2, h3").text();

                // Extract the first link (adjust selector as needed)
                String link = article.select("a").first() != null ? article.select("a").first().absUrl("href") : "";

                // Extract summary (using the first paragraph or similar structure)
                String summary = article.select("p").first() != null ? article.select("p").first().text() : "";

                // Store data if relevant
                if (!headline.isEmpty() && !link.isEmpty()) {
                    dataManager.insertData(title, headline, link, summary);
                }
            }
            System.out.println("Scraping complete for URL: " + url);
        } catch (Exception e) {
            // Improved error logging
            System.err.println("Error scraping URL " + url + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
