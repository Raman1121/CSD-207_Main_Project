package main.java;

import com.mongodb.*;

public class Mongo {

    public static void addDocument(String selected){
        MongoClientURI uri = new MongoClientURI("mongodb://user1:userDataPassword@ds127536.mlab.com:27536/csd-207_main_project");
        MongoClient client = new MongoClient(uri);
        DB database = client.getDB("csd-207_main_project");
        DBCollection collection = database.getCollection("UserTunes");
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("Configuration",selected);
        collection.insert(dbObject);
    }


    
}
