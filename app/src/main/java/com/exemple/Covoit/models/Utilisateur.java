package com.exemple.Covoit.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Utilisateur {

    @PrimaryKey(autoGenerate = true) //Clé primaire = identifiant unique
    private long id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private String urlPhoto;
    private boolean conducteur;
    private boolean passager;

    public Utilisateur(long id, String nom, String prenom, String mail, String mdp, String urlPhoto, boolean conducteur, boolean passager) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.urlPhoto = urlPhoto;
        this.conducteur = conducteur;
        this.passager = passager;
    }

    //Constructeur sans photo de profil
    public Utilisateur(long id, String nom, String prenom, String mail, String mdp, boolean conducteur, boolean passager) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.urlPhoto = "https://dmigit.uqtr.ca/couetm/Covoit.git/data/img/default.jpg"; //Image par défautK
        this.conducteur = conducteur;
        this.passager = passager;
    }


    public long getId() { return id; }

    public String getNom() { return nom; }

    public String getPrenom() { return prenom; }

    public String getMail() { return mail; }

    public String getMdp() { return mdp; }

    public String getUrlPhoto() { return urlPhoto; }

    public boolean isConducteur() { return conducteur; }

    public boolean isPassager() { return passager; }
}
