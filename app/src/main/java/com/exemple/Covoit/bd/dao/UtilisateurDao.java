package com.exemple.Covoit.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.exemple.Covoit.models.Utilisateur;

@Dao //Data Access Object / objet d'accès aux données
public interface UtilisateurDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Replace permet d'écraser un utilisateur déjà existant avec le même id
    void createUtilisateur(Utilisateur utilisateur);

    @Query("SELECT * FROM Utilisateur WHERE id = :userId")
    LiveData<Utilisateur> getUtilisateur(long userId);

}
