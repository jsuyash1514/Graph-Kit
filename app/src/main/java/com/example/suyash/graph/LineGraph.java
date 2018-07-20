package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;

import com.example.suyash.graphlibrary.DataPoint;

import java.util.ArrayList;


public class LineGraph extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);

//        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);

//        com.example.suyash.graphlibrary.LineGraph lineGraph = new com.example.suyash.graphlibrary.LineGraph(getApplicationContext(),700,700);
        com.example.suyash.graphlibrary.LineGraph lineGraph = findViewById(R.id.lineGraph);
        ArrayList<DataPoint> points = new ArrayList<>();
        points.add(new DataPoint(10,10));
        points.add(new DataPoint(100,50));
        points.add(new DataPoint(100,100));
        points.add(new DataPoint(150,200));
        lineGraph.setPoints(points);
        lineGraph.setGraphColor(Color.BLACK);
        lineGraph.setScrollable(true);
//        gridLayout.addView(lineGraph);
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}


