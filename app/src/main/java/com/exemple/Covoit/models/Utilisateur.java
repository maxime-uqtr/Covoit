package com.exemple.Covoit.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Utilisateur {

    @PrimaryKey(autoGenerate = true) //Clé primaire = identifiant unique
    private long id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private String telephone;
    private String urlPhoto;
    private boolean conducteur;
    private boolean passager;

    public Utilisateur(){}

    @Ignore
    public Utilisateur(long id, String nom, String prenom, String mail, String mdp, String telephone, String urlPhoto, boolean conducteur, boolean passager) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.urlPhoto = urlPhoto;
        this.telephone = telephone;
        this.conducteur = conducteur;
        this.passager = passager;
    }

    @Ignore
    public Utilisateur(String nom, String prenom, String mail, String mdp, String telephone, String urlPhoto, boolean conducteur, boolean passager) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.urlPhoto = urlPhoto;
        this.telephone = telephone;
        this.conducteur = conducteur;
        this.passager = passager;
    }

    public long getId() { return id; }

    public String getNom() { return nom; }

    public String getPrenom() { return prenom; }

    public String getMail() { return mail; }

    public String getMdp() { return mdp; }

    public String getTelephone() { return telephone; }

    public String getUrlPhoto() { return urlPhoto; }

    public boolean isConducteur() { return conducteur; }

    public boolean isPassager() { return passager; }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setTelephone(String telephone) { this.telephone = telephone; }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public void setConducteur(boolean conducteur) {
        this.conducteur = conducteur;
    }

    public void setPassager(boolean passager) {
        this.passager = passager;
    }

}
