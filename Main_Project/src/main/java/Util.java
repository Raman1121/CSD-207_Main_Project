package main.java;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Util {
    public static String checkIfLoggedIn(){

        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        String userId = null;

        try{
            FileReader reader = new FileReader("lastUserLoggedIn.json");
            JSONObject loggedInUser = (JSONObject) parser.parse(reader);
            if(loggedInUser.isEmpty()){
                //Display LogIn Screen;
            }
            else{
                userId = (String) loggedInUser.get("userName");
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
}
