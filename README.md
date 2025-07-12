# 📰 ElPaisScraper

ElPaisScraper is a Java-based automation project that uses **Selenium WebDriver**, **MyMemory Translation API**, and **BrowserStack** for web scraping, translation, and cross-browser testing.

---

## 📌 Features

- ✅ Scrapes the first 5 articles from the **Opinion section** of [El País](https://elpais.com/)
- ✅ Extracts **title, content**, and **downloads images**
- ✅ Translates the **article titles** from **Spanish to English** using the **MyMemory API**
- ✅ Analyzes **repeated words** in the translated titles
- ✅ Performs **parallel cross-browser testing** using **BrowserStack**
- ✅ Saves article images locally

---

## 🛠️ Technologies Used

- Java (JDK 20+)
- Selenium WebDriver
- MyMemory Translation API
- Apache HttpClient & Gson
- BrowserStack Automate
- Maven (for dependency management)

---

## 🔄 Project Structure

ElPaisScraper/
├── pom.xml
├── images
├── /src/
│ └── /main/
│ └── /java/
│ └── com/akshay/
│ ├── Article.java
│ ├── BrowserStackTest.java
│ ├── ElPaisScraper.java
│ ├── LibreTranslateutil.java
│ └── Main.java
└── [downloaded article images]


---

##  How to Run

### 1. Clone the Repository

git clone https://github.com/your-username/ElPaisScraper.git
cd ElPaisScraper

2. Import the project into IntelliJ IDEA or Eclipse
Make sure to use a JDK version 17 or above and enable Maven support.

3. Add BrowserStack credentials
Update the code with your actual browserstack.username and browserstack.access_key.

Alternatively, you can use environment variables if preferred.

4. Run the project
First, run ElPaisScraper.java to scrape articles and download images

Then, run BrowserStackTest.java to perform parallel browser testing

📂 Output
Output
✅ Article titles and content printed to console

✅ Article images downloaded to the images/ folder

✅ Repeated words from translated titles printed

✅ Parallel browser session logs viewable on BrowserStack Dashboard


---

**3. `pom.xml` Verification**  
Make sure it contains all necessary dependencies:

<dependencies>
  <!-- Selenium -->
  <dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.21.0</version>
  </dependency>

  <!-- Apache HttpClient -->
  <dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
    <version>5.3.1</version>
  </dependency>

  <!-- Gson -->
  <dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
  </dependency>
</dependencies>
📌 Notes
Ensure your internet connection is active when running the scraper and translation API

For BrowserStack testing, free accounts are limited — plan usage accordingly

This project demonstrates a mix of automation, web scraping, API integration, and testing — all in one place
