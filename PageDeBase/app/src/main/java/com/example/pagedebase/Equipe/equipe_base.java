package com.example.pagedebase.Equipe;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.example.pagedebase.R;

public class equipe_base extends AppCompatActivity implements View.OnClickListener{

    private Button buttonQuitterEquipe;
    private Button go_btn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipebase);

        //On cherche les boutons
        buttonQuitterEquipe = findViewById(R.id.buttonQuitterEquipe);
        go_btn = findViewById(R.id.go_btn);


        //on met des ecouteur sur les boutons
        buttonQuitterEquipe.setOnClickListener(this);
        go_btn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {


        // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
        if (v.getId() == R.id.buttonQuitterEquipe) {

            //l'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
            Intent intent = new Intent(this, equipe_creation_ou_rejoindre.class); //on choisit ou on transmet

            intent.putExtra("BOUTON_CLIQUé", "equipe_creation_ou_rejoindre");

            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }
        else if (v.getId() == R.id.go_btn) {

            Intent intent = new Intent(this, com.example.pagedebase.GO.activite_en_cours.class);
            intent.putExtra("BOUTON_CLIQUé", "activite_en_cours");
            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }

    }
}