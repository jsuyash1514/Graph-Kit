package com.example.suyash.graph;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PieChartNew extends AppCompatActivity {
    RecyclerView recyclerView;
//    List<PieChartEntryModel> pieChartEntries = new ArrayList<>();
    PieChartEntryAdapter pieChartEntryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_new);

        Button addEntry = findViewById(R.id.pieChartAddEntryButton);
        ImageButton close = findViewById(R.id.pieChartNewClose);
        ImageButton done = findViewById(R.id.pieChartNewDone);
        TextView noEnteries = findViewById(R.id.pieChartNoEntries);
        recyclerView = findViewById(R.id.pieChartEntriesListRecyclerView);

        PieChartEntryDatabase db = Room.databaseBuilder(getApplicationContext(),PieChartEntryDatabase.class,"pieChartEntries")
                .allowMainThreadQueries()
                .build();

        List<PieChartEntryModel> pieChartEntries = db.pieChartEntryModelDao().getAllPieChartEntries();
        pieChartEntryAdapter = new PieChartEntryAdapter(this, pieChartEntries);

        RecyclerView.LayoutManager recycler = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recycler);
        recyclerView.setAdapter(pieChartEntryAdapter);


        if (pieChartEntries.size() == 0) {
            noEnteries.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noEnteries.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }


        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PieChartAddEntry.class);
                finish();
                startActivity(intent);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PieChartResult.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PieChartLanding.class);
        finish();
        startActivity(intent);
    }
}
