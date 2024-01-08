package com.example.projet_run_it.Defi;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_run_it.Defi.WeekCalendar.WeekCalendarView;
import com.example.projet_run_it.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/*
 * Fichier Java créé par : Tianchen HAN
 * Groupe : TP5B1 Interaction
 * Intégrateur : [Nom de l'intégrateur]
 * Date de création : 2023-11-25
 * Description : Cette activité gère la proposition de défis avec un affichage de calendrier hebdomadaire.
 * Version : 3.2
 * Dernière modification : 2024-01-04
 */
public class DefiProposeActivity extends AppCompatActivity {

    private WeekCalendarView vueCalendrierSemaine;
    private TextView textViewDate;
    private SimpleDateFormat formatDeDate;
    private Button boutonJouer;
    private Dialog fenetreDeDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.defi_propose);

        vueCalendrierSemaine = findViewById(R.id.weekCalendarView);
        formatDeDate = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());

        definirPlageCalendrier();

        boutonJouer = findViewById(R.id.playButton);

        // Configurer l'événement de clic du bouton
        boutonJouer.setOnClickListener(new View.OnClickListener() {
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

    private void definirPlageCalendrier() {
        Calendar calMin = Calendar.getInstance();
        calMin.add(Calendar.DATE, -7); // Définir la date minimale à il y a une semaine
        Calendar calMax = Calendar.getInstance();
        calMax.add(Calendar.DATE, 3); // Définir la date maximale à dans trois jours
        vueCalendrierSemaine.setRange(calMin, calMax);
    }
}


