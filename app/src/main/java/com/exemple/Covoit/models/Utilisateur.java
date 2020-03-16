package com.exemple.Covoit.models;

public class Utilisateur {


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

    public long getId() { return id; }

    public String getNom() { return nom; }

    public String getPrenom() { return prenom; }

    public String getMail() { return mail; }

    public String getMdp() { return mdp; }

    public String getUrlPhoto() { return urlPhoto; }

    public boolean isConducteur() {
        return conducteur;
    }

    public boolean isPassager() {
        return passager;
    }
}
