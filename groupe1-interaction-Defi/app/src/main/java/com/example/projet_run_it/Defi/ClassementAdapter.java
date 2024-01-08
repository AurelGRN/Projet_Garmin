package com.example.projet_run_it.Defi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projet_run_it.R;
import java.util.List;

/*
 * Fichier Java créé par : Tianchen HAN
 * Groupe : TP5B1 Interaction
 * Intégrateur : [Nom de l'intégrateur]
 * Date de création : 2024-01-04
 * Description : Cet adaptateur est utilisé pour lier les données de classement à une vue RecyclerView.
 * Chaque élément de classement est représenté par un ViewHolder.
 * Version : 1.0
 * Dernière modification : 2024-01-04
 */
public class ClassementAdapter extends RecyclerView.Adapter<ClassementAdapter.ViewHolder> {

    private List<ClassementItem> classementList;

    // Classe ViewHolder interne
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewRank;
        public TextView textViewRunner;
        public TextView textViewTeam;
        public TextView textViewScore;

        public ViewHolder(View view) {
            super(view);
            textViewRank = view.findViewById(R.id.textView_rank);
            textViewRunner = view.findViewById(R.id.textView_runner);
            textViewTeam = view.findViewById(R.id.textView_team);
            textViewScore = view.findViewById(R.id.textView_score);
        }
    }

    // Constructeur de ClassementAdapter
    public ClassementAdapter(List<ClassementItem> classementList) {
        this.classementList = classementList;
    }

    // Création de nouvelles vues
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Création d'une nouvelle vue
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classement_item, parent, false);
        return new ViewHolder(view);
    }

    // Remplacement du contenu d'une vue
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Obtenir les données de l'élément
        // Mettre à jour le contenu de la vue
        ClassementItem item = classementList.get(position);
        holder.textViewRank.setText(String.valueOf(item.getRank()));
        holder.textViewRunner.setText(item.getRunnerName());
        holder.textViewTeam.setText(item.getTeamName());
        holder.textViewScore.setText(String.valueOf(item.getScore()));
    }

    // Retourner la taille de l'ensemble des données
    @Override
    public int getItemCount() {
        return classementList.size();
    }
}
