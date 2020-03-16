package com.exemple.Covoit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.bd.dao.CovoiturageDao;
import com.exemple.Covoit.models.CovoiturageInfo;
import com.facebook.stetho.Stetho;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CovoiturageBd bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stetho.initializeWithDefaults(this); //Ajout de stetho à l'activité
        bd = CovoiturageBd.getInstance(getApplicationContext());
        CovoiturageInfo c = new CovoiturageInfo(1, new Date(2020, 06,13),
                "Dep",
                "Arr",
                1, 1);
        bd.getCovoiturageDao().insertCovoiturage(c);
        LiveData<List<CovoiturageInfo>> covoiturages = bd.getCovoiturageDao().getCovoiturages(1);

        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new ListAdapteur(Arrays.asList(
                covoiturages.getValue().get(0),
                new CovoiturageInfo(
                        2, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2),
                new CovoiturageInfo(
                        3, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        4, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2),
                new CovoiturageInfo(
                        5, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        6, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2),
                new CovoiturageInfo(
                        7, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        8, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2),
                new CovoiturageInfo(
                        9, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        10, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2),
                new CovoiturageInfo(
                        11, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        12, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2))));
    }

    public CovoiturageBd getBd(){
        return bd;
    }

}
