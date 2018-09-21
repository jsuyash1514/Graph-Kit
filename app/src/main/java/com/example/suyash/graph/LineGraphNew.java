package com.example.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LineGraphNew extends AppCompatActivity {

    static ArrayList<LineGraphEntryModel> lineGraphPts;
    static int lineGraphPtsNumber = 0;
    static String line_graph_name = "";
    LineGraphEntryAdapter lineGraphEntryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_line_graph);
        if (lineGraphPtsNumber == 0) {
            lineGraphPts = new ArrayList<>();
        }

        lineGraphPtsNumber = lineGraphPts.size();

        ((EditText) findViewById(R.id.lineGraphTitleEditText)).setText(line_graph_name);
        Button addlinePoint = findViewById(R.id.addLinePoint);
        addlinePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText lineGraphNameEditText = findViewById(R.id.lineGraphTitleEditText);
                line_graph_name = lineGraphNameEditText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), LinePointNew.class);
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

        if (lineGraphPtsNumber == 0) {
            ((TextView) findViewById(R.id.no_pts_tag)).setVisibility(View.VISIBLE);
            ((RecyclerView) findViewById(R.id.lineGraphPtsList)).setVisibility(View.GONE);
        } else {
            ((TextView) findViewById(R.id.no_pts_tag)).setVisibility(View.GONE);

            RecyclerView linePtRecyclerView = findViewById(R.id.lineGraphPtsList);

            lineGraphEntryAdapter = new LineGraphEntryAdapter(getApplicationContext(), lineGraphPts);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            linePtRecyclerView.setLayoutManager(mLayoutManager);
            linePtRecyclerView.setItemAnimator(new DefaultItemAnimator());
            linePtRecyclerView.setAdapter(lineGraphEntryAdapter);
            linePtRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
            linePtRecyclerView.setVisibility(View.VISIBLE);

        }

        ImageButton tickMakeGraphButton = findViewById(R.id.okLineGraph);
        tickMakeGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showLineGraph = new Intent(getApplicationContext(), LineGraphDisplay.class);
                if (lineGraphPtsNumber >= 2) {
                    EditText lineGraphNameEditText = findViewById(R.id.lineGraphTitleEditText);
                    line_graph_name = lineGraphNameEditText.getText().toString();
                    if (!line_graph_name.isEmpty() && !line_graph_name.equals("")) {

                        startActivity(showLineGraph);
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter graph name!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Not enough points to plot!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LineGraphLanding.class));
        finish();
    }
}
