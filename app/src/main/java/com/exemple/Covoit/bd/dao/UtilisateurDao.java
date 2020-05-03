package com.exemple.Covoit.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.exemple.Covoit.models.Covoiturage;
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

    @Query("SELECT id FROM utilisateur WHERE mail = :mail AND mdp = :mdp")
    long getIdConnection(String mail, String mdp);

    @Query("SELECT * FROM covoiturage WHERE id IN (SELECT covoiturage_id FROM trajet WHERE passagers_id = :userId OR conducteur_id = :userId AND confirme) ORDER BY date")
    List<Covoiturage> getCovoituragesConfirmes(long userId);

    @Query("SELECT * FROM covoiturage WHERE id IN (SELECT covoiturage_id FROM trajet WHERE passagers_id = :userId OR conducteur_id = :userId AND en_attente) ORDER BY date")
    long getDemandes(long userId);

    @Update
    int update(Utilisateur utilisateur);

    @Delete
    int delete(Utilisateur utilisateur);
}
