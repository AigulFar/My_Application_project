package com.example.myapplication_project;

public class Single {

    public static String user_id = null;

    public Single(String  us_id){
        user_id = us_id;
    }

    public static String getuser_id(){
        return user_id;
    }
}