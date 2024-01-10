package com.example.pagedebase;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

public class activite_en_cours extends AppCompatActivity implements View.OnClickListener{

    private Button btnstop;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_en_cours);

        //On cherche les boutons
        btnstop = findViewById(R.id.btnstop);



        //on met des ecouteur sur les boutons
        btnstop.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {


        // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
        if (v.getId() == R.id.btnstop) {

            //l'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
            Intent intent = new Intent(this, activite_stoppee.class); //on choisit ou on transmet

            intent.putExtra("BOUTON_CLIQUé", "activite_stoppee");

            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }

    }
}