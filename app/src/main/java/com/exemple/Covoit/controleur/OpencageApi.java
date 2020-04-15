package com.exemple.Covoit.controleur;

import android.util.Log;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public interface OpencageApi {

    static ArrayList<String> autoComplete(String input){
        ArrayList<String> arrayList = new ArrayList<String>();
        HttpURLConnection connexion = null;
        StringBuilder json = new StringBuilder();
        try{
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
            sb.append("input=" + input);
            sb.append("&key=AIzaSyAvAft3BV6eE_TTVvZiChAbxBj2i6Drgp0"); //On ajoute la clé d'API à l'URL
            URL url = new URL(sb.toString()); //On crée l'URL avec la chaine de caractère passée en paramètre
            connexion = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connexion.getInputStream());

            int ligne;
            char[] buff = new char[1024];
            //lecture du fichier json
            while((ligne=inputStreamReader.read(buff))!=-1){
                json.append(buff, 0, ligne);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(connexion!=null){
                connexion.disconnect();
            }
        }

        try{
            JSONObject jsonObject = new JSONObject(json.toString());
            JSONArray predictions = jsonObject.getJSONArray("predictions"); //On veut savoir les prédictions transmises dans le fichier .json
            if(predictions.length()!=0) {
                Log.i("test", "no json error");
                for (int i = 0; i < predictions.length(); i++) {
                    String desc = predictions.getJSONObject(i).getString("description");
                    arrayList.add(desc); //On ajoute la description destinée à la liste de l'adapter
                }
            }
            else{
                Log.i("test", "error");
                String erro = jsonObject.getString("error_message");
                arrayList.add(erro);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0; i<arrayList.size(); i++){
            Log.i("test", arrayList.get(i));
        }

        return arrayList;
    }

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
        request.setNoAnnotations(true);
        request.setNoDedupe(true); //resultats sont non dupliqués
        //response
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        //result
        JOpenCageLatLng result = response.getFirstPosition();

        return new LatLng(result.getLat(), result.getLng());
    }
}
