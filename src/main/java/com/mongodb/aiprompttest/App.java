package com.mongodb.aiprompttest;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        EjsonShellParser parser = new EjsonShellParser();
        Document filter = parser.parseDocument("{x: Double(1.5)}");
        List pipeline = parser.parseArray("[{$match: {x: Double(1.5)}}]");

        try (MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:50619/test")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("test");
            System.out.println(collection.find(filter).first());
            System.out.println(collection.aggregate(pipeline).first());
        }
    }
}
