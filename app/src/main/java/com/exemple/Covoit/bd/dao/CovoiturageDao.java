package com.exemple.Covoit.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.exemple.Covoit.models.CovoiturageInfo;

import java.util.List;

@Dao //Data Access Object / objet d'accès aux données
public interface CovoiturageDao { //Room requiert une interface par DAO

    //Actions CRUD
    @Query("SELECT * FROM CovoiturageInfo WHERE conducteur_id = :conducteurId") //@Query pour définir une requête SQL
    LiveData<List<CovoiturageInfo>> getCovoiturages(long conducteurId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCovoiturage(CovoiturageInfo covoiturage);

    @Update
    int updateCovoiturage(CovoiturageInfo covoiturage);

    @Query("DELETE FROM CovoiturageInfo WHERE id = :covoiturageId")
    int deleteCovoiturage(long covoiturageId);
}
