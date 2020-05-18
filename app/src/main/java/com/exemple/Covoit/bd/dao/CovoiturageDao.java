package com.exemple.Covoit.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.exemple.Covoit.controleur.ConversionDate;
import com.exemple.Covoit.models.Covoiturage;

import java.util.Date;
import java.util.List;

@Dao //Data Access Object / objet d'accès aux données
public interface CovoiturageDao { //Room requiert une interface par DAO

    //Actions CRUD
    @Query("SELECT * FROM covoiturage WHERE id = :covoiturageId")
    Covoiturage get(long covoiturageId);

    @Query("SELECT * FROM covoiturage")
    List<Covoiturage> getAll();

    @Query("SELECT * FROM covoiturage")
    LiveData<List<Covoiturage>> getLiveAll();

    @Query("SELECT DISTINCT * FROM covoiturage WHERE id IN (SELECT covoiturage_id FROM trajet WHERE passager_id = :userId) ORDER BY date")
    List<Covoiturage> getPassager(long userId);

    @Query("SELECT DISTINCT * FROM covoiturage WHERE id IN (SELECT covoiturage_id FROM trajet WHERE conducteur_id = :conducteurId) ORDER BY date")
    List<Covoiturage> getConducteur(long conducteurId);

    @TypeConverters(ConversionDate.class)
    @Query("SELECT * FROM covoiturage WHERE ville_dep LIKE '%' || :depart || '%' AND ville_arr LIKE '%' || :destination || '%' ORDER BY date")
    List<Covoiturage> getLike(String depart, String destination);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Covoiturage... covoiturage);

    @Update
    int update(Covoiturage covoiturage);

    @Query("DELETE FROM covoiturage WHERE id = :covoiturageId")
    int delete(long covoiturageId);
}
