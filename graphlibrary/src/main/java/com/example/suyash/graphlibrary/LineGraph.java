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
    int scaleX, scaleY;
    int marginX = 50;
    int marginY = 50;

    public LineGraph(){
        bitmap = Bitmap.createBitmap(2000,2000,Bitmap.Config.ARGB_8888);
        paint = new Paint();
        canvas = new Canvas(bitmap);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setTextSize(60);

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

    private void setAxes(){
        canvas.drawLine(canvas.getWidth()/2,0,canvas.getWidth()/2,canvas.getHeight(),paint);
        canvas.drawLine(0,canvas.getHeight()/2,canvas.getWidth(),canvas.getHeight()/2,paint);
        canvas.translate(canvas.getHeight()/2, canvas.getWidth()/2);
//        canvas.drawText("0",0-marginX,0-marginY,paint);
//        canvas.drawText("0",0+marginX,0+marginY,paint);
        float max_x, max_y;
        max_x = -Integer.MAX_VALUE;
        max_y = -Integer.MAX_VALUE;
        for (int i=0; i<dataPoints.size(); i++){
            if (Math.abs(dataPoints.get(i).getX()) >= max_x){
                max_x = dataPoints.get(i).getX();
            }
            if (Math.abs(dataPoints.get(i).getY()) >= max_y){
                max_y = dataPoints.get(i).getY();
            }
        }
        paint.setStrokeWidth(5);

        Log.d("Library: ", "max_X= " + max_x);
        Log.d("Library: ", "max_y= " + max_y);
        scaleX = String.valueOf(max_x).length()-2;
        Log.d("Library: ", "scaleX= " + scaleX);
        scaleY = String.valueOf(max_y).length()-2;
        Log.d("Library: ", "scaleY= " + scaleY);
        for (int i=0;i<10;i++){
            canvas.drawLine(i*100, -marginY,i*100,0,paint);
            if (i%2==0){
                canvas.drawText(Integer.toString((int)(i*Math.pow(10,scaleX))/10),(i*100)-(15*scaleX),marginY,paint);
            }
            else {
                canvas.drawText(Integer.toString((int)(i*Math.pow(10,scaleX))/10),(i*100)-(15*scaleX),marginY*2,paint);

            }
            canvas.drawLine(0,-i*100,marginX,-i*100,paint);
            canvas.drawText(Integer.toString((int)(i*Math.pow(10,scaleY))/10),-marginX*scaleY, -i*100,paint);
        }
        scaleX = (int)Math.pow(10,(3-scaleX));
        Log.d("Library: ", "scaleX= " + scaleX);
        scaleY = (int)Math.pow(10,(3-scaleY));
        Log.d("Library: ", "scaleY= " + scaleY);

    }

    public Bitmap plot(){
        setAxes();
        canvas.scale(1,-1);
        if (dataPoints.size() != 0){
            Log.d("Library: ", "size: " + dataPoints.size());
            for (int i=0; i<dataPoints.size(); i++){
                canvas.drawPoint((dataPoints.get(i).getX())*scaleX, (dataPoints.get(i).getY())*scaleY, paint);
                Log.d("Library: ", "x: " + (dataPoints.get(i).getX()) + "  y: " + (dataPoints.get(i).getY()));
                if (i>0){
                    canvas.drawLine((dataPoints.get(i-1).getX())*scaleX,(dataPoints.get(i-1).getY())*scaleY,(dataPoints.get(i).getX())*scaleX, (dataPoints.get(i).getY())*scaleY,paint );
                }
            }
        }
        else {
            Log.d("Library: ", "dataPoints.size(): " + dataPoints.size());
        }
        return bitmap;
    }
}
