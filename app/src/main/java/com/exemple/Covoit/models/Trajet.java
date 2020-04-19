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
@Entity(foreignKeys = {@ForeignKey(entity = Covoiturage.class,
                       parentColumns = "id",
                       childColumns = "covoiturage_id"),
                       @ForeignKey(entity = Utilisateur.class,
                       parentColumns = "id",
                       childColumns = "passagers_id")},
        indices = {@Index(value = "covoiturage_id"),
                   @Index(value = "passagers_id")}
        )

public class Trajet {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "covoiturage_id")
    private long covoiturageId;
    @ColumnInfo(name = "passagers_id")
    private long passagersId;

    public Trajet(){}

    @Ignore
    public Trajet(long id, long covoiturageId, long passagersId) {
        this.id = id;
        this.covoiturageId = covoiturageId;
        this.passagersId = passagersId;
    }

    @Ignore
    public Trajet(long covoiturageId, long passagersId) {
        this.covoiturageId = covoiturageId;
        this.passagersId = passagersId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCovoiturageId() {
        return covoiturageId;
    }

    public void setCovoiturageId(long covoiturageId) {
        this.covoiturageId = covoiturageId;
    }

    public long getPassagersId() {
        return passagersId;
    }

    public void setPassagersId(long passagersId) {
        this.passagersId = passagersId;
    }
}