package com.example.suyash.graphlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suyash on 6/12/18.
 */

public class BarGraph extends View{
    List<DataPoint> dataPoints = new ArrayList<>();
    Bitmap mBitmap;
    Canvas canvas;
    Paint paint, mBitmapPaint;
    int thickness, scaleY;
    int flg =0;
    public BarGraph(Context context, float vW, float vH){
        super(context);
        mBitmap = Bitmap.createBitmap((int)vW,(int)vH, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(mBitmap);
        paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setTextSize(80);

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        canvas.drawColor(Color.WHITE);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if (flg == 0) {
            drawGraph();
            flg = 1;
        }
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
    }

    public void setPoints(ArrayList<DataPoint> pointList) {
        flg = 0;
        this.dataPoints = pointList;
    }

    private void setAxes(){
        canvas.drawLine(canvas.getWidth()/10,canvas.getHeight(),canvas.getWidth()/10,0,paint);
        canvas.drawLine(0,canvas.getHeight()*9/10, canvas.getWidth(), canvas.getHeight()*9/10,paint);
        canvas.translate(canvas.getWidth()/10,canvas.getHeight()*9/10);

        float max_y;
        max_y = -Integer.MAX_VALUE;
        for (int i=0; i<dataPoints.size(); i++){
            if (Math.abs(dataPoints.get(i).percentage) >= max_y){
                max_y = dataPoints.get(i).percentage;
            }
        }
        paint.setStrokeWidth(5);


        scaleY = String.valueOf(max_y).length()-2;
        thickness = (canvas.getWidth()*9/10)/dataPoints.size();
        for (int i =0; i<dataPoints.size(); i++){
            canvas.drawLine((i*thickness + thickness/2), -40,(i*thickness + thickness/2),0,paint);
            if (i%2==0){
                canvas.drawText(dataPoints.get(i).category,(i*thickness)+thickness/4,70,paint);
            }
            else {
                canvas.drawText(dataPoints.get(i).category,(i*thickness)+thickness/4,140,paint);

            }
        }
        for (int i=0;i<10;i++){
            canvas.drawLine(0,-i*200,40,-i*200,paint);
            canvas.drawText(Integer.toString((int)(i*Math.pow(10,scaleY))/10),-80*scaleY, (-i*200)+(15*scaleY),paint);
        }
        scaleY = (int)Math.pow(10,(3-scaleY));

    }

    public void drawGraph(){
        setAxes();
        int start = 0;
        for (int i=0;i<dataPoints.size();i++){
            paint.setColor(dataPoints.get(i).color);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRect(start,-(dataPoints.get(i).percentage*scaleY*2),start+thickness,0,paint);
            paint.setColor(Color.BLACK);
            canvas.drawText(dataPoints.get(i).percentage+"",start+(thickness/4),-(dataPoints.get(i).percentage*scaleY*2)-50,paint);
            start+=thickness;
        }
    }
}
