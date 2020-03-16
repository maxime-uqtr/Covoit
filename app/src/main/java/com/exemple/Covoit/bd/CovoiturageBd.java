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
import com.exemple.Covoit.models.CovoiturageInfo;
import com.exemple.Covoit.models.UtilisateurInfo;

@Database(entities = {CovoiturageInfo.class, UtilisateurInfo.class}, version = 1, exportSchema = false) //On exporte pas car pas définit le directory
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
                .addCallback(preremplirDatabase())
                .build();
    }

    //Test de la bd
    private static Callback preremplirDatabase(){
        return new RoomDatabase.Callback() {

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
