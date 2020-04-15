package com.exemple.Covoit.vue;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Display;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.OpencageApi;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PopUpCovoiturage extends DialogFragment implements OnMapReadyCallback{
    private static View view;

    private TextView tvDate;
    private TextView tvNbPassager;
    private ImageView imageFermer;
    private TextView tvPNom;
    private ImageView imageAppel;
    private TextView tvTrajet;
    private TextView tvPrix;
    private GoogleMap mMap;
    String numeroConducteur;
    private Covoiturage covoiturage;

    public PopUpCovoiturage() {
        // Required empty public constructor
    }

    public PopUpCovoiturage(Covoiturage c) {
        covoiturage = c;
    }

    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
        Point size = new Point();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width = size.x;
        int height = size.y;

        window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT); //taille du pop ip à la taille de l'écran

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view != null) { //Si vue déjà inflatée, on la supprime pour éviter de la dupliquer
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeAllViews(); //On remove les vues, dont vue
        }
        try {
            view = inflater.inflate(R.layout.popup_covoiturage, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDate = view.findViewById(R.id.popupCovoiturage_date);
        tvNbPassager = view.findViewById(R.id.popupCovoiturage_nbPassager);
        imageFermer = view.findViewById(R.id.popupCovoiturage_fermer);
        tvPNom = view.findViewById(R.id.popupCovoiturage_P_Nom);
        tvTrajet = view.findViewById(R.id.popupCovoiturage_departArrivee);
        tvPrix = view.findViewById(R.id.popupCovoiturage_prix);
        imageAppel = view.findViewById(R.id.popupCovoiturage_tel);

        //Ajout du listener sur l'image fermer les détails du covoiturage
        imageFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //Ajout du listener sur l'image téléphone
        imageAppel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appeler();
            }
        });

        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentByTag("mapFragment");
        if (mapFragment == null) { //Si il n'existe pas encore, on rajoute le fragment de la GoogleMaps
            mapFragment = new SupportMapFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.popupCovoiturage_map, mapFragment, "mapFragment");
            ft.commit();
            fm.executePendingTransactions();
        }
        mapFragment.getMapAsync(this);
        setData(covoiturage);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Activity activity = getActivity();
        LocationManager lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null) {
            LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
            placerCurseur(pos);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(pos).zoom(9).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        else{
            tvPNom.setText("posi");
        }
        placerCurseur(OpencageApi.getLatLng(covoiturage.getVilleDep()));
        placerCurseur(OpencageApi.getLatLng(covoiturage.getVilleArr()));
    }

    public void setData(Covoiturage c){
        String contenu = c.getDate().getDay() + "/" + c.getDate().getMonth() + "/" + c.getDate().getYear();
        tvDate.setText(contenu);
        contenu = c.getNbPassager() + " places";
        tvNbPassager.setText(contenu);
        Utilisateur conducteur = CovoiturageBd.getInstance(getContext()).getUtilisateurDao().get(c.getConducteurId()); //On prend les renseignements sur le conducteur
        char initialeNom = conducteur.getNom().charAt(0); //On garde la première lettre du nom de famille
        contenu = conducteur.getPrenom() + " " + initialeNom + ".";
        tvPNom.setText(contenu);
        contenu = c.getVilleDep() + " - " + c.getVilleArr();
        tvTrajet.setText(contenu);
        contenu = "$" + c.getPrix();
        tvPrix.setText(contenu);
        numeroConducteur = conducteur.getTelephone();
    }

    public void appeler(){
        if(numeroConducteur.length()>0){
            Activity activity = getActivity();
            if(activity instanceof RechercheActivite){
                ((RechercheActivite) activity).appelerConducteur(numeroConducteur);
            }
        }
    }

    public void placerCurseur(LatLng position) {
        if (mMap != null) {
            MarkerOptions marker = new MarkerOptions().position(position);
            mMap.addMarker(marker);
        }
    }

}