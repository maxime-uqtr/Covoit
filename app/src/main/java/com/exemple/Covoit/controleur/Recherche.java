package com.exemple.Covoit.controleur;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.models.Covoiturage;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Recherche  {

    public List<Covoiturage> rechercher(List<Covoiturage> covoiturages, String selectionDepart, String selectionDestination, Date dateRecherchee) {
        List<Covoiturage> listeTrouve = null;
            for (Covoiturage c : covoiturages) {
                if (c.getVilleDep() == selectionDepart && c.getVilleArr() == selectionDestination && c.getDate() == dateRecherchee) {
                    listeTrouve.add(c);
                }
            }
        return listeTrouve;
    }
}
