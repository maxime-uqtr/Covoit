package com.exemple.Covoit.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;




public class Covoiturage {
    private long id;
    private Date date;
    private String villeDep;
    private String villeArr;
    private int nbPassager;
    private Utilisateur conducteurId;

    public Covoiturage(long id, Date date, String villeDep, String getVilleArr, int nbPassager, Utilisateur conducteurId) {
        this.id = id;
        this.date = date;
        this.villeDep = villeDep;
        this.villeArr = getVilleArr;
        this.nbPassager = nbPassager;
        this.conducteurId = conducteurId;
    }

    public long getId() { return id; }

    public Date getDate() {
        return date;
    }

    public String getVilleDep() {
        return villeDep;
    }

    public String getVilleArr() {
        return villeArr;
    }

    public int getNbPassager() {
        return nbPassager;
    }

    public Utilisateur getConducteurId() { return conducteurId; }

}
