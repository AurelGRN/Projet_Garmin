
package com.example.pagedebase;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

public class activite_enregistrement extends AppCompatActivity implements View.OnClickListener{

    private Button btnsave;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_enregistrement);

        //On cherche les boutons
        btnsave = findViewById(R.id.btnsave);



        //on met des ecouteur sur les boutons
        btnsave.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
        if (v.getId() == R.id.btnsave) {

            //l'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
            Intent intent = new Intent(this, menu.class); //on choisit ou on transmet

            intent.putExtra("BOUTON_CLIQUé", "menu");

            // Démarrer l'activité avec l'intention
            startActivity(intent);

        }

    }
}