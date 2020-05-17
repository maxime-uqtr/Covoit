package com.exemple.Covoit.models;

import android.util.Log;

import com.exemple.Covoit.controleur.ControleurConnexion;
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.UtilisateurService;

public class UtilisateurActuel {
    private static Utilisateur utilisateur;

    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public static void setUtilisateur(Utilisateur utilisateur) { UtilisateurActuel.utilisateur = utilisateur; }

}