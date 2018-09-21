package com.example.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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


        Button addLinePointEntry = findViewById(R.id.addLinePointEntry);
        addLinePointEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPoint();
            }
        });

    }

    private void getPoint() {

        if (!(((EditText) findViewById(R.id.xInput)).getText().toString()).isEmpty() && !(((EditText) findViewById(R.id.yInput)).getText().toString()).isEmpty()) {
            float x = Float.parseFloat((((EditText) findViewById(R.id.xInput)).getText().toString()));
            float y = Float.parseFloat((((EditText) findViewById(R.id.yInput)).getText().toString()));
            LineGraphNew.lineGraphPts.add(new LineGraphEntryModel(x, y));
            LineGraphNew.lineGraphPtsNumber++;
            ((EditText) findViewById(R.id.xInput)).setText("");
            ((EditText) findViewById(R.id.yInput)).setText("");
            ((EditText) findViewById(R.id.xInput)).requestFocus();
            Toast.makeText(getApplicationContext(), "Point Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Input!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LineGraphNew.class));
        finish();
    }
}
