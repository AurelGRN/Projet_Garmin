package com.example.pagedebase.GO;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.pagedebase.R;

public class activite_enregistrement extends AppCompatActivity implements View.OnClickListener {

    private Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_enregistrement);

        // On cherche les boutons
        btnsave = findViewById(R.id.btnsave);

        // On met des écouteurs sur les boutons
        btnsave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
        if (v.getId() == R.id.btnsave) {
            // L'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
            Intent intent = new Intent(this, com.example.pagedebase.menu.class); // On choisit où on transmet
            intent.putExtra("BOUTON_CLIQUÉ", "menu");

            // Démarrer l'activité avec l'intention
            startActivity(intent);
        }
    }
}
