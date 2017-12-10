package main.java;

import com.mongodb.DBObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Util {
    public static String checkIfLoggedIn(){

        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        String userId = null;

        try{
            FileReader reader = new FileReader("lastUserLoggedIn.json");
            JSONObject loggedInUser = (JSONObject) parser.parse(reader);
            if(loggedInUser.isEmpty()){
            }
            else{
                userId = (String) loggedInUser.get("username");
            }
        }
        catch(IOException e){
            System.out.println("#Util.java IOException " + e);
        }
        catch(ParseException e){
            System.out.println("#Util.java ParseException " + e);
        }
        return userId;
    }

    public static void setCurrentUser(String username){
        try{
            FileWriter writer = new FileWriter("lastUserLoggedIn.json");
            JSONObject object = new JSONObject();
            object.put("username",username);
            writer.write(object.toJSONString());
            writer.flush();
        }
        catch(IOException e){
            System.out.println("#Util.java IOException " + e);
        }
    }

    public static void checkUser(String username,String pass){
        ArrayList<JSONObject> data = Mongo.getData();
        if(data != null) {
            for (JSONObject object : data) {
                String user = (String) object.get("username");
                String decryptPass = (String) object.get("password");
                if (user.equals(username) && AES.decrypt(decryptPass,"CapsLock").equals(AES.decrypt(pass,"CapsLock"))) {
                    Util.setCurrentUser(user);
                    new BeatBox(user).run();
                } else {
                    login.showMessage();
                }
            }
        }
        else{
            System.out.println("1");
        }
    }
}
