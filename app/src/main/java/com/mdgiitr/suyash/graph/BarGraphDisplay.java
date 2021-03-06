package com.mdgiitr.suyash.graph;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgiitr.suyash.graphkit.BarGraph;
import com.mdgiitr.suyash.graphkit.DataPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.mdgiitr.suyash.graph.BarGraphNew.barGraphPts;
import static com.mdgiitr.suyash.graph.BarGraphNew.bar_graph_name;

public class BarGraphDisplay extends AppCompatActivity {

    Bitmap saveBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph_display);

        ((TextView) findViewById(R.id.bar_graph_name)).setText(bar_graph_name);

        ImageButton backButton = findViewById(R.id.barGraphDisplayBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final BarGraph barGraph = findViewById(R.id.barGraph);
        ArrayList<DataPoint> points = new ArrayList<>();
        for (BarGraphEntryModel entry : barGraphPts) {

            points.add(new DataPoint(entry.getName(), entry.getData(), entry.color));

        }

        barGraph.setPoints(points);

        ImageButton saveImageButton = findViewById(R.id.saveImage);
        saveImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveBitmap = barGraph.getBitmap();
                    if (saveBitmap != null) {
                        saveImage();
                    } else {
                        Toast.makeText(getApplicationContext(), "Couldn't save image!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void saveImage() throws Exception {

        boolean perm = isWriteStoragePermissionGranted();

        if (perm) {
            FileOutputStream fOut = null;
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File file2 = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File file = File.createTempFile(imageFileName, ".jpeg", file2);

            try {
                fOut = new FileOutputStream(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            (saveBitmap).compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            try {
                fOut.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri cUri = Uri.fromFile(file);
            mediaScanIntent.setData(cUri);
            this.sendBroadcast(mediaScanIntent);
            Toast.makeText(getApplicationContext(), "Image Saved to Pictures", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getApplicationContext(), "Storage Permission required to save image!", Toast.LENGTH_SHORT).show();

        }


    }

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Permission: ", "Permission is granted2");
                return true;
            } else {

                Log.v("Permission: ", "Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Permission: ", "Permission is granted2");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                Log.d("Permission: ", "External storage2");
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v("Permission: ", "Permission: " + permissions[0] + "was " + grantResults[0]);
                    //resume tasks needing this permission
                    try {
                        saveImage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                }
                break;

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), BarGraphNew.class);
        finish();
        startActivity(intent);
    }
}
