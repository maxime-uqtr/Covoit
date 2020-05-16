package com.exemple.Covoit.models;

import android.util.Log;

public class UtilisateurActuel {
    private static Utilisateur utilisateur;
    private static boolean inst;

    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public static void setUtilisateur(Utilisateur utilisateur) {
        UtilisateurActuel.utilisateur = utilisateur;
        inst = true;
        //Log.i("TAG1", String.valueOf(UtilisateurActuel.isInst()));
    }

    public static boolean isInst() {
        Log.i("TAG1", String.valueOf(UtilisateurActuel.isInst()));
        return inst;
    }
}