package com.mdgiitr.suyash.graph;


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

import static com.mdgiitr.suyash.graph.BarGraphNew.barGraphPts;
import static com.mdgiitr.suyash.graph.BarGraphNew.barGraphPtsNumber;

public class BarPointNew extends AppCompatActivity implements ColorPickerDialogListener{
    int selectedColorR, selectedColorG, selectedColorB, selectedColorRGB;
    ImageButton DarkBlue, LightBlue, Red, Yellow, Green, Grey, pieChartSelectedcolor, colorize, close;
    EditText name, data;
    Button add;
    String defaultName;
    Float defaultData;
    int defaultColor;
    boolean n = false, p = false, isClickable = false;
    private static final int DIALOG_ID = 0;
    Bundle bundle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_point_new);

        DarkBlue = findViewById(R.id.DarkBlue);
        LightBlue = findViewById(R.id.LightBlue);
        Red = findViewById(R.id.Red);
        Yellow = findViewById(R.id.yellow);
        Green = findViewById(R.id.green);
        Grey = findViewById(R.id.grey);
        pieChartSelectedcolor = findViewById(R.id.pieChartSelectedColor);
        colorize = findViewById(R.id.pieChartColorizer);
        name = findViewById(R.id.nameInput);
        data = findViewById(R.id.dataInput);
        add = findViewById(R.id.addBarPointEntry);
        close = findViewById(R.id.crossBarPoint);

        add.setBackgroundColor(getResources().getColor(R.color.button_inactive));
        selectedColorRGB = getResources().getColor(R.color.grey);

        final Bundle bundle = getIntent().getExtras();
        bundle2 = bundle;
        if (bundle != null) {
            defaultName = bundle.get("editName").toString();
            name.setText(defaultName);
            defaultData = (Float) bundle.get("editData");
            data.setText(defaultData.toString());
            defaultColor = (int) bundle.get("editColor");
            selectedColorRGB = defaultColor;
            pieChartSelectedcolor.setBackgroundColor(selectedColorRGB);
            n = true;
            p = true;
            isClickable = true;
            add.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

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

        data.addTextChangedListener(new TextWatcher() {
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
                    Float p = Float.valueOf(data.getText().toString());
                    int c = selectedColorRGB;
                    barGraphPts.add(new BarGraphEntryModel(n, p, c));
                    barGraphPtsNumber++;
                    name.setText("");
                    data.setText("");
                    c = defaultColor;
                    name.requestFocus();
                    Toast.makeText(getApplicationContext(), "Point Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), BarGraphNew.class);
                    finish();
                    startActivity(intent);

                } else {
                    Toast.makeText(getBaseContext(), "Invalid Name!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 onBackPressed();
            }
        });

    }

    public void showColorizer() {
        ColorPickerDialog.newBuilder()
                .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                .setAllowPresets(false)
                .setColor(selectedColorRGB)
                .setDialogId(DIALOG_ID)
                .setShowAlphaSlider(true)
                .show(BarPointNew.this);
    }

    @Override
    public void onColorSelected(int dialogid,int color){
        selectedColorRGB = color;
        pieChartSelectedcolor.setBackgroundColor(selectedColorRGB);
    }
    @Override
    public void onDialogDismissed(int dialogid){}

    @Override
    public void onBackPressed() {
        if (bundle2 != null) {
            String n = name.getText().toString();
            Float d = Float.parseFloat(data.getText().toString());
            int c = selectedColorRGB;
            Intent intent = new Intent(getApplicationContext(), BarGraphNew.class);
            intent.putExtra("name", n);
            intent.putExtra("data", d);
            intent.putExtra("color", c);
            intent.putExtra("edit",true);
            finish();
            startActivity(intent);

        }
        else{
            Intent intent = new Intent(this, BarGraphNew.class);
            finish();
            startActivity(intent);
        }

    }
}