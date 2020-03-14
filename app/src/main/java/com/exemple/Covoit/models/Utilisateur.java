package com.exemple.Covoit.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.security.KeyStore;

@Entity
public class Utilisateur {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private String urlPhoto;


    public Utilisateur(long id, String nom, String prenom, String mail, String mdp, String urlPhoto) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.urlPhoto = urlPhoto;
    }

    public long getId() { return id; }

    public String getNom() { return nom; }

    public String getPrenom() { return prenom; }

    public String getMail() { return mail; }

    public String getMdp() { return mdp; }

    public String getUrlPhoto() { return urlPhoto; }
}
