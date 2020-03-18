package com.example.tentativa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChatMaybe extends AppCompatActivity {

    DatabaseReference DB;
    List<String> chatString;
    ListView CHAT;
    EditText textBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DB = FirebaseDatabase.getInstance().getReference("chat");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_maybe);
        getSupportActionBar().hide();

        chatString = new ArrayList<>();
        textBox = findViewById(R.id.chatBox);
        CHAT = findViewById(R.id.CHATO);

        //saveToDB();

    }

    public void saveToDB(View v){

        String id = DB.push().getKey();

        String currentEmail = InfoClass.getAccountEmail();
        int indexof =  currentEmail.indexOf("@");
        String userName = currentEmail.substring(0, indexof);

        String realMsg = textBox.getText().toString();
        String msg = userName + ": " + realMsg;

        DB.child(id).setValue(msg);

        //Toast.makeText(this, "Send?", Toast.LENGTH_LONG).show();
        if(realMsg.equals("klaplaucius")){
            Toast.makeText(ChatMaybe.this, "KLAPAUCIUS", Toast.LENGTH_SHORT).show();
        DB.setValue(null);
        }
        textBox.setText("");

    }


    @Override
    protected void onStart(){
        super.onStart();

        DB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                chatString.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                    String TS = postSnapshot.getValue(String.class);
                    chatString.add(TS);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChatMaybe.this, android.R.layout.simple_list_item_1, chatString);
                CHAT.setAdapter(adapter);
                CHAT.setSelection(adapter.getCount() - 1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
