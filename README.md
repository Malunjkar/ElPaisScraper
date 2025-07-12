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
