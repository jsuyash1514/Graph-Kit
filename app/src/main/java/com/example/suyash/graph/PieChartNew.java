package com.example.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PieChartNew extends AppCompatActivity {
    RecyclerView recyclerView;
    List<PieChartEntryModel> pieChartEntries = new ArrayList<>();
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
        pieChartEntryAdapter = new PieChartEntryAdapter(this, pieChartEntries);

        PieChartEntryModel pieChartEntryModel = new PieChartEntryModel("Male",49.7,getResources().getColor(R.color.yellow));
        pieChartEntries.add(pieChartEntryModel);
        pieChartEntryAdapter.notifyDataSetChanged();

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
                startActivity(new Intent(getApplicationContext(), PieChartAddEntry.class));
                finish();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PieChartLanding.class));
        finish();
    }
}
