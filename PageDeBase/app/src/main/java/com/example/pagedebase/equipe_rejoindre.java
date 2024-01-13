package com.example.pagedebase;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

    public class equipe_rejoindre extends AppCompatActivity implements View.OnClickListener{

       private ImageButton info_imageButton;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.equipe_rejoindre);

            //On cherche les boutons
            info_imageButton = findViewById(R.id.info_imageButton);


            //on met des ecouteur sur les boutons
            info_imageButton.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {


            // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
            if (v.getId() == R.id.info_imageButton) {

                //l'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
                Intent intent = new Intent(this, equipe_rejoindre_info.class); //on choisit ou on transmet

                intent.putExtra("BOUTON_CLIQUé", "equipe_rejoindre_info");

                // Démarrer l'activité avec l'intention
                startActivity(intent);

            }

        }
    }