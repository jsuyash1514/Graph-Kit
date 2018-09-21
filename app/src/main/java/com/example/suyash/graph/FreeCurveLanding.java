package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.suyash.graphlibrary.EditGraphView;

public class FreeCurveLanding extends AppCompatActivity {

    float prog = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_curve_landing);

        final EditGraphView editGraphView = findViewById(R.id.editgraphview);

        TypedValue typedValue = new TypedValue();

//        TypedArray a = getApplicationContext().obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorPrimary });
//        int colorPrimary = a.getColor(0, 0);
//        a.recycle();
        editGraphView.lineColor(Color.parseColor("#305c99"));
        editGraphView.lineThickness(10);
        editGraphView.setTouchTolerance(25);
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#305c99"), PorterDuff.Mode.SRC_IN);
        seekBar.getThumb().setColorFilter(Color.parseColor("#305c99"), PorterDuff.Mode.SRC_IN);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setProgress((float) i / 1000.0f);
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
                Toast.makeText(getApplicationContext(), "Y-Value : " + editGraphView.getYFromX(getProgress()), Toast.LENGTH_SHORT).show();
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
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
