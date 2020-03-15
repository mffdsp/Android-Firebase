package com.example.tentativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.FontRequest;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class GetActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    String bodyToShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        getSupportActionBar().setTitle("Listinha");

        textView = findViewById(R.id.bodyText);
        listView = findViewById(R.id.TextList);

        ArrayAdapter<TextStructure> adapter = new ArrayAdapter<TextStructure>(this, android.R.layout.simple_list_item_1, InfoClass.LISTA);

        listView.setAdapter(adapter);

        for(TextStructure TS: InfoClass.LISTA){
            if(TS.EMAIL.equals(InfoClass.ACCOUNT_EMAIL)) {
                System.out.println(TS.getTitle());
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String buscaID =  ((TextStructure)listView.getItemAtPosition(position)).getID();
                for(TextStructure TS: InfoClass.LISTA){
                    if(TS.getID().equals(buscaID)){
                        ((TextView) textView).setText(TS.getBody());
                    }
                }
            }
        });
    }
}
