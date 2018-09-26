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
import android.widget.TextView;
import android.widget.Toast;


public class LinePointNew extends AppCompatActivity {

    boolean isClickable = false;
    boolean xf = false, yf = false;
    TextView xText, yText;
    Bundle bundle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_point_new);


        final Button addLinePointEntry = findViewById(R.id.addLinePointEntry);
        addLinePointEntry.setBackgroundColor(getResources().getColor(R.color.button_inactive));
        addLinePointEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPoint();
                Intent intent = new Intent(getApplicationContext(), LineGraphNew.class);
                finish();
                startActivity(intent);
            }
        });

        final EditText xInputEditText = findViewById(R.id.xInput);
        final EditText yInputEditText = findViewById(R.id.yInput);
        xText = xInputEditText;
        yText = yInputEditText;
        final Bundle bundle = getIntent().getExtras();
        bundle2 = bundle;
        if (bundle != null && bundle.getBoolean("edit")) {
            xInputEditText.setText(Float.toString(bundle.getFloat("x")));
            yInputEditText.setText(Float.toString(bundle.getFloat("y")));
            xf = yf = true;
            addLinePointEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            isClickable = true;
        }

        ImageButton crossLinePoint = findViewById(R.id.crossLinePoint);
        crossLinePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        xInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    xf = false;
                    addLinePointEntry.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    isClickable = false;
                } else {
                    xf = true;
                    if (xf && yf) {
                        addLinePointEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        isClickable = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (String.valueOf(s).isEmpty() || String.valueOf(s) == null || String.valueOf(s) == "") {
                    xf = false;
                    addLinePointEntry.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    isClickable = false;
                } else {
                    xf = true;
                    if (xf && yf) {
                        addLinePointEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        isClickable = true;
                    }
                }
            }
        });

        yInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    yf = false;
                    addLinePointEntry.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    isClickable = false;
                } else {
                    yf = true;
                    if (xf && yf) {
                        addLinePointEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        isClickable = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (String.valueOf(s).isEmpty() || String.valueOf(s) == null || String.valueOf(s) == "") {
                    yf = false;
                    addLinePointEntry.setBackgroundColor(getResources().getColor(R.color.button_inactive));
                    isClickable = false;
                } else {
                    yf = true;
                    if (xf && yf) {
                        addLinePointEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        isClickable = true;
                    }
                }
            }
        });


    }

    private void getPoint() {

        if (isClickable) {
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
        Intent intent = new Intent(getApplicationContext(), LineGraphNew.class);
        try {
            intent.putExtra("x", Float.parseFloat(xText.getText().toString()));
            intent.putExtra("y", Float.parseFloat(yText.getText().toString()));
            intent.putExtra("edit", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
        startActivity(intent);
    }
}
