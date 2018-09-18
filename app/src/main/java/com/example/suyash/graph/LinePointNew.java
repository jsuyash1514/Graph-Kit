package com.example.suyash.graph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class LinePointNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_point_new);

        ImageButton crossLinePoint = findViewById(R.id.crossLinePoint);
        crossLinePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, LineGraphNew.class));
        finish();
    }
}
