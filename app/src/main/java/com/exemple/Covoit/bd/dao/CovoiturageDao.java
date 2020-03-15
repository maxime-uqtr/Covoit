package com.exemple.Covoit.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.exemple.Covoit.models.Covoiturage;

import java.util.List;

@Dao //Data Access Object / objet d'accès aux données
public interface CovoiturageDao { //Room requiert une interface par DAO

    //Actions CRUD
    @Query("SELECT * FROM Covoiturage WHERE conducteurId = :conducteurId") //@Query pour définir une requête SQL
    LiveData<List<Covoiturage>> getCovoiturages(long conducteurId);

    @Insert
    long insertCovoiturage(Covoiturage covoiturage);

    @Update
    int updateCovoiturage(Covoiturage covoiturage);

    @Query("DELETE FROM Covoiturage WHERE id = :covoiturageId")
    int deleteCovoiturage(long covoiturageId);
}
