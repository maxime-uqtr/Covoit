package com.exemple.Covoit.bd;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.exemple.Covoit.bd.dao.CovoiturageDao;
import com.exemple.Covoit.bd.dao.UtilisateurDao;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;

@Database(entities = {Covoiturage.class, Utilisateur.class}, version = 1, exportSchema = false) //On exporte pas car pas définit le directory
public abstract class CovoiturageBd extends RoomDatabase {

    private static CovoiturageBd INSTANCE;

    public abstract CovoiturageDao getCovoiturageDao();
    public abstract UtilisateurDao getUtilisateurDao();

    public static synchronized CovoiturageBd getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static CovoiturageBd buildDatabase(final Context context){
        return Room.databaseBuilder(context, CovoiturageBd.class, "database")
                .build();
    }

}
