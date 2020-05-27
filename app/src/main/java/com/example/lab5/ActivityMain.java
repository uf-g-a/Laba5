package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    private Cats catsInfo;
    private EndlessRecyclerViewScrollListener scrollListener;
    private Spinner s_breeds;
    private RecyclerView rv_list;
    private LinearLayoutManager linearLayoutManager;
    AdapterCats adapterCats ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = this.getSharedPreferences("Save", Context.MODE_PRIVATE);
        String breedID = sp.getString("BreedID", "0");

        catsInfo = Cats.getInstance();
        adapterCats = new AdapterCats(this);
        rv_list = findViewById(R.id.rv_list);
        linearLayoutManager = new LinearLayoutManager(this);
        rv_list.setLayoutManager(linearLayoutManager);

        if (!breedID.equals("0")) {
            loadingSpinner(breedID);
            catsInfo.getBread().setId(breedID);
            try {
                new LoadingNewCats(adapterCats).execute(new URL("https://api.thecatapi.com/v1/images/search?limit=5&page=0&order=Desc&api_key=e6ecc7ad-d624-474d-b628-88128c4d17eb&breed_ids=" + catsInfo.getBread().getId()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            rv_list.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    try {
                        new LoadingNewCats(adapterCats).execute(new URL("https://api.thecatapi.com/v1/images/search?limit=5&page=" + page + "&order=Desc&api_key=e6ecc7ad-d624-474d-b628-88128c4d17eb&breed_ids=" + catsInfo.getBread().getId()));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            });
            rv_list.setAdapter(adapterCats);

        } else {
            try {
                new LoadingNewCats(adapterCats).execute(new URL("https://api.thecatapi.com/v1/images/search?limit=5&page=0&order=Desc&api_key=e6ecc7ad-d624-474d-b628-88128c4d17eb"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                    Log.d("TESTER", String.valueOf(page));
                    URL url = null;
                    try {
                        String LINK = "https://api.thecatapi.com/v1/images/search?limit=5&page=" + page + "&order=Desc&api_key=e6ecc7ad-d624-474d-b628-88128c4d17eb";
                        url = new URL(LINK);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new LoadingNewCats(adapterCats).execute(url);
                }
            };
            rv_list.setAdapter(adapterCats);
            rv_list.addOnScrollListener(scrollListener);
            loadingSpinner("0");
        }

    }

    private void loadingSpinner(String breedID) {
        s_breeds = findViewById(R.id.s_breeds);
        s_breeds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catsInfo.setBread(catsInfo.getBreeds().get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }
        });


        ArrayList<String> breedNames = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, breedNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_breeds.setAdapter(adapter);

        try {
            new LoadingBreeds(adapter, breedNames, breedID, s_breeds).execute(new URL("https://api.thecatapi.com/v1/breeds"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void onClickSearch(View v) {
        Toast.makeText(getBaseContext(), "Breed = " + catsInfo.getBread().getBreed() + " " +
                catsInfo.getBread().getId(), Toast.LENGTH_SHORT).show();

        catsInfo.getImageURLs().clear();
        catsInfo.getImage().clear();
        rv_list.clearOnScrollListeners();

        if (catsInfo.getBread().getId().equals("0")) {
            try {
                new LoadingNewCats(adapterCats).execute(new URL("https://api.thecatapi.com/v1/images/search?limit=5&page=0&order=Desc&api_key=e6ecc7ad-d624-474d-b628-88128c4d17eb"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            rv_list.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                    Log.d("TESTER", String.valueOf(page));
                    URL url = null;
                    try {
                        String LINK = "https://api.thecatapi.com/v1/images/search?limit=5&page=" + page + "&order=Desc&api_key=e6ecc7ad-d624-474d-b628-88128c4d17eb";
                        url = new URL(LINK);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new LoadingNewCats(adapterCats).execute(url);
                }
            });
        } else {
            try {
                new LoadingNewCats(adapterCats).execute(new URL("https://api.thecatapi.com/v1/images/search?limit=5&page=0&order=Desc&api_key=e6ecc7ad-d624-474d-b628-88128c4d17eb&breed_ids=" + catsInfo.getBread().getId()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            rv_list.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    try {
                        new LoadingNewCats(adapterCats).execute(new URL("https://api.thecatapi.com/v1/images/search?limit=5&page=" + page + "&order=Desc&api_key=e6ecc7ad-d624-474d-b628-88128c4d17eb&breed_ids=" + catsInfo.getBread().getId()));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void onClickHistory(View v) {
        Intent intent = new Intent(this, ActivityHistory.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sp = this.getSharedPreferences("Save", Context.MODE_PRIVATE);
        sp.edit().putString("BreedID", catsInfo.getBread().getId()).apply();
    }
}
