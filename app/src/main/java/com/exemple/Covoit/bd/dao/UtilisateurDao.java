package com.exemple.Covoit.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.exemple.Covoit.models.Utilisateur;

import java.util.List;

@Dao //Data Access Object / objet d'accès aux données
public interface UtilisateurDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Replace permet d'écraser un utilisateur déjà existant avec le même id
    void insert(Utilisateur... utilisateur);

    @Query("SELECT * FROM utilisateur WHERE id = :userId")
    Utilisateur get(long userId);

    @Query("SELECT * FROM utilisateur")
    List<Utilisateur> getAll();

    @Query("SELECT * FROM utilisateur")
    LiveData<List<Utilisateur>> getLiveAll();

    @Update
    int update(Utilisateur utilisateur);

    @Delete
    int delete(Utilisateur utilisateur);

}
