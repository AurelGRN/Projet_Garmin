package com.example.pagedebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connexion = findViewById(R.id.btnLogIn);

        //écouteurs
        connexion.setOnClickListener(this);
    }

    public void onClick(View v){
        Intent intent = new Intent (this,MenuActivity.class);
        startActivity(intent);
    }
}