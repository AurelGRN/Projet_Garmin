package com.example.pagedebase.Equipe;

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

import com.example.pagedebase.R;


public class equipe_Admin extends AppCompatActivity implements View.OnClickListener {

    private Button buttonQuitterEquipe;
    private ImageButton edit_imageButton;
    private Dialog fenetreDeDialogue;

    private Button go_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipeadmin);

        // On cherche les boutons dans le layout XML
        buttonQuitterEquipe = findViewById(R.id.buttonQuitterEquipe);
        edit_imageButton = findViewById(R.id.edit_imageButton);
        go_btn = findViewById(R.id.go_btn);

        // On met des écouteurs sur les boutons
        buttonQuitterEquipe.setOnClickListener(this);
        go_btn.setOnClickListener(this);

        // On utilise un écouteur anonyme pour le bouton d'édition
        edit_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appeler la méthode pour afficher la fenêtre de dialogue floue
                afficherDialogueFlou();
            }
        });
    }

    private void afficherDialogueFlou() {
        // Méthode pour afficher une fenêtre de dialogue avec un fond flou

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

        // Créer et configurer la fenêtre de dialogue
        fenetreDeDialogue = new Dialog(this);
        fenetreDeDialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
        fenetreDeDialogue.setContentView(R.layout.dialog_layout_admin_equipe); // Utiliser le layout personnalisé

        Window fenetre = fenetreDeDialogue.getWindow();
        if (fenetre != null) {
            fenetre.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            fenetre.setGravity(Gravity.CENTER);
            fenetre.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // Configurer les boutons dans la fenêtre de dialogue
        Button exclureButton = fenetreDeDialogue.findViewById(R.id.exclureButton);
        exclureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fenetreDeDialogue.dismiss(); // Fermer la fenêtre de dialogue
            }
        });
        Button boutonFermer = fenetreDeDialogue.findViewById(R.id.closeButton);
        boutonFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fenetreDeDialogue.dismiss(); // Fermer la fenêtre de dialogue
            }
        });

        // Afficher la fenêtre de dialogue
        fenetreDeDialogue.show();

        // Supprimer l'effet flou lorsque la fenêtre de dialogue est fermée
        fenetreDeDialogue.setOnDismissListener(dialogInterface -> vueDecor.removeView(vueFloue));
    }

    private Bitmap obtenirBitmapDeVue(View vue) {
        // Convertir la vue en bitmap
        Bitmap bitmap = Bitmap.createBitmap(vue.getWidth(), vue.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vue.draw(canvas);
        return bitmap;
    }

    private Bitmap flouterBitmap(Bitmap image, Context contexte) {
        // Appliquer un effet de flou à un bitmap
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
        // Méthode appelée lorsque l'utilisateur clique sur un bouton

        // Ajouter une information supplémentaire à l'intention pour indiquer quel bouton a été cliqué
        if (v.getId() == R.id.buttonQuitterEquipe) {

            // L'intent va permettre de passer des informations entre les activités Acceuil et MainActivity
            Intent intent = new Intent(this, equipe_creation_ou_rejoindre.class); // On choisit où on transmet

            // Ajouter une chaîne supplémentaire à l'intention pour indiquer quel bouton a été cliqué
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
