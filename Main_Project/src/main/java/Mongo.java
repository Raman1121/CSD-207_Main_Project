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

    private static MongoClientURI uri = new MongoClientURI("mongodb://user1:userDataPassword@ds127536.mlab.com:27536/csd-207_main_project");
    private static MongoClient client = new MongoClient(uri);
    private static DB database = client.getDB("csd-207-main-project");

    public static void addDocument(String selected,String tune){
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
        System.out.println(userObject);
        userCollection.insert(userObject);
    }

    public static ArrayList<JSONObject> getData() {
        DBCollection collection = database.getCollection("Users");
        Cursor cursor = collection.find();
        ArrayList<JSONObject> dbObjects = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            System.out.println(object);
            JSONObject obj = (JSONObject) object;
            dbObjects.add(obj);
        }
        return dbObjects;
    }

    public static ArrayList<String> getUserTunes(String user){
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
                JSONArray array = (JSONArray) obj.get("tunes");
                for(int i = 0;i <= array.size() - 1;i++){
                    String tune = (String) array.get(i);
                    userTunes.add(tune);
                }
            }
        }
        return userTunes;
    }


}
