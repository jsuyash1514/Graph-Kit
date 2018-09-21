package com.example.suyash.graph;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

public class PieChartAddEntry extends AppCompatActivity {
    int selectedColorR, selectedColorG, selectedColorB, selectedColorRGB;
    ImageButton DarkBlue, LightBlue, Red, Yellow, Green, Grey, pieChartSelectedcolor, colorize, close;
    EditText name, percentage;
    Button add;
    String defaultName;
    Double defaultPercentage;
    int defaultColor;
    boolean n = false, p = false, isClickable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_add_entry);

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
        close = findViewById(R.id.pieEntryCloseButton);

        add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
        selectedColorRGB = getResources().getColor(R.color.grey);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            defaultName = bundle.get("editName").toString();
            name.setText(defaultName);
            defaultPercentage = (Double) bundle.get("editPercentage");
            percentage.setText(defaultPercentage.toString());
            defaultColor = (int) bundle.get("editColor");
            selectedColorRGB = defaultColor;
            pieChartSelectedcolor.setBackgroundColor(selectedColorRGB);
            n = true;
            p = true;
            isClickable = true;
            add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle != null) {
                    String n = defaultName;
                    Double p = Double.valueOf(defaultPercentage);
                    int c = defaultColor;
                    Intent intent = new Intent(getApplicationContext(), PieChartNew.class);
                    intent.putExtra("name", n);
                    intent.putExtra("percentage", p);
                    intent.putExtra("color", c);
                    finish();
                    startActivity(intent);

                } else onBackPressed();
            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    n = false;
                    add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    isClickable = false;
                } else {
                    n = true;
                    if (n & p) {
                        add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        isClickable = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (String.valueOf(s).isEmpty() || String.valueOf(s) == null || String.valueOf(s) == "") {
                    n = false;
                    add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    isClickable = false;
                } else {
                    n = true;
                    if (n & p) {
                        add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        isClickable = true;
                    }
                }
            }
        });

        percentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    p = false;
                    add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    isClickable = false;
                } else {
                    p = true;
                    if (n & p) {
                        add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        isClickable = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (String.valueOf(s).isEmpty() || String.valueOf(s) == null || String.valueOf(s) == "") {
                    p = false;
                    add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    isClickable = false;
                } else {
                    p = true;
                    if (n & p) {
                        add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        isClickable = true;
                    }
                }
            }
        });


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

        colorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorizer();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClickable) {
                    String n = name.getText().toString();
                    Double p = Double.valueOf(percentage.getText().toString());
                    int c = selectedColorRGB;
                    Intent intent = new Intent(getApplicationContext(), PieChartNew.class);
                    intent.putExtra("name", n);
                    intent.putExtra("percentage", p);
                    intent.putExtra("color", c);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Invalid Name or Percentage!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void showColorizer() {
        final ColorPicker cp = new ColorPicker(this, Color.red(selectedColorRGB), Color.green(selectedColorRGB), Color.blue(selectedColorRGB));
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
                Log.d("Color", selectedColorR + "");
                Log.d("Color", selectedColorG + "");
                Log.d("Color", selectedColorB + "");
                Log.d("Color", selectedColorRGB + "");
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PieChartNew.class);
        finish();
        startActivity(intent);

    }
}
