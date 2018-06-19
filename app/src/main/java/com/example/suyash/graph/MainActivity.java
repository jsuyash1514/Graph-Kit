package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import com.example.suyash.graphlibrary.BarGraph;

import com.example.suyash.graphlibrary.PieChart;

import java.io.ByteArrayOutputStream;

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
                PieChart pieChart = new PieChart();
                pieChart.addDataPoint("Football",(float)40.1,Color.parseColor("#34495E"));
                pieChart.addDataPoint("Cricket", (float)30.9, Color.parseColor("#EC7063"));
                pieChart.addDataPoint("Basketball", (float)15.8,Color.parseColor("#2ECC71"));
                pieChart.addDataPoint("Voleyball",(float)12.4,Color.parseColor("#F5B041"));
                Bitmap bitmap = pieChart.plot();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(getApplicationContext(),Graph.class);
                intent.putExtra("image", byteArray);
                startActivity(intent);
                finish();
            }
        });

        barGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarGraph barGraph = new BarGraph();
                barGraph.addDataPoints("2014",5,Color.parseColor("#34495E"));
                barGraph.addDataPoints("2015",9,Color.parseColor("#EC7063"));
                barGraph.addDataPoints("2016",2,Color.parseColor("#2ECC71"));
                barGraph.addDataPoints("2017",4,Color.parseColor("#F5B041"));
                Bitmap bitmap = barGraph.plot();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(getApplicationContext(),Graph.class);
                intent.putExtra("image", byteArray);
                startActivity(intent);
                finish();
            }
        });

    }

}
