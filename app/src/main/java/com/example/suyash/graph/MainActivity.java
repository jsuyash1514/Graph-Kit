package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.example.suyash.graphlibrary.LineGraph;
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
                LineGraph lineGraph = new LineGraph();
                lineGraph.addDataPoint(1,1);
                lineGraph.addDataPoint(2,7);
                lineGraph.addDataPoint(4,3);
                lineGraph.addDataPoint(5,3);
                lineGraph.addDataPoint(7,9);
                lineGraph.setGraphColor(Color.BLACK);
                lineGraph.setBackgroundColor(Color.WHITE);
                Bitmap bitmap = lineGraph.plot();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(getApplicationContext(),Graph.class);
                intent.putExtra("image", byteArray);
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

            }
        });

    }

}
