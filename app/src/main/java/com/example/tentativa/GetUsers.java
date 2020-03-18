package com.example.tentativa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GetUsers extends AppCompatActivity {

    ListView listView;
    List<String> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_users);
        getSupportActionBar().hide();

        listView = findViewById(R.id.userList);
        userList = new ArrayList<>();


        for(String TS : InfoClass.USERS){

            String currentEmail = TS;
            int indexof =  currentEmail.indexOf("@");
            String userName = currentEmail.substring(0, indexof);

            userList.add(userName);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userList);

        listView.setAdapter(adapter);


    }
}
