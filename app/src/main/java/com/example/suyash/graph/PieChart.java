package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;

import com.example.suyash.graphlibrary.DataPoint;

import java.util.ArrayList;

public class PieChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

//        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_pie);



        com.example.suyash.graphlibrary.PieChart pieChart = findViewById(R.id.grid_pie);
        ArrayList<DataPoint> points = new ArrayList<>();
        points.add(new DataPoint("Football",(float)40.1,Color.parseColor("#34495E")));
        points.add(new DataPoint("Cricket", (float)30.9, Color.parseColor("#EC7063")));
        points.add(new DataPoint("Basketball", (float)15.8,Color.parseColor("#2ECC71")));
        points.add(new DataPoint("Voleyball",(float)12.4,Color.parseColor("#F5B041")));

        pieChart.setPoints(points);
//        gridLayout.addView(pieChart);
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

