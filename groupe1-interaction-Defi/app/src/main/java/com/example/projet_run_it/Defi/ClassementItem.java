package com.example.projet_run_it.Defi;
/*
 * Fichier Java créé par : Tianchen HAN
 * Groupe : TP5B1 Interaction
 * Intégrateur : [Nom de l'intégrateur]
 * Date de création : 2024-01-04
 * Description : Cette classe représente un élément de classement pour l'application Projet Run It.
 * Elle contient le rang, le nom du coureur, le nom de l'équipe et le score.
 * Version : 1.0
 * Dernière modification : 2024-01-04
 */
public class ClassementItem {
    private int rank;
    private String runnerName;
    private String teamName;
    private int score;

    // Constructeur
    public ClassementItem(int rank, String runnerName, String teamName, int score) {
        this.rank = rank;
        this.runnerName = runnerName;
        this.teamName = teamName;
        this.score = score;
    }

    // Méthodes getter et setter
    public int getRank() { return rank; }
    public String getRunnerName() { return runnerName; }
    public String getTeamName() { return teamName; }
    public int getScore() { return score; }
}