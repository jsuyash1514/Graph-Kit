package com.mdgiitr.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static com.mdgiitr.suyash.graph.BarGraphNew.barGraphPtsNumber;

public class BarGraphLanding extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph_landing);

        Button createNewGraphButton = findViewById(R.id.createNewBarGraph);
        createNewGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barGraphPtsNumber = 0;
                Intent intent = new Intent(getApplicationContext(), BarGraphNew.class);
                finish();
                startActivity(intent);
            }
        });

        ImageButton barGraphBack = findViewById(R.id.barGraphBack);
        barGraphBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed(){
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
