package com.example.suyash.graph;

import android.arch.persistence.room.Room;
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
import java.util.List;

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

        PieChartEntryDatabase db = Room.databaseBuilder(getApplicationContext(),PieChartEntryDatabase.class,"pieChartEntries")
                .allowMainThreadQueries()
                .build();
        List<PieChartEntryModel> pieChartEntries = db.pieChartEntryModelDao().getAllPieChartEntries();
        for (int i=0;i<pieChartEntries.size();i++){
            points.add(new DataPoint(pieChartEntries.get(i).getName(),(float)pieChartEntries.get(i).getPercentage(),pieChartEntries.get(i).getColor()));
        }
        pieChart.setPoints(points);
        db.close();
    }

    public void onBackPressed(){
        Intent intent = new Intent(this,PieChartNew.class);
        finish();
        startActivity(intent);
    }
}
