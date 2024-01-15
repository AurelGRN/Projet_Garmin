package com.example.pagedebase.GO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.pagedebase.R;

public class activite_en_cours extends AppCompatActivity implements View.OnClickListener {

    private Button btnstop;
    private Chronometer chronometer;
    private boolean isChronometerRunning = false;
    private long pauseOffset;

    // Dans votre méthode onCreate de activite_en_cours
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_en_cours);

        // Chercher les boutons et le chronomètre
        btnstop = findViewById(R.id.btnstop);
        chronometer = findViewById(R.id.chronometer);

        // Mettre des écouteurs sur les boutons
        btnstop.setOnClickListener(this);

        // Récupérer le temps mis en pause si disponible
        long pauseTime = getIntent().getLongExtra("PAUSE_TIME", 0);

        // Démarrer le chronomètre au lancement de l'activité
        startChronometer(pauseTime);
    }

    // Méthode pour démarrer le chronomètre avec un temps initial
    private void startChronometer(long initialTime) {
        if (!isChronometerRunning) {
            chronometer.setBase(SystemClock.elapsedRealtime() - initialTime);
            chronometer.start();
            isChronometerRunning = true;
        }
    }




    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnstop) {
            if (isChronometerRunning) {
                // Si le chronomètre est en cours, arrêter et enregistrer l'offset
                pauseChronometer();
            }

            // L'intent va permettre de passer des informations entre les activités
            Intent intent = new Intent(this, com.example.pagedebase.GO.activite_stoppee.class);
            intent.putExtra("BOUTON_CLIQUé", "activite_stoppee");
            intent.putExtra("PAUSE_OFFSET", pauseOffset); // Passer l'offset à activite_stoppee

            // Démarrer l'activité avec l'intention
            startActivity(intent);
        }
    }


    // Méthode pour mettre en pause le chronomètre
    private void pauseChronometer() {
        if (isChronometerRunning) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            isChronometerRunning = false;
        }
    }
}
