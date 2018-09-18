package com.example.suyash.graph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PieChartNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_new);

        Button addEntry = findViewById(R.id.pieChartAddEntryButton);
        ImageButton close = findViewById(R.id.pieChartNewClose);
        ImageButton done = findViewById(R.id.pieChartNewDone);

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PieChartAddEntry.class));
                finish();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this,PieChartLanding.class));
        finish();
    }
}
