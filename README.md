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

## 🚀 How to Run

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/ElPaisScraper.git
cd ElPaisScraper

### 2. Import it into IntelliJ / Eclipse

### 3. Add your browserstack.username and browserstack.access_key in the code

### 4. Run ElPaisScraper.java and then BrowserStackTest.java

📂 Output
Prints article titles and content

Saves images

Displays repeated words

Logs test execution on BrowserStack


---

**3. `pom.xml` Verification**  
Make sure it contains all necessary dependencies:
```xml
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
