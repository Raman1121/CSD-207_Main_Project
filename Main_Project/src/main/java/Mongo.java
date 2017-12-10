package main.java;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;
import org.bson.BSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;

public class Mongo {

    public static void addDocument(String selected,String tune){
        MongoClientURI uri = new MongoClientURI("mongodb://user1:userDataPassword@ds127536.mlab.com:27536/csd-207_main_project");
        MongoClient client = new MongoClient(uri);
        DB database = client.getDB("csd-207-main-project");
        ArrayList<String> tunes = Mongo.getUserTunes(selected);
        tunes.add(tune);
        DBCollection collection = database.getCollection("UserTunes");
        BasicDBObject object = new BasicDBObject();
        object.put("username",selected);
        Cursor  cursor = collection.find(object);
        if(cursor != null){
            while(cursor.hasNext()){
                DBObject obj = cursor.next();
                String username = (String) obj.get("username");
                String pass = (String) obj.get("password");
                JSONArray array = (JSONArray) obj.get("stats");
                BasicDBObject obj1 = new BasicDBObject();
                obj1.put("username",username);
                obj1.put("password",pass);
                obj1.put("tunes",tunes);
                obj1.put("stats",array);
                collection.remove(obj);
                collection.insert(obj1);
            }
        }

    }

    public static void addUser(String username,String encryptPass){
        MongoClientURI uri = new MongoClientURI("mongodb://user1:userDataPassword@ds127536.mlab.com:27536/csd-207_main_project");
        MongoClient client = new MongoClient(uri);
        DB database = client.getDB("csd-207-main-project");
        DBCollection collection = database.getCollection("UserTunes");
        DBCollection userCollection = database.getCollection("Users");
        BasicDBObject dbObject = new BasicDBObject();
        ArrayList<String> stats = new ArrayList<>();
        ArrayList<String> tunes = new ArrayList<>();
        dbObject.put("username",username);
        dbObject.put("password",encryptPass);
        dbObject.put("tunes",tunes);
        dbObject.put("stats",stats);
        collection.insert(dbObject);
        BasicDBObject userObject = new BasicDBObject();
        userObject.put("username",username);
        userObject.put("password",encryptPass);
        userCollection.insert(userObject);
    }

    public static ArrayList<JSONObject> getData() {
        MongoClientURI uri = new MongoClientURI("mongodb://user1:userDataPassword@ds127536.mlab.com:27536/csd-207_main_project");
        MongoClient client = new MongoClient(uri);
        DB database = client.getDB("csd-207-main-project");
        DBCollection collection = database.getCollection("Users");
        Cursor cursor = collection.find();
        ArrayList<JSONObject> dbObjects = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            String user = (String) object.get("username");
            String pass = (String) object.get("password");
            JSONObject obj = new JSONObject();
            obj.put("username",user);
            obj.put("password",pass);
            dbObjects.add(obj);
        }
        return dbObjects;
    }

    public static ArrayList<String> getUserTunes(String user){
        MongoClientURI uri = new MongoClientURI("mongodb://user1:userDataPassword@ds127536.mlab.com:27536/csd-207_main_project");
        MongoClient client = new MongoClient(uri);
        DB database = client.getDB("csd-207-main-project");
        DBCollection collection =database.getCollection("UserTunes");
        BasicDBObject object = new BasicDBObject();
        object.put("username",user);
        ArrayList<String> userTunes = new ArrayList<>();
        Cursor cursor = collection.find(object);
        if(cursor == null){
        }
        else {
            while (cursor.hasNext()) {
                DBObject userData = cursor.next();
                JSONObject obj = (JSONObject) userData;
                ArrayList<JSONObject> array = (ArrayList<JSONObject>) obj.get("tunes");
                for(int i = 0;i <= array.size() - 1;i++){ ;
                    userTunes.add(tune);
                }
            }
        }
        return userTunes;
    }


}
