package com.example.lab5;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class LoadingBreeds extends AsyncTask<URL, Void, String> {

    private Cats catsInfo;
    private int position;
    private Spinner s_breeds;
    private String breedId;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> breedNames;


    public LoadingBreeds(ArrayAdapter<String> adapter, ArrayList<String> breedNames, String breedID, Spinner s_breed) {
        catsInfo = Cats.getInstance();
        this.adapter = adapter;
        this.breedNames = breedNames;
        this.breedId = breedID;
        this.s_breeds = s_breed;
    }

    protected String doInBackground(URL... urls) {

        try {
            String responseToQuery = ConnectAPI.query(urls[0]);

            JSONObject breed;
            JSONArray breeds = new JSONArray(responseToQuery);

            Breed bufferBreed;
            for (int i = 0; i < breeds.length(); i++) {
                breed = breeds.getJSONObject(i);
                bufferBreed = new Breed();
                bufferBreed.setId(breed.getString("id"));
                bufferBreed.setBreed(breed.getString("name"));
                catsInfo.getBreeds().add(bufferBreed);
                if (breed.getString("id").equals(breedId)) {
                    position = i;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "0";
    }

    protected void onPostExecute(String response) {
        for (int i = 0; i < catsInfo.getBreeds().size(); i++) {
            breedNames.add(catsInfo.getBreeds().get(i).getBreed());
        }
        adapter.notifyDataSetChanged();
        s_breeds.setSelection(position + 1);
    }
}
