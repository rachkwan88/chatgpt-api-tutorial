package com.example;

import io.github.cdimascio.dotenv.Dotenv;

public class App {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
        .directory("./").load();
        String APP_ID = dotenv.get("APP_ID");

        ChatgptApi api = new ChatgptApi(APP_ID);
        String answer = api.getAnswer("What is 1+1?");
        System.out.println(answer);

    }
}