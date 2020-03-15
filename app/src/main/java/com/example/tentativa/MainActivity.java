package com.example.tentativa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tentativa.Data;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.Activity;
import android.app.Notification;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity<mFirebaseRef> extends AppCompatActivity {

    String FINAL_NAME = "oo";
    DatabaseReference DB;
    DatabaseReference DR;
    List<Pessoa> listaDePessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = FirebaseDatabase.getInstance().getReference("mensagens");

        InfoClass.LISTA = new ArrayList<>();

        if(InfoClass.SEND){
            boolean newText = true;
            for(TextStructure TS: InfoClass.LISTA){
                System.out.println(TS.getTitle() + " " + InfoClass.title);
                if(TS.getTitle().equals(InfoClass.title)){
                    newText = false;
                }
            }
            if(newText){
                saveToDB();
            }else Toast.makeText(MainActivity.this, "Não é possível enviar textos com titulos repetidos!", Toast.LENGTH_LONG).show();

            InfoClass.SEND = false;
        }
        ((EditText) findViewById(R.id.editText3)).setText(InfoClass.getAccountName());
        ((EditText) findViewById(R.id.datatext)).setText(InfoClass.getAccountEmail());
        getSupportActionBar().hide();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
        finish();
    }

    public void chooseWhatToDo(View v){
        startActivity(new Intent(getBaseContext(), OptionScreen.class));

    }
    public void saveToDB(){

        String id = DB.push().getKey();

        TextStructure TS = new TextStructure(InfoClass.title, InfoClass.boby, id);
        TS.EMAIL = InfoClass.getAccountEmail();

        DB.child(id).setValue(TS);

        Toast.makeText(this, "SALVANDINHO", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart(){
        super.onStart();

        DB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                InfoClass.LISTA.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                    TextStructure TS = postSnapshot.getValue(TextStructure.class);

                    if(TS.EMAIL.equals(InfoClass.ACCOUNT_EMAIL)) {
                        InfoClass.LISTA.add(TS);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void goToLogin(View v){
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
        finish();
    }

}

