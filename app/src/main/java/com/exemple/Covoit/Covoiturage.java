package com.exemple.Covoit;

import java.util.Date;

public class Covoiturage {
    private Date date;
    private String villeDep;
    private String getVilleArr;
    private int nbPassager;

    public Covoiturage(Date date, String villeDep, String getVilleArr, int nbPassager) {
        this.date = date;
        this.villeDep = villeDep;
        this.getVilleArr = getVilleArr;
        this.nbPassager = nbPassager;
    }

    public Date getDate() {
        return date;
    }

    public String getVilleDep() {
        return villeDep;
    }

    public String getGetVilleArr() {
        return getVilleArr;
    }

    public int getNbPassager() {
        return nbPassager;
    }
}
