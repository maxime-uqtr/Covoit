package com.exemple.Covoit.controleur;

import android.util.Log;

import com.exemple.Covoit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public interface PlaceApi {

    static ArrayList<String> autoComplete(String input){
        ArrayList<String> arrayList = new ArrayList<String>();
        HttpURLConnection connexion = null;
        StringBuilder json = new StringBuilder();
        try{
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
            sb.append("input="+input);
            sb.append("&key=" + R.string.google_maps_key);
            URL url = new URL(sb.toString()); //On crée l'URL avec la chaine de caractère passée en paramètre
            connexion = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connexion.getInputStream());
            Log.i("test", "inpu");
            int ligne;

            char[] buff = new char[1024];
            //lecture du fichier json
            while((ligne=inputStreamReader.read(buff)) !=0){
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
            for(int i=0;i<predictions.length(); i++){
                String desc = predictions.getJSONObject(i).getString("description");
                arrayList.add(desc); //On ajoute la description destinée à la liste de l'adapter
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
