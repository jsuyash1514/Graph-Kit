package com.example.suyash.graphlibrary;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suyash on 6/11/18.
 */

public class LineGraph {
    List<DataPoint> dataPoints = new ArrayList<>();
    Bitmap bitmap;
    Paint paint;
    Canvas canvas;

    public LineGraph(){
        bitmap = Bitmap.createBitmap(1000,1000,Bitmap.Config.ARGB_8888);
        paint = new Paint();
        canvas = new Canvas(bitmap);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);

        canvas.drawColor(Color.WHITE);
    }

    public void addDataPoint(float x, float y){
        DataPoint dataPoint = new DataPoint();
        dataPoint.set(x,y);
        dataPoints.add(dataPoint);
    }

    public void setBackgroundColor(int color){
        canvas.drawColor(color);
    }

    public void setGraphColor(int color){
        paint.setColor(color);
    }

    public Bitmap plot(){
        canvas.drawLine(canvas.getWidth()/2,0,canvas.getWidth()/2,canvas.getHeight(),paint);
        canvas.drawLine(0,canvas.getHeight()/2,canvas.getWidth(),canvas.getHeight()/2,paint);
        paint.setStrokeWidth(5);
        canvas.translate(canvas.getHeight()/2, canvas.getWidth()/2);
        canvas.scale(1,-1);
        canvas.drawText("(0,0)",0,0,paint);
        if (dataPoints.size() != 0){
            Log.d("Library: ", "size: " + dataPoints.size());
            for (int i=0; i<dataPoints.size(); i++){
                canvas.drawPoint(dataPoints.get(i).getX(), dataPoints.get(i).getY(), paint);
                Log.d("Library: ", "x: " + dataPoints.get(i).getX() + "  y: " + dataPoints.get(i).getY());
                if (i>0){
                    canvas.drawLine(dataPoints.get(i-1).getX(),dataPoints.get(i-1).getY(),dataPoints.get(i).getX(), dataPoints.get(i).getY(),paint );
                }
            }
        }
        else {
            Log.d("Library: ", "dataPoints.size(): " + dataPoints.size());
        }
        return bitmap;
    }
}
