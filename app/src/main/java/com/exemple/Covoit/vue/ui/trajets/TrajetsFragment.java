package com.exemple.Covoit.vue.ui.trajets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.ListAdapteur;
import com.exemple.Covoit.controleur.OnListClickListener;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Trajet;
import com.exemple.Covoit.models.Utilisateur;
import com.exemple.Covoit.vue.PopUpCovoiturage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrajetsFragment extends Fragment implements OnListClickListener {

    private TrajetsViewModel homeViewModel;
    private boolean isRotate = false;
    private CovoiturageBd bd;
    private RecyclerView rv;
    private PopUpCovoiturage popupCovoiturage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(TrajetsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_trajets, container, false);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //Cgmt
            }
        });

        bd = CovoiturageBd.getInstance(getContext());
        initBd();

        rv = view.findViewById(R.id.accueil_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new ListAdapteur(bd.getUtilisateurDao().getTrajetsConfirmes(1), this::onListClick));
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;

    }

    public void initBd(){
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
        bd.getUtilisateurDao().insert(listeU.toArray(new Utilisateur[]{}));

        List<Covoiturage> listeC = new ArrayList<>();
        listeC.add(new Covoiturage(new Date(2020, 4,14),
                "Québec",
                "Louiseville", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,15),
                "Louiseville",
                "Trois-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,14),
                "Québec",
                "Montréal", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,14),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,14),
                "Louiseville",
                "Montréal", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,20),
                "Montréal",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 6,24),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,4),
                "Louiseville",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,30),
                "Trois-Rivières",
                "Montréal", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,4),
                "Montréal",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,1),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 6,16),
                "Trois-Rivières",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        for(Covoiturage c : listeC){
            bd.getCovoiturageDao().insert(c);
        }

        ArrayList<Trajet> listeT = new ArrayList<>();
        listeT.add(new Trajet(1,1));
        listeT.add(new Trajet(1, 3));
        listeT.add(new Trajet(2, 2));
        listeT.add(new Trajet(3, 1));
        for(Trajet t : listeT){
            bd.getTrajetDao().insert(t);
        }

    }

    @Override
    public void onListClick(Covoiturage c) {
        if(popupCovoiturage == null) { //Première instance du popup
            popupCovoiturage = new PopUpCovoiturage(c);
            popupCovoiturage.show(getFragmentManager(), null);
        }
        else if(!popupCovoiturage.isVisible()){ //Si dialog non visible
            popupCovoiturage.setCovoiturage(c);
            popupCovoiturage.show(getFragmentManager(), null);
        }
        else{
            //Affichage dialog en cours
        }
    }
}