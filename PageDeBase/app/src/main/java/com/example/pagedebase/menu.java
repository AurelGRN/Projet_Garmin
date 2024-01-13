package com.example.pagedebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class menu extends AppCompatActivity implements View.OnClickListener{

    private Button go_btn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //On cherche les boutons
        go_btn = findViewById(R.id.go_btn);



        //on met des ecouteur sur les boutons
        go_btn.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {


        // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
        if (v.getId() == R.id.go_btn) {

            //l'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
            Intent intent = new Intent(this, activite_en_cours.class); //on choisit ou on transmet

            intent.putExtra("BOUTON_CLIQUé", "activite_en_cours");

            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }

    }
}