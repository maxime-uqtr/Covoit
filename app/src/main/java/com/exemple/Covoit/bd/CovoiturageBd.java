package com.exemple.Covoit.bd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.exemple.Covoit.bd.dao.CovoiturageDao;
import com.exemple.Covoit.bd.dao.UtilisateurDao;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Covoiturage.class, Utilisateur.class}, version = 1, exportSchema = false) //On exporte pas car pas définit le directory
public abstract class CovoiturageBd extends RoomDatabase {

    private static CovoiturageBd INSTANCE;

    public abstract CovoiturageDao getCovoiturageDao();
    public abstract UtilisateurDao getUtilisateurDao();

    public static synchronized CovoiturageBd getInstance(final Context context){

        if(INSTANCE == null){
            context.deleteDatabase("database");
            INSTANCE = buildDatabase(context);
        }
        else{
            INSTANCE.clearAllTables();
        }
        return INSTANCE;
    }

    private static CovoiturageBd buildDatabase(final Context context){
        return Room.databaseBuilder(context, CovoiturageBd.class, "database")
                .addCallback(new RoomDatabase.Callback() {
                    public void onCreate (SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(() -> iniSchema(db, INSTANCE,context));
                    }})
                .allowMainThreadQueries()
                .build();
    }

    private static void iniSchema(SupportSQLiteDatabase db, CovoiturageBd instance, Context context) {

        List<Utilisateur> listC = new ArrayList<>();
        listC.add(new Utilisateur(
                1, "nom",
                "prenom",
                "mail",
                "mdp",
                "url", true, false));
        listC.add(new Utilisateur(
                2,  "nom2",
                "prenom2",
                "mail2",
                "mdp2",
                "url2", false, true));
        INSTANCE.getUtilisateurDao().insert(listC.toArray(new Utilisateur[]{}));

    }

}
