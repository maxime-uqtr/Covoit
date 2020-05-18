package com.exemple.Covoit.controleur;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TelechargerImage extends AsyncTask<String, Void, Bitmap> {

	@SuppressLint("WrongThread")
	@Override
	protected Bitmap doInBackground(String... urls) {
		try {
			URL url = new URL(urls[0]);
			HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
			InputStream inputStream = connexion.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			return bitmap;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}