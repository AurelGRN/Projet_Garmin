package com.example.pagedebase;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

public class equipe_creation_ou_rejoindre extends AppCompatActivity implements View.OnClickListener{

    private Button btnRejoindre;
    private Button btnCreer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipe_creation_ou_rejoindre);

        //On cherche les boutons
        btnRejoindre = findViewById(R.id.btnRejoindre);
        btnCreer = findViewById(R.id.btnCreer);


        //on met des ecouteur sur les boutons
        btnRejoindre.setOnClickListener(this);
        btnCreer.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {


        // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
        if (v.getId() == R.id.btnRejoindre) {

            //l'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
            Intent intent = new Intent(this, equipe_rejoindre.class); //on choisit ou on transmet

            intent.putExtra("BOUTON_CLIQUé", "equipe_rejoindre");

            // Démarrer l'activité avec l'intention
            startActivity(intent);

        } else if (v.getId() == R.id.btnCreer) {

            Intent intent = new Intent(this, equipe_creation.class);
            intent.putExtra("BOUTON_CLIQUé", "equipe_creation");
            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }


    }
}