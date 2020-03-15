package com.example.tentativa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    //Criar lista com "Notas"
    //Criar objeto Notas, upar pro FireBase;
    //Dar o get nesse objeto, igual fiz com "pessoa"
    //Manipular titulo e corpo da Nota, ao clicar em alguem da lista, retorna O titulo e o corpo

    private SignInButton SB;
    private Button SOB;
    private GoogleSignInClient GSC;
    private FirebaseAuth FBA;
    private int RC_SIGN_IN = 1;
    private ImageView profileImage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Mudar cor
        getWindow().getDecorView().setBackgroundColor(Color.rgb(255,70, 0));
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SB = findViewById(R.id.gbutton);
        SOB = findViewById(R.id.signOutButton);
        FBA = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.INVISIBLE);

        GoogleSignInOptions GSO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        GSC = GoogleSignIn.getClient(this, GSO);

        SB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIntent = GSC.getSignInIntent();
                startActivityForResult(signIntent, RC_SIGN_IN);
            }
        });

        SOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GSC.signOut();
                Toast.makeText(LoginActivity.this, "Log Out", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleResult(task);
        }
    }

    public void handleResult(Task<GoogleSignInAccount> task){
        try{
            GoogleSignInAccount acc = task.getResult(ApiException.class);
            //Toast.makeText(LoginActivity.this, "Sucesso", Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(acc);
        }catch (ApiException e){
            Toast.makeText(LoginActivity.this, "ERRO AO LOGAR", Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(null );

        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acc){
        AuthCredential AC = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
        FBA.signInWithCredential(AC).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "SUCESSO", Toast.LENGTH_LONG).show();
                    FirebaseUser user = FBA.getCurrentUser();
                    updateUI(user);
                }else{
                    Toast.makeText(LoginActivity.this, "ERROZINHO", Toast.LENGTH_LONG).show();
                    updateUI(null);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateUI(FirebaseUser user){

        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(acc != null){

            //Salvando nas variaveis globais ihu
            InfoClass.setAccountName(acc.getDisplayName());
            InfoClass.setAccountEmail(acc.getEmail());
            InfoClass.setAccountId(acc.getId());
            InfoClass.setAccountPhoto(acc.getPhotoUrl());

            Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    finish();
                }
            }, 2000);
            //((TextView) findViewById(R.id.infoText)).setText(InfoClass.getAccountName() + "\n" + InfoClass.getAccountEmail());

          //  setProfileImage(accPhoto.toString());

        }

    }

}
