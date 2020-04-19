package com.exemple.Covoit.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.exemple.Covoit.models.Trajet;
import com.exemple.Covoit.models.Utilisateur;

import java.util.List;

@Dao //Data Access Object / objet d'accès aux données
public interface TrajetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Replace permet d'écraser un utilisateur déjà existant avec le même id
    void insert(Trajet... trajet);

    @Query("SELECT * FROM trajet WHERE id = :trajetId")
    Trajet get(long trajetId);

    @Query("SELECT * FROM trajet")
    List<Trajet> getAll();

    @Query("SELECT * FROM trajet")
    LiveData<List<Trajet>> getLiveAll();

    @Update
    int update(Trajet utilisateur);

    @Delete
    int delete(Trajet utilisateur);
}
