package com.example.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.suyash.graphlibrary.DataPoint;
import com.example.suyash.graphlibrary.LineGraph;

import java.util.ArrayList;

public class LineGraphDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph_display);

        ((TextView) findViewById(R.id.line_graph_name)).setText(LineGraphNew.line_graph_name);

        LineGraph lineGraph = findViewById(R.id.lineGraph);
        ArrayList<DataPoint> points = new ArrayList<>();

        for (LineGraphEntryModel entry : LineGraphNew.lineGraphPts) {

            points.add(new DataPoint(entry.getX(), entry.getY()));

        }

        lineGraph.setPoints(points);

        ImageButton backButton = findViewById(R.id.lineGraphDisplayBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LineGraphNew.class));
        finish();
    }
}
