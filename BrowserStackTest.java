package com.akshay;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrowserStackTest implements Runnable {

    private String os;
    private String osVersion;
    private String browserName;
    private String browserVersion;

    public BrowserStackTest(String os, String osVersion, String browserName, String browserVersion) {
        this.os = os;
        this.osVersion = osVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @Override
    public void run() {
        try {
            // Browser capabilities
            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", browserName);
            capabilities.setCapability("browserVersion", browserVersion);

            // BrowserStack capabilities under 'bstack:options'
            Map<String, Object> browserstackOptions = new HashMap<>();
            if (os.equalsIgnoreCase("Android") || os.equalsIgnoreCase("iOS")) {
                browserstackOptions.put("deviceName", osVersion); // use device name in place of version
                browserstackOptions.put("realMobile", "true");
            } else {
                browserstackOptions.put("os", os);
                browserstackOptions.put("osVersion", osVersion);
            }
            browserstackOptions.put("sessionName", "El Pais Test");
            browserstackOptions.put("userName", "akshaymalunjkar_Ud0KPD");
            browserstackOptions.put("accessKey", "rHwGJpNkTxrz1m3F6Ypr");

            capabilities.setCapability("bstack:options", browserstackOptions);

            RemoteWebDriver driver = new RemoteWebDriver(
                    new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);

            driver.get("https://elpais.com/opinion/");
            System.out.println("[" + browserName + "] Page Title: " + driver.getTitle());

            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[][] configs = {
                {"Windows", "11", "chrome", "latest"},
                {"Windows", "11", "firefox", "latest"},
                {"OS X", "Ventura", "safari", "latest"},
                {"Windows", "11", "edge", "latest"},
                {"Android", "Samsung Galaxy S22", "chrome", "latest"}


        };

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (String[] config : configs) {
            executor.submit(new BrowserStackTest(config[0], config[1], config[2], config[3]));
        }

        executor.shutdown();
    }
}
