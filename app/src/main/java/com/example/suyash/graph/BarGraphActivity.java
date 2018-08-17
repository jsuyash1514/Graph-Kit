package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.suyash.graphlibrary.BarGraph;
import com.example.suyash.graphlibrary.BarGraphDataPoint;

import java.util.ArrayList;

public class BarGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);


        BarGraph bG = findViewById(R.id.barGraph);
        ArrayList<BarGraphDataPoint> points = new ArrayList<>();
        points.add(new BarGraphDataPoint("2014",5, Color.parseColor("#34495E")));
        points.add(new BarGraphDataPoint("2015",9, Color.parseColor("#EC7063")));
        points.add(new BarGraphDataPoint("2016",2, Color.parseColor("#2ECC71")));
        points.add(new BarGraphDataPoint("2017",4, Color.parseColor("#F5B041")));
        bG.setPoints(points);
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
