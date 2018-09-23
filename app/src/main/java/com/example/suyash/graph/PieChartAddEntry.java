package com.example.suyash.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

public class PieChartAddEntry extends AppCompatActivity implements ColorPickerDialogListener{
    int selectedColorR, selectedColorG, selectedColorB, selectedColorRGB;
    ImageButton DarkBlue, LightBlue, Red, Yellow, Green, Grey, pieChartSelectedcolor, colorize, close;
    EditText name, percentage;
    Button add;
    String defaultName;
    Double defaultPercentage;
    int defaultColor;
    boolean n = false, p = false, isClickable = false;
    private final int DIALOG_ID = 0;
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
        ColorPickerDialog.newBuilder()
                .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                .setAllowPresets(false)
                .setDialogId(DIALOG_ID)
                .setColor(selectedColorRGB)
                .setShowAlphaSlider(true)
                .show(PieChartAddEntry.this);
    }


    @Override
    public void onColorSelected(int dialogid,int color){
        selectedColorRGB = color;
        pieChartSelectedcolor.setBackgroundColor(color);
    }
    @Override
    public void onDialogDismissed(int dialogid){}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PieChartNew.class);
        finish();
        startActivity(intent);

    }
}