package com.exemple.Covoit.models;

import android.util.Log;

public class UtilisateurActuel {
    private static Utilisateur utilisateur;

    public static Utilisateur getUtilisateur() {
        return new Utilisateur(8, "Covoit", "Admin", "admin", "admin", "8012", true, true);
        //return utilisateur;
    }

    public static void setUtilisateur(Utilisateur utilisateur) { UtilisateurActuel.utilisateur = utilisateur; }

}