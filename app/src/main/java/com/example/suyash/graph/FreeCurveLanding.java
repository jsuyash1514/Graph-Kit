package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suyash.graphlibrary.EditGraphView;

import java.text.DecimalFormat;

public class FreeCurveLanding extends AppCompatActivity {

    float prog = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_curve_landing);

        final DecimalFormat three_round_format = new DecimalFormat("#0.000");

        final EditGraphView editGraphView = findViewById(R.id.editgraphview);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#305c99"), PorterDuff.Mode.SRC_IN);
        seekBar.getThumb().setColorFilter(Color.parseColor("#305c99"), PorterDuff.Mode.SRC_IN);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setProgress((float) i / 1000.0f);
                ((TextView) findViewById(R.id.x_value_label)).setText("X : " + prog);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button showYValButton = findViewById(R.id.getYVal);
        showYValButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Y-Value : " + Float.valueOf(three_round_format.format(editGraphView.getYFromX(getProgress()))), Toast.LENGTH_SHORT).show();
            }
        });

        Button resetButton = findViewById(R.id.resetGraph);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editGraphView.resetGraph();
            }
        });
    }

    private float getProgress() {
        return prog;
    }

    private void setProgress(float p) {
        prog = p;
    }

    @Override
    public void onBackPressed(){
        finish();
        startActivity(new Intent(this, MainActivity.class));

    }
}
