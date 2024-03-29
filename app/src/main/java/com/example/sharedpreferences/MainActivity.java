package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends = new ArrayList<>();

        friends.add("Cindy");
        friends.add("Ahmed");

        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();

        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newFriends = new ArrayList<>();

        try {

            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("new friends: " + newFriends.toString());
        //sharedPreferences.edit().putString("username","Jawad").apply();

        //String username = sharedPreferences.getString("username","");

       // System.out.println(username);
    }
}
