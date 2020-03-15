package com.example.tentativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
    }

    public void send(View v){
        String title = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String body = ((EditText) findViewById(R.id.editText)).getText().toString();

        InfoClass.SEND = true;
        InfoClass.title = title;
        InfoClass.boby = body;

        Toast.makeText(SendActivity.this, "Enviado com Sucesso!", Toast.LENGTH_LONG).show();

        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();

    }
}
