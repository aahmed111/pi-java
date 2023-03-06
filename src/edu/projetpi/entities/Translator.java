/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.entities;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {
    public static void main(String[] args) throws Exception {
        String text = "Bonjour, comment ça va?";
        String fromLang = "fr";
        String toLang = "en";

        // Build URL
        String urlString = "https://api.microsofttranslator.com/V2/Http.svc/Translate?text=" + URLEncoder.encode(text, "UTF-8") +
                "&from=" + fromLang + "&to=" + toLang;

        // Create URL object
        URL url = new URL(urlString);

        // Create HTTP connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", "your_subscription_key");

        // Get response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Extract translated text
        String translatedText = response.toString().replaceAll("<.*?>", "");

        // Print translated text
        System.out.println("Original text: " + text);
        System.out.println("Translated text: " + translatedText);
    }
}


