package com.exemple.Covoit.bd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.exemple.Covoit.bd.dao.CovoiturageDao;
import com.exemple.Covoit.bd.dao.TrajetDao;
import com.exemple.Covoit.bd.dao.UtilisateurDao;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Trajet;
import com.exemple.Covoit.models.Utilisateur;

@Database(entities = {Covoiturage.class, Utilisateur.class, Trajet.class}, version = 1, exportSchema = false) //On exporte pas car pas définit le directory
public abstract class CovoiturageBd extends RoomDatabase {

    private static CovoiturageBd Instance;

    public abstract CovoiturageDao getCovoiturageDao();
    public abstract UtilisateurDao getUtilisateurDao();
    public abstract TrajetDao getTrajetDao();

    public static synchronized CovoiturageBd getInstance(final Context context){

        if(Instance == null){
            context.deleteDatabase("database");
            Instance = buildDatabase(context);
        }
        return Instance;
    }

    private static CovoiturageBd buildDatabase(final Context context){
        return Room.databaseBuilder(context, CovoiturageBd.class, "database")
                .addCallback(new RoomDatabase.Callback() {
                    public void onCreate (SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }})
                .allowMainThreadQueries()
                .build();
    }

}
