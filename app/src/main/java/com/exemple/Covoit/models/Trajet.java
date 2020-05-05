package com.exemple.Covoit.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

//Clé étrangère = un Utilisateur
@Entity(foreignKeys = {@ForeignKey(entity = Covoiturage.class,
                       parentColumns = "id",
                       childColumns = "covoiturage_id"),
                       @ForeignKey(entity = Utilisateur.class,
                       parentColumns = "id",
                       childColumns = "passager_id")},
        indices = {@Index(value = "covoiturage_id"),
                   @Index(value = "passager_id")}
        )

public class Trajet {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    private long id;
    @SerializedName("covoiturage_id")
    @ColumnInfo(name = "covoiturage_id")
    private long covoiturageId;
    @SerializedName("passager_id")
    @ColumnInfo(name = "passager_id")
    private long passagerId;
    @SerializedName("en_attente")
    @ColumnInfo(name = "en_attente")
    private boolean enAttente = true;
    @SerializedName("confirme")
    private boolean confirme = false;

    public Trajet(){}

    @Ignore
    public Trajet(long id, long covoiturageId, long passagerId) {
        this.id = id;
        this.covoiturageId = covoiturageId;
        this.passagerId = passagerId;
    }

    @Ignore
    public Trajet(long covoiturageId, long passagerId) {
        this.covoiturageId = covoiturageId;
        this.passagerId = passagerId;
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

    public long getPassagerId() {
        return passagerId;
    }

    public void setPassagerId(long passagerId) {
        this.passagerId = passagerId;
    }

    public boolean isEnAttente() { return enAttente; }

    public void setEnAttente(boolean enAttente) { this.enAttente = enAttente; }

    public boolean isConfirme() { return confirme; }

    public void setConfirme(boolean confirme) { enAttente = false; this.confirme = confirme; }

}