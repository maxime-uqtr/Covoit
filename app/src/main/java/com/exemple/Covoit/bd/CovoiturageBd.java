package com.exemple.Covoit.bd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.dao.CovoiturageDao;
import com.exemple.Covoit.bd.dao.TrajetDao;
import com.exemple.Covoit.bd.dao.UtilisateurDao;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Trajet;
import com.exemple.Covoit.models.Utilisateur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Covoiturage.class, Utilisateur.class, Trajet.class}, version = 1, exportSchema = false) //On exporte pas car pas définit le directory
public abstract class CovoiturageBd extends RoomDatabase {

    private static CovoiturageBd Instance;

    public abstract CovoiturageDao getCovoiturageDao();
    public abstract UtilisateurDao getUtilisateurDao();
    public abstract TrajetDao getTrajetDao();

    public static synchronized CovoiturageBd getInstance(final Context context){

        if(Instance == null){
            //context.deleteDatabase("database");
            Instance = buildDatabase(context);
        }
        return Instance;
    }

    private static CovoiturageBd buildDatabase(final Context context){
        return Room.databaseBuilder(context, CovoiturageBd.class, "database")
                .addCallback(new RoomDatabase.Callback() {
                    public void onCreate (SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(() -> iniSchema(db, Instance ,context));
                    }})
                .allowMainThreadQueries()
                .build();
    }

    private static void iniSchema(SupportSQLiteDatabase db, CovoiturageBd instance, Context context) {

        List<Utilisateur> listC = new ArrayList<>();
        listC.add(new Utilisateur(
                1, "admin",
                "covoit",
                "covoit@mail.com",
                "admin","0708099080",
                context.getString(R.string.urlLogo), true, true));
        listC.add(new Utilisateur(
                2,  "nom2",
                "prenom2",
                "mail2",
                "mdp2", "0102087020",
                "url2", false, true));
        Instance.getUtilisateurDao().insert(listC.toArray(new Utilisateur[]{}));

        List<Utilisateur> listeU = new ArrayList<>();
        listeU.add(new Utilisateur(3,
                "Dromont",
                "Matt",
                "mail",
                "mdp", "0708099080",
                "url", true, false));
        listeU.add(new Utilisateur(4,
                "Dion",
                "Angele",
                "mail2",
                "mdp2", "0102087020",
                "url2", false, true));
        Instance.getUtilisateurDao().insert(listeU.toArray(new Utilisateur[]{}));

        List<Covoiturage> listeC = new ArrayList<>();
        listeC.add(new Covoiturage(new Date(2020, 4,14),
                "Québec",
                "Louiseville", 6, 2,
                Instance.getUtilisateurDao().getAll().get(0).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,15),
                "Louiseville",
                "Trois-Rivières", (float) 5, 4,
                Instance.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,14),
                "Québec",
                "Montréal", (float) 5, 4,
                Instance.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,14),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                Instance.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,14),
                "Louiseville",
                "Montréal", (float) 5, 2,
                Instance.getUtilisateurDao().getAll().get(2).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,20),
                "Montréal",
                "Québec", (float) 5, 2,
                Instance.getUtilisateurDao().getAll().get(3).getId()));
        listeC.add(new Covoiturage(new Date(2020, 6,24),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                Instance.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,4),
                "Louiseville",
                "Québec", (float) 5, 2,
                Instance.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,30),
                "Trois-Rivières",
                "Montréal", (float) 5, 2,
                Instance.getUtilisateurDao().getAll().get(3).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,4),
                "Montréal",
                "Québec", (float) 5, 2,
                Instance.getUtilisateurDao().getAll().get(2).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,1),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                Instance.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 6,16),
                "Trois-Rivières",
                "Québec", (float) 5, 2,
                Instance.getUtilisateurDao().getAll().get(2).getId()));
        for(Covoiturage c : listeC){
            Instance.getCovoiturageDao().insert(c);
        }

        List<Trajet> listeT = new ArrayList<>();
        listeT.add(new Trajet(1,2));
        listeT.add(new Trajet(1, 3));
        listeT.add(new Trajet(2, 2));
        listeT.add(new Trajet(3, 1));
        Trajet trajet = new Trajet(1,3);
        trajet.setConfirme(true);
        listeT.add(trajet);
        trajet = new Trajet(4,1);
        trajet.setConfirme(false);
        listeT.add(trajet);
        for(Trajet t : listeT){
            Instance.getTrajetDao().insert(t);
        }
    }

}
