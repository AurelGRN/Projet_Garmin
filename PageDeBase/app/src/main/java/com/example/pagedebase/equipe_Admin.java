package com.example.pagedebase;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Dialog;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.ColorDrawable;
        import android.os.Bundle;
        import android.renderscript.Allocation;
        import android.renderscript.Element;
        import android.renderscript.RenderScript;
        import android.renderscript.ScriptIntrinsicBlur;
        import android.view.Gravity;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Window;
        import android.widget.Button;
        import android.widget.FrameLayout;
        import android.widget.ImageButton;

    public class equipe_Admin extends AppCompatActivity implements View.OnClickListener{

        private Button buttonQuitterEquipe;
        private ImageButton edit_imageButton;
        private Dialog fenetreDeDialogue;





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.equipeadmin);

            //On cherche les boutons
            buttonQuitterEquipe = findViewById(R.id.buttonQuitterEquipe);
            edit_imageButton = findViewById(R.id.edit_imageButton);


            //on met des ecouteur sur les boutons
            buttonQuitterEquipe.setOnClickListener(this);

            edit_imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    afficherDialogueFlou();
                }
            });
        }


        private void afficherDialogueFlou() {
            // Créer un arrière-plan flou en plein écran
            Bitmap image = obtenirBitmapDeVue(getWindow().getDecorView().getRootView());
            Bitmap imageFloue = flouterBitmap(image, this);
            View vueFloue = new View(this);
            vueFloue.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            vueFloue.setBackground(new BitmapDrawable(getResources(), imageFloue));

            // Ajouter l'arrière-plan flou à la vue de contenu
            ViewGroup vueDecor = (ViewGroup) getWindow().getDecorView();
            vueDecor.addView(vueFloue);

            fenetreDeDialogue = new Dialog(this);
            fenetreDeDialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
            fenetreDeDialogue.setContentView(R.layout.dialog_layout); // Utiliser le layout personnalisé

            Window fenetre = fenetreDeDialogue.getWindow();
            if (fenetre != null) {
                fenetre.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                fenetre.setGravity(Gravity.CENTER);
                fenetre.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            Button boutonFermer = fenetreDeDialogue.findViewById(R.id.closeButton);
            boutonFermer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fenetreDeDialogue.dismiss(); // Fermer la fenêtre de dialogue
                }
            });

            fenetreDeDialogue.show();

            // Supprimer l'effet flou lorsque la fenêtre de dialogue est fermée
            fenetreDeDialogue.setOnDismissListener(dialogInterface -> vueDecor.removeView(vueFloue));
        }
        private Bitmap obtenirBitmapDeVue(View vue) {
            Bitmap bitmap = Bitmap.createBitmap(vue.getWidth(), vue.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vue.draw(canvas);
            return bitmap;
        }

        private Bitmap flouterBitmap(Bitmap image, Context contexte) {
            float rayon = 20; // Ajuster le niveau de flou
            RenderScript scriptRendu = RenderScript.create(contexte);
            Allocation entree = Allocation.createFromBitmap(scriptRendu, image);
            Allocation sortie = Allocation.createTyped(scriptRendu, entree.getType());
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(scriptRendu, Element.U8_4(scriptRendu));
            script.setRadius(rayon);
            script.setInput(entree);
            script.forEach(sortie);
            sortie.copyTo(image);
            scriptRendu.destroy();
            return image;
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

        }
    }