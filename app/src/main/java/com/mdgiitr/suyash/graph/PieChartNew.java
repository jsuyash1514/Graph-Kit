package com.mdgiitr.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PieChartNew extends AppCompatActivity {
    static List<PieChartEntryModel> pieChartEntries;
    static String title;
    RecyclerView recyclerView;
    PieChartEntryAdapter pieChartEntryAdapter;
    EditText pieChartName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_new);

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            if (bundle1.get("new") != null) {
                pieChartEntries = new ArrayList<>();
                title = "";
            }
        }

        Button addEntry = findViewById(R.id.pieChartAddEntryButton);
        ImageButton close = findViewById(R.id.pieChartNewClose);
        ImageButton done = findViewById(R.id.pieChartNewDone);
        TextView noEnteries = findViewById(R.id.pieChartNoEntries);
        recyclerView = findViewById(R.id.pieChartEntriesListRecyclerView);
        pieChartEntryAdapter = new PieChartEntryAdapter(this, pieChartEntries);
        pieChartName = findViewById(R.id.pieChartTitleEditText);


        pieChartName.setText(title);
        pieChartName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title = pieChartName.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                title = pieChartName.getText().toString();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.get("name") != null) {
                PieChartEntryModel pieChartEntryModel = new PieChartEntryModel(bundle.get("name").toString(), (Double) bundle.get("percentage"), (int) bundle.get("color"));
                pieChartEntries.add(pieChartEntryModel);
                pieChartEntryAdapter.notifyDataSetChanged();
            }
        }


        RecyclerView.LayoutManager recycler = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recycler);
        recyclerView.setAdapter(pieChartEntryAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(pieChartEntryAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);


        if (pieChartEntries.size() == 0) {
            noEnteries.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noEnteries.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }


        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pieChartName.getText().toString().length() != 0) {
                    Intent intent = new Intent(getApplicationContext(), PieChartAddEntry.class);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Enter a chart title.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pieChartName.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Enter a chart title.", Toast.LENGTH_SHORT).show();
                } else if (pieChartEntries.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Add atleast one entry", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                    startActivity(new Intent(getApplicationContext(), PieChartResult.class));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PieChartLanding.class);
        finish();
        startActivity(intent);
    }
}