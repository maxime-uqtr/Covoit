package com.exemple.Covoit.bd;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.exemple.Covoit.bd.dao.CovoiturageDao;
import com.exemple.Covoit.bd.dao.UtilisateurDao;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Covoiturage.class, Utilisateur.class}, version = 2, exportSchema = false) //On exporte pas car pas définit le directory
public abstract class CovoiturageBd extends RoomDatabase {

    private static CovoiturageBd INSTANCE;

    public abstract CovoiturageDao getCovoiturageDao();
    public abstract UtilisateurDao getUtilisateurDao();

    public static synchronized CovoiturageBd getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = buildDatabase(context);
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

        List<Utilisateur> listeU = new ArrayList<>();
        listeU.add(new Utilisateur(
                        1, "nom",
                        "prenom",
                        "mail",
                        "mdp",
                        "url", true, false));
        listeU.add(new Utilisateur(
                        2,  "nom2",
                "prenom2",
                "mail2",
                "mdp2",
                "url2", false, true));
        INSTANCE.getUtilisateurDao().insert(listeU.toArray(new Utilisateur[]{}));

        List<Covoiturage> listeC = new ArrayList<>();
        listeC.add(new Covoiturage(1, new Date(2020, 06,13),
                "Dep",
                "Arr", (float) 10, 1, INSTANCE.getUtilisateurDao().get(1).getId()));
        listeC.add(new Covoiturage(
                2, new Date(2020, 07,14),
                "Depa",
                "Arri", (float) 5, 2,
                2));
        INSTANCE.getCovoiturageDao().insert(listeC.toArray(new Covoiturage[]{}));

    }

}
