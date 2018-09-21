package com.example.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView lineGraph, pieChart, barGraph, freeCurve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        lineGraph = findViewById(R.id.card_lineGraph);
        pieChart = findViewById(R.id.card_pieChart);
        barGraph = findViewById(R.id.card_barGraph);
        freeCurve = findViewById(R.id.card_freeCurve);

        lineGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LineGraphLanding.class);
                finish();
                startActivity(intent);
            }
        });

        pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PieChartLanding.class);
                finish();
                startActivity(intent);
            }
        });

        barGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BarGraphLanding.class);
                finish();
                startActivity(intent);
            }
        });

        freeCurve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FreeCurveLanding.class);
                finish();
                startActivity(intent);
            }
        });

    }

    public void onBackPressed(){
        finish();
    }

}
