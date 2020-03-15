package com.exemple.Covoit.bd;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.exemple.Covoit.bd.dao.CovoiturageDao;
import com.exemple.Covoit.bd.dao.UtilisateurDao;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;

@Database(entities = {Covoiturage.class, Utilisateur.class}, version = 1, exportSchema = false)
public abstract class SauvegarderCovoituragesBd extends RoomDatabase {

    private static volatile SauvegarderCovoituragesBd INSTANCE;

    public abstract CovoiturageDao covoiturageDao();
    public abstract UtilisateurDao utilisateurDao();

    public static SauvegarderCovoituragesBd getInstance(Context context){
        if(INSTANCE == null){
            synchronized (SauvegarderCovoituragesBd.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SauvegarderCovoituragesBd.class, "MyDatabase.db")
                            .addCallback(preremplirDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //Test de la bd
    private static Callback preremplirDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db){
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("nom","Rives-Niessel");
                contentValues.put("prenom","Maxime");
                contentValues.put("mail","maximervs78@gmail.com");
                contentValues.put("mdp","mdp123");
                contentValues.put("urlPhoto","https://example.jpg?auto=compress,format&q=80&h=100&dpr=2");

                db.insert("Utilisateur", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }
}
