package com.exemple.Covoit.controleur;

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

public class PlaceApi {

    public ArrayList<String> autoComplete(String input){
        ArrayList arrayList = null;
        HttpURLConnection connexion = null;
        StringBuilder json = null;
        try{
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
            sb.append("input="+input);
            sb.append("$key=AIzaSyAvAft3BV6eE_TTVvZiChAbxBj2i6Drgp0"/*+ R.string.google_maps_key*/);
            URL url = new URL(sb.toString());
            InputStreamReader inputStreamReader = new InputStreamReader(connexion.getInputStream());

            int read;

            char[] buff = new char[1024];
            while((read=inputStreamReader.read(buff)) !=0){
                json.append(buff, 0, read);
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
            JSONObject jsonObject=new JSONObject(json.toString());
            JSONArray prediction = jsonObject.getJSONArray("predictions");
            for(int i=0;i<prediction.length(); i++){
                arrayList.add((prediction.getJSONObject(i).getString("description")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
