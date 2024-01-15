package com.example.pagedebase.GO;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pagedebase.R;

import android.widget.Chronometer;
import android.os.SystemClock;

public class activite_stoppee extends AppCompatActivity implements View.OnClickListener {

    private Button btnterminer;
    private Button btnreprendre;
    private Chronometer chronometerStoppee;
    private boolean isChronometerStoppeeRunning = false;
    private long pauseOffsetStoppee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_stoppee);

        // Chercher les boutons et le chronomètre dans activite_stoppee
        btnterminer = findViewById(R.id.btnterminer);
        btnreprendre = findViewById(R.id.btnreprendre);
        chronometerStoppee = findViewById(R.id.chronometer_stoppee);

        // Mettez des écouteurs sur les boutons
        btnterminer.setOnClickListener(this);
        btnreprendre.setOnClickListener(this);

        // Récupérez l'offset du chronomètre de activite_en_cours
        long pauseOffset = getIntent().getLongExtra("PAUSE_OFFSET", 0);
        // Réglez le chronomètre de activite_stoppee avec l'offset
        chronometerStoppee.setBase(SystemClock.elapsedRealtime() - pauseOffset);
        isChronometerStoppeeRunning = true; // Vous pouvez supprimer la ligne chronometerStoppee.start();
    }

    // Dans votre méthode onClick de activite_stoppee
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnreprendre) {
            // L'intent va permettre de passer des informations entre les activités
            Intent intent = new Intent(this, com.example.pagedebase.GO.activite_en_cours.class);
            intent.putExtra("BOUTON_CLIQUé", "activite_en_cours");

            // Passer le temps mis en pause comme extra
            long pauseTime = getIntent().getLongExtra("PAUSE_TIME", 0);
            intent.putExtra("PAUSE_TIME", pauseTime);

            // Démarrer l'activité avec l'intention
            startActivity(intent);
        } else if (v.getId() == R.id.btnterminer) {
            // Mettez en pause le chronomètre de activite_stoppee lorsque vous appuyez sur "Terminer"
            pauseChronometerStoppee();

            Intent intent = new Intent(this, activite_enregistrement.class);
            intent.putExtra("BOUTON_CLIQUé", "activite_enregistrement");
            // Démarrer l'activité avec l'intention
            startActivity(intent);
        }
    }



    // Méthode pour mettre en pause le chronomètre de activite_stoppee
    private void pauseChronometerStoppee() {
        if (isChronometerStoppeeRunning) {
            chronometerStoppee.stop();
            pauseOffsetStoppee = SystemClock.elapsedRealtime() - chronometerStoppee.getBase();
            isChronometerStoppeeRunning = false;
        }
    }
}
