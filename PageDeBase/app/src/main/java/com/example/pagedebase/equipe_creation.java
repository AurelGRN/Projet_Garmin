package com.example.pagedebase;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

    public class equipe_creation extends AppCompatActivity implements View.OnClickListener{

        private Button buttonCreerEquipe;





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.equipe_creation);

            //On cherche les boutons
            buttonCreerEquipe = findViewById(R.id.buttonCreerEquipe);


            //on met des ecouteur sur les boutons
            buttonCreerEquipe.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {


            // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
            if (v.getId() == R.id.buttonCreerEquipe) {

                //l'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
                Intent intent = new Intent(this, equipe_Admin.class); //on choisit ou on transmet

                intent.putExtra("BOUTON_CLIQUé", "equipe_Admin");

                // Démarrer l'activité avec l'intention
                startActivity(intent);

            }

        }
    }