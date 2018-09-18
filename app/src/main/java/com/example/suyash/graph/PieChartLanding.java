package com.example.suyash.graph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PieChartLanding extends AppCompatActivity {
    ImageButton back;
    Button createNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_landing);

        back = findViewById(R.id.pieToolbar_back);
        createNew = findViewById(R.id.pie_createNewButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PieChartNew.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
