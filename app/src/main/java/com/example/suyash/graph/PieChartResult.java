package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.suyash.graphlibrary.DataPoint;
import com.example.suyash.graphlibrary.PieChart;

import java.util.ArrayList;

public class PieChartResult extends AppCompatActivity {
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_result);

        backButton = findViewById(R.id.pieChartResultClose);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        PieChart pieChart = findViewById(R.id.pie_chart);
        ArrayList<DataPoint> points = new ArrayList<>();
        points.add(new DataPoint("Football",(float)40.1, Color.parseColor("#34495E")));
        points.add(new DataPoint("Cricket", (float)30.9, Color.parseColor("#EC7063")));
        points.add(new DataPoint("Basketball", (float)15.8,Color.parseColor("#2ECC71")));
        points.add(new DataPoint("Voleyball",(float)12.4,Color.parseColor("#F5B041")));


        pieChart.setPoints(points);
    }

    public void onBackPressed(){
        Intent intent = new Intent(this,PieChartNew.class);
        finish();
        startActivity(intent);
    }
}
