package com.example.suyash.graph;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;


public class LineGraph extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);

        com.example.suyash.graphlibrary.LineGraph lineGraph = new com.example.suyash.graphlibrary.LineGraph(getApplicationContext(),1000,1000);
        lineGraph.addDataPoint(1,1);
        lineGraph.addDataPoint(2,7);
        lineGraph.addDataPoint(4,3);
        lineGraph.addDataPoint(5,3);
        lineGraph.addDataPoint(7,9);
        lineGraph.setGraphColor(Color.BLACK);
        lineGraph.setBackgroundColor(Color.WHITE);

        gridLayout.addView(lineGraph);
    }
}


