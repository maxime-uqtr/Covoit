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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.ListAdapteur;
import com.exemple.Covoit.controleur.OnListClickListener;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.vue.PopUpCovoiturage;

import java.util.List;

public class TrajetsFragment extends Fragment implements OnListClickListener, SwipeRefreshLayout.OnRefreshListener {

    private TrajetsViewModel homeViewModel;
    private boolean isRotate = false;
    private CovoiturageBd bd;
    private RecyclerView rv;
    private ListAdapteur adapteur;
    private SwipeRefreshLayout swipeContainer;
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

        rv = view.findViewById(R.id.trajets_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        List<Covoiturage> covs = bd.getCovoiturageDao().getAll();
        adapteur = new ListAdapteur(covs, this);

        rv.setAdapter(adapteur);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        //On instancie le SwipeRefreshLayout
        swipeContainer = view.findViewById(R.id.trajets_swipeContainer);
        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        //adapteur.setCovoiturages(getTrajetsData());
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
        adapteur.clear();

        List<Covoiturage> trajets = bd.getCovoiturageDao().getCovoituragesConfirmes(1);
        adapteur.addAll(trajets);
        if (swipeContainer.isRefreshing()) {
            swipeContainer.setRefreshing(false);
        }
    }
}