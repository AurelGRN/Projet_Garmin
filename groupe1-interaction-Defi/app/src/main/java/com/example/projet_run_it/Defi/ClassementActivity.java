package com.example.projet_run_it.Defi;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projet_run_it.R;
import java.util.ArrayList;
import java.util.List;

/*
 * Fichier Java créé par : Tianchen HAN
 * Groupe : TP5B1 Interaction
 * Intégrateur : [Nom de l'intégrateur]
 * Date de création : 2024-01-04
 * Description : Cette activité gère l'affichage du classement dans l'application Projet Run It.
 * Elle inclut la gestion des onglets et l'affichage des éléments de classement dans un RecyclerView.
 * Version : 1.0
 * Dernière modification : 2024-01-04
 */
public class ClassementActivity extends AppCompatActivity {

    private TextView textViewQuotidien, textViewMensuel, textViewAnnuel;
    private TextView textViewIndividuel, textViewEquipe;
    private RecyclerView recyclerViewClassement;
    private ClassementAdapter classementAdapter;
    private List<ClassementItem> classementItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classement);

        // Initialisation des TextViews pour "Quotidien/Mensuel/Annuel"
        textViewQuotidien = findViewById(R.id.textView_quotidien);
        textViewMensuel = findViewById(R.id.textView_mensuel);
        textViewAnnuel = findViewById(R.id.textView_annuel);

        // Initialisation des TextViews pour "Individuel/Équipe"
        textViewIndividuel = findViewById(R.id.textView_individuel);
        textViewEquipe = findViewById(R.id.textView_equipe);

        // Configuration des TextViews pour les deux groupes
        setupTextView(textViewQuotidien, true);
        setupTextView(textViewMensuel, true);
        setupTextView(textViewAnnuel, true);
        setupTextView(textViewIndividuel, false);
        setupTextView(textViewEquipe, false);

        // Sélection par défaut des onglets
        selectTab(textViewQuotidien, true);
        selectTab(textViewIndividuel, false);

        recyclerViewClassement = findViewById(R.id.recyclerView_classement);

        // Création de données fictives
        createFakeData();

        // Configuration du LayoutManager
        recyclerViewClassement.setLayoutManager(new LinearLayoutManager(this));

        // Création et configuration de l'adaptateur
        classementAdapter = new ClassementAdapter(classementItems);
        recyclerViewClassement.setAdapter(classementAdapter);
    }

    private void setupTextView(final TextView textView, final boolean isTimeCategory) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(textView, isTimeCategory);
            }
        });
    }

    private void selectTab(TextView selectedTab, boolean isTimeCategory) {
        if (isTimeCategory) {
            resetTabStyles(true);
        } else {
            resetTabStyles(false);
        }
        selectedTab.setSelected(true);
        selectedTab.setTypeface(null, Typeface.BOLD);
    }

    private void resetTabStyles(boolean isTimeCategory) {
        if (isTimeCategory) {
            textViewQuotidien.setSelected(false);
            textViewMensuel.setSelected(false);
            textViewAnnuel.setSelected(false);

            textViewQuotidien.setTypeface(null, Typeface.NORMAL);
            textViewMensuel.setTypeface(null, Typeface.NORMAL);
            textViewAnnuel.setTypeface(null, Typeface.NORMAL);
        } else {
            textViewIndividuel.setSelected(false);
            textViewEquipe.setSelected(false);

            textViewIndividuel.setTypeface(null, Typeface.NORMAL);
            textViewEquipe.setTypeface(null, Typeface.NORMAL);
        }
    }

    private void createFakeData() {
        classementItems = new ArrayList<>();
        classementItems.add(new ClassementItem(1, "Runner 1", "Team A", 100));
        classementItems.add(new ClassementItem(2, "Runner 2", "Team B", 95));
        // Ajout de plus de données fictives
    }
}
