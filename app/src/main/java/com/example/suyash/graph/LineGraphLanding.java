package com.example.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static com.example.suyash.graph.LineGraphNew.lineGraphPtsNumber;

public class LineGraphLanding extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph_landing);

        Button createNewGraphButton = findViewById(R.id.createNewLineGraph);
        createNewGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LineGraphNew.class);
                intent.putExtra("edit",false);
                lineGraphPtsNumber = 0;
                finish();
                startActivity(intent);
            }
        });

        ImageButton lineGraphBack = findViewById(R.id.lineGraphBack);
        lineGraphBack.setOnClickListener(new View.OnClickListener() {
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
