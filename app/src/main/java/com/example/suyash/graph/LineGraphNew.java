package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class LineGraphNew extends AppCompatActivity {

    static ArrayList<LineGraphEntryModel> lineGraphPts;
    static int lineGraphPtsNumber = 0;
    LineGraphEntryAdapter lineGraphEntryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_line_graph);
        if(lineGraphPtsNumber == 0){
            lineGraphPts = new ArrayList<>();
        }

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

        if(lineGraphPtsNumber == 0){
            ((TextView)findViewById(R.id.no_pts_tag)).setVisibility(View.VISIBLE);
            ((RecyclerView)findViewById(R.id.lineGraphPtsList)).setVisibility(View.GONE);
        }else {
            ((TextView)findViewById(R.id.no_pts_tag)).setVisibility(View.GONE);

            RecyclerView linePtRecyclerView = findViewById(R.id.lineGraphPtsList);

            lineGraphEntryAdapter = new LineGraphEntryAdapter(getApplicationContext(),lineGraphPts);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            linePtRecyclerView.setLayoutManager(mLayoutManager);
            linePtRecyclerView.setItemAnimator(new DefaultItemAnimator());
            linePtRecyclerView.setAdapter(lineGraphEntryAdapter);

            linePtRecyclerView.setVisibility(View.VISIBLE);

        }

    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, LineGraphLanding.class));
        finish();
    }
}
