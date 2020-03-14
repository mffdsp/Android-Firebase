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
        DB = FirebaseDatabase.getInstance().getReference("name");

        listaDePessoas = new ArrayList<>();

        getSupportActionBar().hide();
    }


    public void saveToDB(View v){

        String name = ((EditText) findViewById(R.id.editText3)).getText().toString();
        String data = ((EditText) findViewById(R.id.datatext)).getText().toString();

        Pessoa pessoa = new Pessoa(name, data);
        String id = DB.push().getKey();

        DB.child(id).setValue(pessoa);

        Toast.makeText(this, "SALVANDINHO", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart(){
        super.onStart();

        DB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listaDePessoas.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Pessoa post = postSnapshot.getValue(Pessoa.class);
                    listaDePessoas.add(post);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void loadDB(View v){

        for(Pessoa pessoa : listaDePessoas){
            System.out.println(pessoa.nome + " " + pessoa.data);
        }
    }

    public void goToLogin(View v){
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
        finish();
    }

}

