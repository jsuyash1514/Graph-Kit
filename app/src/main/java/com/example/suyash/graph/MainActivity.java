package com.example.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button lineGraph, pieChart, barGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lineGraph = (Button)findViewById(R.id.button_lineGraph);
        pieChart = (Button)findViewById(R.id.button_pieChart);
        barGraph = (Button)findViewById(R.id.button_barGraph);

        lineGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.suyash.graph.LineGraph.class);
                startActivity(intent);
                finish();
            }
        });

        pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), com.example.suyash.graph.PieChart.class);
                startActivity(intent);
                finish();
            }
        });

        barGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),com.example.suyash.graph.BarGraph.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
