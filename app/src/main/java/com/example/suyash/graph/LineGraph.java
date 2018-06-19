package com.example.suyash.graph;

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

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);

        com.example.suyash.graphlibrary.LineGraph lineGraph = new com.example.suyash.graphlibrary.LineGraph(getApplicationContext(),700,700);
        ArrayList<DataPoint> points = new ArrayList<>();
        points.add(new DataPoint(1,1));
        points.add(new DataPoint(2,7));
        points.add(new DataPoint(4,3));
        points.add(new DataPoint(5,3));
        points.add(new DataPoint(7,9));
        lineGraph.setPoints(points);
        lineGraph.setGraphColor(Color.BLACK);

        gridLayout.addView(lineGraph);
    }
}


