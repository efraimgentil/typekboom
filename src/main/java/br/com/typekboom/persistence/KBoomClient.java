package br.com.typekboom.persistence;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class KBoomClient {

    private static KBoomClient instance;

    private MongoClient mongo;
    private DB typeKboomDB;

    private KBoomClient() {
        try {
            mongo = new MongoClient("localhost", 27017);
            typeKboomDB = mongo.getDB("typekboom");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static KBoomClient getInstance() {
        if (instance == null) {
            instance = new KBoomClient();
        }
        return instance;
    }

    public DBCollection getCollection(String collectionName) {
        return typeKboomDB.getCollection(collectionName);
    }

}