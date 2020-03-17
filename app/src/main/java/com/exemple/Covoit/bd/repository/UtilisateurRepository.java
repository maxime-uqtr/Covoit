/*
package com.exemple.Covoit.bd.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.exemple.Covoit.bd.dao.UtilisateurDao;
import com.exemple.Covoit.models.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurDaoRepository {
    private UtilisateurDao dao;
    private LiveData<List<Utilisateur>> data;

    public UtilisateurDaoRepository(Application app){
        dao = AppDatabase.getInstance(app.getApplicationContext()).getUtilisateurDao();
    }

    public void save(Utilisateur... produits){
        // TODO
        new Saver(dao).execute(produits);
    }

    public void delete(Utilisateur... produits){
        // TODO
        new Delete(dao).execute(produits);
    }

    public LiveData<List<Utilisateur>> allLive(){
        update(dao);
        if (data == null)
            data = dao.getLiveAll();

        return data;
    }

    public List<Utilisateur> all(){
        update(dao);
        return dao.getAll();
    }

    */
/**
     * Update les données locals en fonction du serveur
     *//*

    public static void update(UtilisateurDao dao){
        // si rien dans la bdd local on cherche sur le serveur distant
        if(dao.getAll().size() == 0){
            // on simule le serveur distant
            List<Utilisateur> UtilisateurList = new ArrayList<>();
            UtilisateurList.add(new Utilisateur(1, "nom", "prenom", "mail", "mdp", "url", true, false));
            UtilisateurList.add(new Utilisateur(2, "nom", "prenom", "mail", "mdp", "url", false, true));
            UtilisateurList.add(new Utilisateur(3, "nom", "prenom", "mail", "mdp", "url", true, false));
            UtilisateurList.add(new Utilisateur(4, "nom", "prenom", "mail", "mdp", "url", false, true));

            for (Utilisateur Utilisateur:
                    UtilisateurList) {
                // update timesstamps
                Utilisateur.timestampsCreated();
                // insert dans la bdd locale
                dao.insert(Utilisateur);
            }
        }
    }


    */
/**
     * Sauvegarde dans la bdd en Async.
     *//*

    class Saver extends AsyncTask<Utilisateur, Void, Void> {
        private UtilisateurDao dao;

        public Saver(UtilisateurDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Utilisateur... utilisateurs) {
            for (Utilisateur u: utilisateurs) {
                Utilisateur.timestampsCreated();
                dao.insert(u);
            }
            return null;
        }
    }

    */
/**
     * Supprime dans la bdd en Async.
     *//*

    class Delete extends AsyncTask<Utilisateur, Void, Void>{
        private UtilisateurDao dao;

        public Delete(UtilisateurDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Utilisateur... utilisateurs) {
            for(Utilisateur u : utilisateurs){
                dao.delete(u);
            }
            return null;
        }
    }
}
*/
