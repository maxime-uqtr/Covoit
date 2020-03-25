package com.exemple.Covoit.controleur;

public interface controleurInscription {
    boolean inscription(String nom, String prenom, String mail, String mdp, String verifMdp, boolean passager, boolean conducteur);
}
