package com.example.suyash.graphlibrary;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suyash on 6/12/18.
 */

public class PieChart {
    List<DataPoint> dataPoints = new ArrayList<>();
    Bitmap bitmap;
    Canvas canvas,index;
    Paint paint;

    public PieChart(){
        bitmap = Bitmap.createBitmap(1000,2000,Bitmap.Config.ARGB_8888);
        paint = new Paint();

    }
    public void addDataPoint(String catgory, float percentage, int color){
        DataPoint dataPoint = new DataPoint();
        dataPoint.set(catgory,percentage,color);
        dataPoints.add(dataPoint);
    }

    private Bitmap drawPieChart(){
        Bitmap bitmap = Bitmap.createBitmap(1000,1000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        paint.setTextSize(60);
        canvas.translate(canvas.getWidth()/2,canvas.getHeight()/2);
        RectF oval = new RectF();
        oval.set(-canvas.getWidth()/3,-canvas.getHeight()/3,canvas.getWidth()/3,canvas.getHeight()/3);
        float startAngle = 0;
        for (int i=0;i<dataPoints.size();i++){
            paint.setColor(dataPoints.get(i).getColor());
            float sweepAngle = (dataPoints.get(i).getPercentage()*360)/100;
            canvas.drawArc(oval,startAngle,sweepAngle,true,paint);
            startAngle += sweepAngle;
        }

        if (startAngle<360){
            DataPoint dataPoint = new DataPoint();
            dataPoint.set("Other",((360-startAngle)*100)/360,Color.parseColor("#99A3A4"));
            dataPoints.add(dataPoint);
            paint.setColor(Color.parseColor("#99A3A4"));
            canvas.drawArc(oval,startAngle,360-startAngle,true,paint);
        }
        return bitmap;
    }
    private Bitmap drawIndex(){
        Bitmap bitmap = Bitmap.createBitmap(1000,1000, Bitmap.Config.ARGB_8888);
        index = new Canvas(bitmap);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        paint.setTextSize(60);

        for (int i=0;i<dataPoints.size();i++){
            paint.setColor(dataPoints.get(i).getColor());
            index.drawCircle(40,100+(i*100),40,paint);
            paint.setColor(Color.BLACK);
            index.drawText(dataPoints.get(i).getCatagory() + " : " + dataPoints.get(i).getPercentage() + "% ",100,120+(i*100),paint);
        }
        return bitmap;

    }

    public Bitmap plot(){
       Bitmap bitmap1 = drawPieChart();
       Bitmap bitmap2 = drawIndex();
       Canvas combine = new Canvas(bitmap);
       combine.drawBitmap(bitmap1,0,0,paint);
       combine.drawBitmap(bitmap2,0,canvas.getHeight(),paint);
       return  bitmap;

    }
}
