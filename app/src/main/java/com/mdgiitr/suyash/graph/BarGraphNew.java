package com.mdgiitr.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BarGraphNew extends AppCompatActivity {

    static ArrayList<BarGraphEntryModel> barGraphPts;
    static int barGraphPtsNumber = 0;
    static String bar_graph_name = "";
    BarGraphEntryAdapter barGraphEntryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph_new);

        if (barGraphPtsNumber == 0) {
            barGraphPts = new ArrayList<>();
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getBoolean("edit")) {
            barGraphPts.add(new BarGraphEntryModel(bundle.getString("name"), bundle.getFloat("data"), bundle.getInt("color")));
        }
        if (bundle != null && bundle.getBoolean("new")) {
            bar_graph_name = "";
        }

        barGraphPtsNumber = barGraphPts.size();

        ((EditText) findViewById(R.id.lineGraphTitleEditText)).setText(bar_graph_name);

        Button addPointButton = findViewById(R.id.addBarPointEntry);
        addPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText barGraphNameEditText = findViewById(R.id.lineGraphTitleEditText);
                bar_graph_name = barGraphNameEditText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), BarPointNew.class);
                finish();
                startActivity(intent);
            }
        });

        ImageButton closeButton = findViewById(R.id.crossBarGraph);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ImageButton tickMakeGraphButton = findViewById(R.id.okLineGraph);
        tickMakeGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showBarGraph = new Intent(getApplicationContext(), BarGraphDisplay.class);
                if (barGraphPtsNumber >= 1) {
                    EditText lineGraphNameEditText = findViewById(R.id.lineGraphTitleEditText);
                    bar_graph_name = lineGraphNameEditText.getText().toString();
                    if (!bar_graph_name.isEmpty() && !bar_graph_name.equals("")) {
                        finish();
                        startActivity(showBarGraph);
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter graph name!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Enter at least one set of Data!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (barGraphPtsNumber == 0) {
            ((TextView) findViewById(R.id.no_pts_tag)).setVisibility(View.VISIBLE);
            ((RecyclerView) findViewById(R.id.barGraphPtsList)).setVisibility(View.GONE);
        } else {
            ((TextView) findViewById(R.id.no_pts_tag)).setVisibility(View.GONE);

            RecyclerView barPtRecyclerView = findViewById(R.id.barGraphPtsList);

            barGraphEntryAdapter = new BarGraphEntryAdapter(getApplicationContext(), barGraphPts);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            barPtRecyclerView.setLayoutManager(mLayoutManager);
            barPtRecyclerView.setItemAnimator(new DefaultItemAnimator());
            barPtRecyclerView.setAdapter(barGraphEntryAdapter);
            barPtRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(barGraphEntryAdapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(barPtRecyclerView);
            barPtRecyclerView.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, BarGraphLanding.class);
        finish();
        startActivity(intent);
    }
}
