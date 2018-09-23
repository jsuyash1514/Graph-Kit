package com.example.suyash.graph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.suyash.graphlibrary.DataPoint;
import com.example.suyash.graphlibrary.PieChart;

import static com.example.suyash.graph.PieChartNew.pieChartEntries;
import static com.example.suyash.graph.PieChartNew.title;

import java.util.ArrayList;



public class PieChartResult extends AppCompatActivity {
    ImageButton backButton;
    TextView chartTitle;

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

        chartTitle = findViewById(R.id.pieChartResultToolText);
        chartTitle.setText(title);

        PieChart pieChart = findViewById(R.id.pie_chart);
        ArrayList<DataPoint> points = new ArrayList<>();

        for (int i=0;i<pieChartEntries.size();i++){
            points.add(new DataPoint(pieChartEntries.get(i).getName(),(float)pieChartEntries.get(i).getPercentage(),pieChartEntries.get(i).getColor()));
        }
        pieChart.setPoints(points);
    }

    public void onBackPressed(){
        Intent intent = new Intent(this,PieChartNew.class);
        finish();
        startActivity(intent);
    }
}
