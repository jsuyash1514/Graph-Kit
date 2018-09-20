package com.example.suyash.graph;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

public class PieChartAddEntry extends AppCompatActivity {
    int selectedColorR, selectedColorG, selectedColorB, selectedColorRGB;
    ImageButton DarkBlue, LightBlue, Red, Yellow, Green, Grey, pieChartSelectedcolor, colorize;
    EditText name,percentage;
    Button add;
    boolean n = false,p=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_add_entry);

        ImageButton close = findViewById(R.id.pieEntryCloseButton);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        DarkBlue = findViewById(R.id.DarkBlue);
        LightBlue = findViewById(R.id.LightBlue);
        Red = findViewById(R.id.Red);
        Yellow = findViewById(R.id.yellow);
        Green = findViewById(R.id.green);
        Grey = findViewById(R.id.grey);
        pieChartSelectedcolor = findViewById(R.id.pieChartSelectedColor);
        colorize = findViewById(R.id.pieChartColorizer);
        name = findViewById(R.id.categoryInput);
        percentage = findViewById(R.id.percantageInput);
        add = findViewById(R.id.addPieChartEntry);

        add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
        add.setClickable(false);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    n = false;
                    add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    add.setClickable(false);
                }
                else {
                    n = true;
                    if(n&p){
                        add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        add.setClickable(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(String.valueOf(s).isEmpty() || String.valueOf(s)==null || String.valueOf(s)==""){
                    n=false;
                    add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    add.setClickable(false);
                }
                else {
                    n = true;
                    if (n & p) {
                        add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        add.setClickable(true);
                    }
                }
            }
        });

        percentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    p = false;
                    add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    add.setClickable(false);
                }
                else {
                    p = true;
                    if(n&p){
                        add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        add.setClickable(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(String.valueOf(s).isEmpty() || String.valueOf(s)==null || String.valueOf(s)==""){
                    p=false;
                    add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    add.setClickable(false);
                }
                else {
                    p = true;
                    if (n & p) {
                        add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        add.setClickable(true);
                    }
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                Double p = Double.valueOf(percentage.getText().toString());
                int c = selectedColorRGB;
                Intent intent = new Intent(getApplicationContext(),PieChartNew.class);
                intent.putExtra("name",n);
                intent.putExtra("percentage",p);
                intent.putExtra("color",c);
                startActivity(intent);
                finish();
            }
        });



        selectedColorRGB = getResources().getColor(R.color.grey);

        DarkBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColorRGB = getResources().getColor(R.color.darkBlue);
                pieChartSelectedcolor.setBackgroundColor(selectedColorRGB);
            }
        });

        LightBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColorRGB = getResources().getColor(R.color.lightBlue);
                pieChartSelectedcolor.setBackgroundColor(selectedColorRGB);
            }
        });

        Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColorRGB = getResources().getColor(R.color.red);
                pieChartSelectedcolor.setBackgroundColor(selectedColorRGB);
            }
        });

        Yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColorRGB = getResources().getColor(R.color.yellow);
                pieChartSelectedcolor.setBackgroundColor(selectedColorRGB);
            }
        });

        Green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColorRGB = getResources().getColor(R.color.green);
                pieChartSelectedcolor.setBackgroundColor(selectedColorRGB);
            }
        });

        Grey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColorRGB = getResources().getColor(R.color.grey);
                pieChartSelectedcolor.setBackgroundColor(selectedColorRGB);
            }
        });

        final Context context = this;

        final ColorPicker cp = new ColorPicker(this);

        colorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp.show();
                Button okColor = (Button) cp.findViewById(R.id.okColorButton);

                okColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedColorR = cp.getRed();
                        selectedColorG = cp.getGreen();
                        selectedColorB = cp.getBlue();
                        selectedColorRGB = cp.getColor();
                        pieChartSelectedcolor.setBackgroundColor(selectedColorRGB);
                        cp.dismiss();
                    }
                });
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PieChartNew.class));
        finish();
    }
}
