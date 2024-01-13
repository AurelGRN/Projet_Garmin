package com.example.pagedebase;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

public class activite_stoppee extends AppCompatActivity implements View.OnClickListener{

    private Button btnterminer;
    private Button btnreprendre;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_stoppee);

        //On cherche les boutons
        btnterminer = findViewById(R.id.btnterminer);
        btnreprendre = findViewById(R.id.btnreprendre);



        //on met des ecouteur sur les boutons
        btnterminer.setOnClickListener(this);
        btnreprendre.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {


        // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
        if (v.getId() == R.id.btnreprendre) {

            //l'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
            Intent intent = new Intent(this, activite_en_cours.class); //on choisit ou on transmet

            intent.putExtra("BOUTON_CLIQUé", "activite_en_cours");

            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }
        else if (v.getId() == R.id.btnterminer) {

            Intent intent = new Intent(this, activite_enregistrement.class);
            intent.putExtra("BOUTON_CLIQUé", "activite_enregistrement");
            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }

    }
}