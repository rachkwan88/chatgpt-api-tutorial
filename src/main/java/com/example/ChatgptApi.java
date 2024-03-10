package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChatgptApi {
    private String api_key;

    public ChatgptApi(String api_key) {
        this.api_key = api_key;

    }

    public String getAnswer(String question) {
        String body = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"" + question + "\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://api.openai.com/v1/chat/completions?"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.api_key)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = response.body();
        // System.out.println(result);

        String jsonString = result; // assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);
        JSONArray choices = obj.getJSONArray("choices");

        JSONObject firstItem = choices.getJSONObject(0);
        JSONObject message = firstItem.getJSONObject("message");

        String content = message.getString("content");

        return content;

    }

}