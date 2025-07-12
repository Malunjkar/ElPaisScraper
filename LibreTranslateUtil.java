package com.akshay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LibreTranslateUtil {

    public static String translateWithMyMemory(String text, String sourceLang, String targetLang) {
        try {
            String url = "https://api.mymemory.translated.net/get?q=" +
                    URLEncoder.encode(text, "UTF-8") +
                    "&langpair=" + sourceLang + "|" + targetLang;

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) responseBuilder.append(line);
            reader.close();

            JsonObject json = JsonParser.parseString(responseBuilder.toString()).getAsJsonObject();
            return json.getAsJsonObject("responseData").get("translatedText").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return "[Translation Error: MyMemory API failed]";
        }
    }


}
