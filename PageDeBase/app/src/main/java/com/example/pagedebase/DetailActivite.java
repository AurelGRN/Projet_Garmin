package com.example.pagedebase;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

public class DetailActivite extends AppCompatActivity implements View.OnClickListener {

    private Button btnVoirAnalyse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activite);

        //On cherche les boutons
        btnVoirAnalyse = findViewById(R.id.analyse_btn);


        //on met des ecouteurs sur les boutons
        btnVoirAnalyse.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


        // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
        if (v.getId() == R.id.analyse_btn) {

            //l'intent va permettre de passer des informations entre le détail de l'activite et l'analyse de l'activite
            Intent intent = new Intent(this, Analyse.class); //on choisit ou on transmet

            intent.putExtra("BOUTON_CLIQUE", "Analyse");

            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }
    }
}