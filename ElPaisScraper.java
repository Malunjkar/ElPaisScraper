package com.akshay;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class ElPaisScraper {

    public static void analyzeRepeatedWords(List<String> titles) {
        Map<String, Integer> wordCount = new HashMap<>();

        for (String title : titles) {
            String[] words = title.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");
            for (String word : words) {
                if (word.length() > 1) {
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        }

        System.out.println("\n🔁 Repeated Words in Titles:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
            }
        }
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        List<Article> articleList = new ArrayList<>();





        try {
            driver.get("https://elpais.com/");
            Thread.sleep(3000);

            // Accept cookie banner
            try {
                WebElement acceptCookies = driver.findElement(By.xpath("//button[contains(., 'Aceptar')]"));
                if (acceptCookies.isDisplayed()) {
                    acceptCookies.click();
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                System.out.println("ℹ️ No cookie banner to accept or already dismissed.");
            }

            // Click on Opinión section
            WebElement opinionLink = driver.findElement(By.xpath("//a[contains(@href,'/opinion/') and text()='Opinión']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opinionLink); // ✅ JavaScript click

            Thread.sleep(3000);

            // Get top 5 article URLs
            List<WebElement> articles = driver.findElements(By.cssSelector("article a"));
            List<String> articleLinks = new ArrayList<>();
            List<String> translatedTitles = new ArrayList<>();


            for (WebElement a : articles) {
                String link = a.getAttribute("href");
                if (link != null && link.contains("/opinion/") && !articleLinks.contains(link)) {
                    articleLinks.add(link);
                }
                if (articleLinks.size() == 5) break;
            }

            // Visit each article
            for (String url : articleLinks) {
                driver.get(url);
                Thread.sleep(3000);

                String title = "(No title found)";

                String content;
                String imageUrl;


                try {
                    // Extract title
                    WebElement titleEl = driver.findElement(By.tagName("h1"));
                    title = titleEl.getText();
                } catch (Exception e) {
                    title = "(No title found)";
                }
                String translatedTitle = LibreTranslateUtil.translateWithMyMemory(title, "es", "en");
                System.out.println("🔤 Translated Title: " + translatedTitle);
                translatedTitles.add(translatedTitle);
                try {
                    List<WebElement> paragraphs = driver.findElements(By.cssSelector(
                            "div[data-dtm-region='articulo_cuerpo'] p, div[data-test='article-body'] p"
                    ));
                    StringBuilder contentBuilder = new StringBuilder();
                    for (WebElement p : paragraphs) {
                        String para = p.getText().trim();
                        if (!para.isEmpty()) {
                            contentBuilder.append(para).append("\n");
                        }
                    }
                    content = contentBuilder.toString().trim();
                } catch (Exception e) {
                    content = "(No content found)";
                }


                try {
                    // Extract cover image
                    WebElement imageEl = driver.findElement(By.cssSelector("figure img"));
                    imageUrl = imageEl.getAttribute("src");

                    // Download image
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        saveImage(imageUrl, title.replaceAll("[^a-zA-Z0-9]", "_") + ".jpg");
                    }
                } catch (Exception e) {
                    imageUrl = "(No image found)";
                }

                articleList.add(new Article(title, content, imageUrl));
            }

            // Print final result
            System.out.println("\n🧾 Article Details:");
            for (Article a : articleList) {
                System.out.println("\n=========================");
                System.out.println("Title: " + a.title);
                System.out.println("Image: " + a.imageUrl);
                System.out.println("Content:\n" + a.content);
            }
            // Analyze repeated words in translated titles
            analyzeRepeatedWords(translatedTitles);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // Helper to save image
    public static void saveImage(String imageUrl, String fileName) {
        try {
            File dir = new File("images");
            if (!dir.exists()) dir.mkdir();

            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.connect();

            try (InputStream in = connection.getInputStream();
                 FileOutputStream out = new FileOutputStream("images/" + fileName)) {

                byte[] buffer = new byte[2048];
                int length;

                while ((length = in.read(buffer)) != -1) {
                    out.write(buffer, 0, length);
                }
            }

            System.out.println("🖼️ Image saved: " + fileName);

        } catch (IOException e) {
            System.out.println("⚠️ Failed to download image: " + imageUrl);
        }
    }

}
