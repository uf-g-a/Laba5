package com.example.lab5;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class LoadingNewCats extends AsyncTask<URL, Void, String> {

    private AdapterCats adapterCats;
    private Cats catInfo;

    public LoadingNewCats(AdapterCats adapterCats) {
        this.adapterCats = adapterCats;
        catInfo = Cats.getInstance();
    }

    protected String doInBackground(URL... urls) {

        try {
            String responseToQuery = ConnectAPI.query(urls[0]);

            JSONObject cat;
            JSONArray cats = new JSONArray(responseToQuery);

            for (int i = 0; i < cats.length(); i++) {
                cat = cats.getJSONObject(i);
                catInfo.getImageURLs().add(cat.getString("url"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "0";
    }

    protected void onPostExecute(String response) {
        new LoadingImage(adapterCats).execute();
        for (int i = 0; i < catInfo.getImageURLs().size(); i++) {
            Log.d("TESTER", catInfo.getImageURLs().get(i) + i);
        }
    }
}
