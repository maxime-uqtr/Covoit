package com.exemple.Covoit.controleur;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.google.android.gms.maps.model.LatLng;

public interface OpencageApi {

    static LatLng getLatLng(String ville){
        LatLng minQuebec = new LatLng(-79.76, 44.99);
        LatLng maxQuebec = new LatLng(-57.10, 62.59);
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("b854379cfdb949a7b169f24e581f9f6f");

        //request
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(ville);

        //optional parameters
        request.setRestrictToCountryCode("ca"); //resultats du Canada uniquement
        request.setBounds(minQuebec.latitude, minQuebec.longitude, maxQuebec.latitude, maxQuebec.longitude); //resultats dans bounding box
        request.setMinConfidence(1);
        request.setNoAnnotations(true); //exclus les annotations supplémentaires
        request.setNoDedupe(true); //resultats sont non dupliqués
        request.setLimit(1); //retourne uniquement premier resultat

        //response
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);

        //result
        JOpenCageLatLng result = response.getFirstPosition();

        return new LatLng(result.getLat(), result.getLng());
    }
}
