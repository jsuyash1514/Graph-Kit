package com.example.suyash.graph;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.suyash.graphlibrary.BarGraph;
import com.example.suyash.graphlibrary.BarGraphDataPoint;

import java.util.ArrayList;

import static com.example.suyash.graph.BarGraphNew.barGraphPts;
import static com.example.suyash.graph.BarGraphNew.bar_graph_name;

public class BarGraphDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph_display);

        ((TextView) findViewById(R.id.bar_graph_name)).setText(bar_graph_name);

        BarGraph barGraph = findViewById(R.id.barGraph);
        ArrayList<BarGraphDataPoint> points = new ArrayList<>();
        for (BarGraphEntryModel entry : barGraphPts) {

            points.add(new BarGraphDataPoint(entry.getName(), entry.getData(), entry.color));

        }

        barGraph.setPoints(points);
    }
}
