/*
 * Fichier Java créé par : MOUHOUBI Wilhem
 * Groupe : Intéraction
 * Intégrateur : [Nom de l'intégrateur]
 * Date de création : 2023-11-21
 * Description : Class du menu de navigation
 * Version : V2.5
 * Dernière modification : 2023-11-29
 */

package com.example.pagedebase;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    //les bouttons
    ImageButton trajetBtn,recompBtn,goBtn,partageBtn,paramBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //on récupère les images buttons pour changer en mode sombre
        trajetBtn = findViewById(R.id.trajet_imageButton);
        recompBtn = findViewById(R.id.recompense_imageButton);
        partageBtn = findViewById(R.id.partager_imageButton);
        paramBtn = findViewById(R.id.parametre_imageButton);

        //chargement des drawable selon le thème actif

        if (isDarkActivated()){
            trajetBtn.setImageResource(R.drawable.trajets_b);
            recompBtn.setImageResource(R.drawable.recompense_b);
            partageBtn.setImageResource(R.drawable.partager_b);
            paramBtn.setImageResource(R.drawable.parametres_b);
        }
    }

    private boolean isDarkActivated(){
        return (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
    }
}