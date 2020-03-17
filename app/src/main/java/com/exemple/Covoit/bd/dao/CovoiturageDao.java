package com.exemple.Covoit.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.exemple.Covoit.models.Covoiturage;

import java.util.List;

@Dao //Data Access Object / objet d'accès aux données
public interface CovoiturageDao { //Room requiert une interface par DAO

    //Actions CRUD
    @Query("SELECT * FROM covoiturage WHERE conducteur_id = :conducteurId") //@Query pour définir une requête SQL
    LiveData<List<Covoiturage>> getAll(long conducteurId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Covoiturage covoiturage);

    @Update
    int update(Covoiturage covoiturage);

    @Query("DELETE FROM covoiturage WHERE id = :covoiturageId")
    int delete(long covoiturageId);
}
