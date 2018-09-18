package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class LineGraphNew extends AppCompatActivity {

    static ArrayList<Point> lineGraphPts;
    static int lineGraphPtsNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_line_graph);
        lineGraphPts = new ArrayList<>();

        lineGraphPtsNumber = lineGraphPts.size();

        Button addlinePoint = findViewById(R.id.addLinePoint);
        addlinePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LinePointNew.class);
                startActivity(intent);
            }
        });
        ImageButton crossLineGraphPoint = findViewById(R.id.crossLineGraph);
        crossLineGraphPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
                
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, LineGraphLanding.class));
        finish();
    }
}
