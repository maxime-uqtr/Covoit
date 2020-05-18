package com.exemple.Covoit.vue.ui.trajets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.ControleurTrajets;
import com.exemple.Covoit.controleur.ListAdapteur;
import com.exemple.Covoit.controleur.OnListClickListener;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;
import com.exemple.Covoit.models.UtilisateurActuel;
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.CovoiturageService;
import com.exemple.Covoit.vue.PopUpCovoiturage;

import java.util.ArrayList;
import java.util.List;

public class TrajetsFragment extends Fragment implements OnListClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static CovoiturageService apiInterface;
    private CovoiturageBd bd;

    private TrajetsViewModel trajetsViewModel;
    private TextView texte;
    private RecyclerView rv;
    private ListAdapteur adaptateur;
    private SwipeRefreshLayout swipeContainer;
    private PopUpCovoiturage popupCovoiturage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trajetsViewModel =
                ViewModelProviders.of(this).get(TrajetsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_trajets, container, false);

        apiInterface = ApiClient.getApiClient().create(CovoiturageService.class);
        swipeContainer = view.findViewById(R.id.trajets_swipeContainer);
        Utilisateur user = UtilisateurActuel.getUtilisateur();
        trajetsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    //Cgmt
                }
            });

        rv = view.findViewById(R.id.trajets_recyclerView);
        texte = view.findViewById(R.id.trajets_texte);

        if(user.isPassager()) {
            bd = CovoiturageBd.getInstance(getContext());
            texte.setVisibility(View.INVISIBLE);
            rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
            adaptateur = new ListAdapteur(new ArrayList<>(), this);
            rv.setAdapter(adaptateur);
            rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

            //On instancie le SwipeRefreshLayout
            swipeContainer.setOnRefreshListener(this);
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }
        else{
            texte.setText(R.string.tvPassager);
            rv.setVisibility(View.INVISIBLE);
            swipeContainer.setVisibility(View.INVISIBLE);
        }

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        Utilisateur u = UtilisateurActuel.getUtilisateur();
        if(u.isPassager()) {
            adaptateur.clear();
            ControleurTrajets.updateRides(adaptateur, apiInterface, getActivity().getApplicationContext()); //On actualise les trajets
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

    @Override
    public void onRefresh() {
        Utilisateur u = UtilisateurActuel.getUtilisateur();
        if(u.isPassager()) {
            adaptateur.clear();
            ControleurTrajets.updateRides(adaptateur, apiInterface, getActivity().getApplicationContext());
            if (swipeContainer.isRefreshing()) {
                swipeContainer.setRefreshing(false);
            }
        }
    }
}