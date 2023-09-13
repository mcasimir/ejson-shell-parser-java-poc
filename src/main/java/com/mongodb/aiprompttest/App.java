package com.mongodb.aiprompttest;

import org.bson.Document;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        EjsonShellParser parser = new EjsonShellParser();
        Document document = parser.parseEJSON("{x: Double(1.5)}");
        System.out.println(document.get("x"));
    }
}
