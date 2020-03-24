package com.exemple.Covoit.controleur;

public class Inscription {
    public boolean inscription(String nom, String prenom, String mail, String mdp, String verifMdp, boolean conducteur, boolean passager){
        if(conducteur == false && passager == false){
            return false;
        }
        if(mdp.equals(verifMdp) == false){
            return false;
        }

        return true;
    }
}
