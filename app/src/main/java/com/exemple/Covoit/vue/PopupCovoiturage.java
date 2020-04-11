package com.exemple.Covoit.vue;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PopupCovoiturage extends Dialog implements OnMapReadyCallback {

    public AppCompatActivity activity;
    public TextView tvDate;
    public TextView tvNbPassager;
    public TextView tvPNom;
    public ImageView imageAppel;
    public TextView tvTrajet;
    public TextView tvPrix;
    private GoogleMap mMap;

    String numeroConducteur;


    public PopupCovoiturage(AppCompatActivity activity){
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.popup_covoiturage);
        //On initialise la taille du pop up
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(this.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        this.show();
        this.getWindow().setAttributes(lp);
        this.activity = activity;
        tvDate = findViewById(R.id.popupCovoiturage_date);
        tvNbPassager = findViewById(R.id.popupCovoiturage_nbPassager);
        tvPNom = findViewById(R.id.popupCovoiturage_P_Nom);
        tvTrajet = findViewById(R.id.popupCovoiturage_departArrivee);
        tvPrix = findViewById(R.id.popupCovoiturage_prix);
        imageAppel = findViewById(R.id.popupCovoiturage_tel);

        //Set up de la map
        SupportMapFragment mapFragment = (SupportMapFragment) activity.getSupportFragmentManager().findFragmentById(R.id.popupCovoiturage_map);
        mapFragment.getMapAsync(this);

        //Ajout du listener sur l'image téléphone
        imageAppel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appeler();
            }
        });

    }

    public void build(){
        show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LocationManager lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setInterval(25000);
            locationRequest.setFastestInterval(20000);
            locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null) {
            LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(pos).title("Marker in device location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
        }
    }

    public void setData(Covoiturage c, CovoiturageBd bd){
        tvDate.setText(c.getDate().getDay() + "/" + c.getDate().getMonth() + "/" + c.getDate().getYear());
        tvNbPassager.setText(c.getNbPassager() + " places");
        Utilisateur conducteur = bd.getUtilisateurDao().get(c.getConducteurId()); //On prend les renseignements sur le conducteur
        char initialeNom = conducteur.getNom().charAt(0); //On garde la première lettre du nom de famille
        tvPNom.setText(conducteur.getPrenom() + " " + initialeNom + ".");
        tvTrajet.setText(c.getVilleDep() + " - " + c.getVilleArr());
        tvPrix.setText("$" + c.getPrix());
        numeroConducteur = conducteur.getTelephone();
    }

    public void appeler(){
        if(numeroConducteur.length()>0){
            if(activity instanceof RechercheActivity){
                ((RechercheActivity) activity).appelerConducteur(numeroConducteur);
                Log.i("test", "test appel " + numeroConducteur);
            }
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SupportMapFragment f = (SupportMapFragment) activity.getSupportFragmentManager().findFragmentById(R.id.popupCovoiturage_map);
        if (f != null)
            activity.getSupportFragmentManager().beginTransaction().remove(f).commit(); //On remove la vue du fragment afin de la recréer à la prochaine instance
    }
}
