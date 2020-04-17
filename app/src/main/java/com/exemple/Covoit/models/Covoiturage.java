package com.exemple.Covoit.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.exemple.Covoit.controleur.ConversionDate;

import java.util.Date;

//Clé étrangère = un Utilisateur
@Entity(foreignKeys = {@ForeignKey(entity = Utilisateur.class,
        parentColumns = "id",
        childColumns = "conducteur_id")},
        indices = {@Index(value="conducteur_id")}
)

public class Covoiturage {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @TypeConverters(ConversionDate.class) //Conversion car impossible de sauvegarder le type date dans une BD avec Room
    private Date date;
    @ColumnInfo(name = "ville_dep")
    private String villeDep;
    @ColumnInfo(name = "ville_arr")
    private String villeArr;
    private float prix;
    @ColumnInfo(name = "nb_passager")
    private int nbPassager;
    @ColumnInfo(name = "conducteur_id")
    private long conducteurId;

    public Covoiturage(){}

    @Ignore
    public Covoiturage(long id, Date date, String villeDep, String villeArr, float prix, int nbPassager, long conducteurId) {
        this.id = id;
        this.date = date;
        this.villeDep = villeDep;
        this.villeArr = villeArr;
        this.prix = prix;
        this.nbPassager = nbPassager;
        this.conducteurId = conducteurId;
    }

    @Ignore
    public Covoiturage(Date date, String villeDep, String villeArr, float prix, int nbPassager, long conducteurId) {
        this.date = date;
        this.villeDep = villeDep;
        this.villeArr = villeArr;
        this.prix = prix;
        this.nbPassager = nbPassager;
        this.conducteurId = conducteurId;
    }

    public void setId(long id) { this.id = id; }

    public long getId() { return id; }

    public Date getDate() { return date; }

    public String getVilleDep() { return villeDep; }

    public String getVilleArr() { return villeArr; }

    public float getPrix() { return prix; }

    public int getNbPassager() { return nbPassager; }

    public long getConducteurId() { return conducteurId; }

    public void setDate(Date date) { this.date = date; }

    public void setVilleDep(String villeDep) { this.villeDep = villeDep; }

    public void setVilleArr(String villeArr) { this.villeArr = villeArr; }

    public void setPrix(float prix) { this.prix = prix; }

    public void setNbPassager(int nbPassager) { this.nbPassager = nbPassager; }

    public void setConducteurId(long conducteurId) { this.conducteurId = conducteurId; }

}