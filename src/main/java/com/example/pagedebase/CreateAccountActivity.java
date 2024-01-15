package com.example.pagedebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity  {

    private TextView btnConnectCreate,textViewErrorCreate ;
    private EditText editTextUserCreate, editTextPasswordCreate;

    private Button btnCreateCreate;

    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btnConnectCreate = findViewById(R.id.btnConnectCreate);
        textViewErrorCreate = findViewById(R.id.textViewErrorCreate);
        editTextUserCreate = findViewById(R.id.editTextUserCreate);
        editTextPasswordCreate = findViewById(R.id.editTextPasswordCreate);
        btnCreateCreate = findViewById(R.id.btnCreateCreate);

        btnCreateCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editTextUserCreate.getText().toString();
                password = editTextPasswordCreate.getText().toString();

                // lancer la requete pour connecter l'utilisateur
            }
        });

        btnConnectCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainActivity);
            }
        });


    }


}
