package com.exemple.Covoit.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

//Clé étrangère = un Utilisateur
@Entity(foreignKeys = @ForeignKey(entity = UtilisateurInfo.class,
        parentColumns = "id",
        childColumns = "conducteur_id"),
        indices = {@Index(value="conducteur_id")}
        )

public class CovoiturageInfo {
    @PrimaryKey
    private long id;
    @TypeConverters(DateConverter.class) //Conversion car impossible de sauvegarder le type date dans une BD avec Room
    private Date date;
    private String villeDep;
    private String villeArr;
    private int nbPassager;
    @ColumnInfo(name = "conducteur_id")
    private long conducteurId;

    public CovoiturageInfo(long id, Date date, String villeDep, String villeArr, int nbPassager, long conducteurId) {
        this.id = id;
        this.date = date;
        this.villeDep = villeDep;
        this.villeArr = villeArr;
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

    public int getNbPassager() { return nbPassager; }

    public long getConducteurId() { return conducteurId; }

}
