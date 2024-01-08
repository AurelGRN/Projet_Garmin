package com.example.projet_run_it.Defi.WeekCalendar;

import java.util.Date;

/*
 * Fichier Java créé par : Tianchen HAN
 * Groupe : TP5B1 Interaction
 * Intégrateur : [Nom de l'intégrateur]
 * Date de création : 2023-11-25
 * Description : Cette classe représente un objet de date avec des propriétés telles que le jour, le mois, l'année, la date et la sélection.
 * Version : 3.0
 * Dernière modification : 2023-12-05
 */

public class DateBean {
    private int day;
    private int month;
    private int year;
    private Date date;
    private boolean selectable;

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}