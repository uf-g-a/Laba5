package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ActivityHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TableHistory tableHistory = new TableHistory(this);

        RecyclerView rv_history = findViewById(R.id.rv_history);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_history.setLayoutManager(layoutManager);

        AdapterHistory adapterHistory = new AdapterHistory(this, tableHistory.select());
        rv_history.setAdapter(adapterHistory);
    }
}
