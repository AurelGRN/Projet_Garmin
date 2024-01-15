package com.example.pagedebase;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pagedebase.Equipe.equipe_creation;
import com.example.pagedebase.Equipe.equipe_creation_ou_rejoindre;


public class menu extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //On cherche les boutons
        Button go_btn = findViewById(R.id.go_btn);
        ImageButton parametre_imageButton = findViewById(R.id.parametre_imageButton);




        //on met des ecouteur sur les boutons
        go_btn.setOnClickListener(this);
        parametre_imageButton.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {


        // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
        if (v.getId() == R.id.go_btn) {

            //l'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
            Intent intent = new Intent(this, com.example.pagedebase.GO.activite_en_cours.class); //on choisit ou on transmet

            intent.putExtra("BOUTON_CLIQUé", "activite_en_cours");

            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }else if (v.getId() == R.id.parametre_imageButton) {

            Intent intent = new Intent(this, equipe_creation_ou_rejoindre.class);
            intent.putExtra("BOUTON_CLIQUé", "equipe_creation");
            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }

    }
}