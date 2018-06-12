package com.example.suyash.graphlibrary;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by suyash on 6/12/18.
 */

public class BarGraph {
    List<DataPoint> dataPoints = new ArrayList<>();
    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    int thickness, scaleY;
    public BarGraph(){
        bitmap = Bitmap.createBitmap(2000,2400, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setTextSize(80);

        canvas.drawColor(Color.WHITE);
    }

    public void addDataPoints(String string, float value, int color){
        DataPoint dataPoint = new DataPoint();
        dataPoint.set(string,value,color);
        dataPoints.add(dataPoint);
    }

    private void setAxes(){
        canvas.drawLine(canvas.getWidth()/10,canvas.getHeight(),canvas.getWidth()/10,0,paint);
        canvas.drawLine(0,canvas.getHeight()*9/10, canvas.getWidth(), canvas.getHeight()*9/10,paint);
        canvas.translate(canvas.getWidth()/10,canvas.getHeight()*9/10);

        float max_y;
        max_y = -Integer.MAX_VALUE;
        for (int i=0; i<dataPoints.size(); i++){
            if (Math.abs(dataPoints.get(i).getPercentage()) >= max_y){
                max_y = dataPoints.get(i).getPercentage();
            }
        }
        paint.setStrokeWidth(5);


        scaleY = String.valueOf(max_y).length()-2;
        thickness = (canvas.getWidth()*9/10)/dataPoints.size();
        for (int i =0; i<dataPoints.size(); i++){
            canvas.drawLine((i*thickness + thickness/2), -40,(i*thickness + thickness/2),0,paint);
            if (i%2==0){
                canvas.drawText(dataPoints.get(i).getCatagory(),(i*thickness)+thickness/4,70,paint);
            }
            else {
                canvas.drawText(dataPoints.get(i).getCatagory(),(i*thickness)+thickness/4,140,paint);

            }
        }
        for (int i=0;i<10;i++){
            canvas.drawLine(0,-i*200,40,-i*200,paint);
            canvas.drawText(Integer.toString((int)(i*Math.pow(10,scaleY))/10),-80*scaleY, (-i*200)+(15*scaleY),paint);
        }
        scaleY = (int)Math.pow(10,(3-scaleY));

    }

    public Bitmap plot(){
        setAxes();
        int start = 0;
        for (int i=0;i<dataPoints.size();i++){
            paint.setColor(dataPoints.get(i).getColor());
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRect(start,-(dataPoints.get(i).getPercentage()*scaleY*2),start+thickness,0,paint);
            paint.setColor(Color.BLACK);
            canvas.drawText(dataPoints.get(i).getPercentage()+"",start+(thickness/4),-(dataPoints.get(i).getPercentage()*scaleY*2)-50,paint);
            start+=thickness;
        }
        return bitmap;
    }
}
