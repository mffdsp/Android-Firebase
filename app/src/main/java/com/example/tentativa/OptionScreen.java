package com.example.tentativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);
    }

    public void sendButton(View v){
        startActivity(new Intent(getBaseContext(), SendActivity.class));
        finish();

    }
    public void getButton(View v){
        startActivity(new Intent(getBaseContext(), GetActivity.class));
        finish();


    }
    public void addF(View v){
        startActivity(new Intent(getBaseContext(), GetUsers.class));
        finish();

    }
    public void chato(View v){
        startActivity(new Intent(getBaseContext(), ChatMaybe.class));
        finish();
    }
}
