package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;

import com.example.suyash.graphlibrary.DataPoint;

import java.util.ArrayList;

public class BarGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);


        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_bar);

        com.example.suyash.graphlibrary.BarGraph barGraph= new com.example.suyash.graphlibrary.BarGraph(getApplicationContext(),1000,2000);
        ArrayList<DataPoint> points = new ArrayList<>();
        points.add(new DataPoint("2014",5,Color.parseColor("#34495E")));
        points.add(new DataPoint("2015",9,Color.parseColor("#EC7063")));
        points.add(new DataPoint("2016",2,Color.parseColor("#2ECC71")));
        points.add(new DataPoint("2017",4,Color.parseColor("#F5B041")));

        barGraph.setPoints(points);
        gridLayout.addView(barGraph);
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
