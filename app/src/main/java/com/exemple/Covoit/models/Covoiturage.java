package com.exemple.Covoit.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.exemple.Covoit.controleur.ConversionDate;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

//Clé étrangère = un Utilisateur
@Entity(foreignKeys = {@ForeignKey(entity = Utilisateur.class,
        parentColumns = "id",
        childColumns = "conducteur_id")},
        indices = {@Index(value = "conducteur_id")}
       )

public class Covoiturage {

    @SerializedName("id")
    @PrimaryKey
    private long id;
    @SerializedName("date")
    private String date;
    @SerializedName("ville_dep")
    @ColumnInfo(name = "ville_dep")
    private String villeDep;
    @SerializedName("ville_arr")
    @ColumnInfo(name = "ville_arr")
    private String villeArr;
    @SerializedName("prix")
    private float prix;
    @SerializedName("nb_passager")
    @ColumnInfo(name = "nb_passager")
    private int nbPassager;
    @SerializedName("conducteur_id")
    @ColumnInfo(name = "conducteur_id")
    private long conducteurId;

    public Covoiturage(){}

    @Ignore
    public Covoiturage(long id, String date, String villeDep, String villeArr, float prix, int nbPassager, long conducteurId) {
        this.id = id;
        this.date = date;
        this.villeDep = villeDep;
        this.villeArr = villeArr;
        this.prix = prix;
        this.nbPassager = nbPassager;
        this.conducteurId = conducteurId;
    }

    @Ignore
    public Covoiturage(String date, String villeDep, String villeArr, float prix, int nbPassager, long conducteurId) {
        this.date = date;
        this.villeDep = villeDep;
        this.villeArr = villeArr;
        this.prix = prix;
        this.nbPassager = nbPassager;
        this.conducteurId = conducteurId;
    }

    @NonNull
    @Override
    public String toString() {
        return "id : " + id + ", " + date + " départ : " + villeDep + " destination : " + villeArr + " conducteur : " + conducteurId + " nbPlaces : " + nbPassager;
    }

    public void setId(long id) { this.id = id; }

    public long getId() { return id; }

    public String getDate() { return date; }

    public String getVilleDep() { return villeDep; }

    public String getVilleArr() { return villeArr; }

    public float getPrix() { return prix; }

    public int getNbPassager() { return nbPassager; }

    public long getConducteurId() { return conducteurId; }

    public void setDate(String date) { this.date = date; }

    public void setVilleDep(String villeDep) { this.villeDep = villeDep; }

    public void setVilleArr(String villeArr) { this.villeArr = villeArr; }

    public void setPrix(float prix) { this.prix = prix; }

    public void setNbPassager(int nbPassager) { this.nbPassager = nbPassager; }

    public void setConducteurId(long conducteurId) { this.conducteurId = conducteurId; }

}