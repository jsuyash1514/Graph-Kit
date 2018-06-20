package com.example.suyash.graphlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suyash on 6/12/18.
 */

public class PieChart extends View{
    Paint mPaint, mBitmapPaint;

    ArrayList<DataPoint> dataPoints;


    float width,height;

    public PieChart(Context context, float width) {
        super(context);

        this.width = width;
        height = width*2;

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        mPaint.setTextSize(60);


        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        dataPoints = new ArrayList<>();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged((int) width, (int) height, oldw, oldh);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas ){
        super.onDraw(canvas);
        Bitmap bitmap1 = drawPieChart();
        Bitmap bitmap2 = drawIndex();
        canvas.drawBitmap(bitmap1,0,0,mBitmapPaint);
        canvas.drawBitmap(bitmap2,0,height/2,mBitmapPaint);
    }


    public void setPoints(ArrayList<DataPoint> pointList) {
        this.dataPoints = pointList;
    }

    private Bitmap drawPieChart(){
        Bitmap bitmap = Bitmap.createBitmap((int)width,(int)height/2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(canvas.getWidth()/2,canvas.getHeight()/2);
        RectF oval = new RectF();
        oval.set(-canvas.getWidth()/3,-canvas.getHeight()/3,canvas.getWidth()/3,canvas.getHeight()/3);
        float startAngle = 0;
        for (int i=0;i<dataPoints.size();i++){
            mPaint.setColor(dataPoints.get(i).color);
            float sweepAngle = (dataPoints.get(i).percentage*360)/100;
            canvas.drawArc(oval,startAngle,sweepAngle,true,mPaint);
            startAngle += sweepAngle;
        }

        if (startAngle<360){
            dataPoints.add(new DataPoint("Other",((360-startAngle)*100)/360,Color.parseColor("#99A3A4")));
            mPaint.setColor(Color.parseColor("#99A3A4"));
            canvas.drawArc(oval,startAngle,360-startAngle,true,mPaint);
        }
        return bitmap;
    }
    private Bitmap drawIndex(){
        Bitmap bitmap = Bitmap.createBitmap((int)width,(int)height/2, Bitmap.Config.ARGB_8888);
        Canvas index = new Canvas(bitmap);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(width/100);
        mPaint.setTextSize(width/20);

        for (int i=0;i<dataPoints.size();i++){
            mPaint.setColor(dataPoints.get(i).color);
            index.drawCircle(width/25,(width/10)+(i*(width/10)),width/25,mPaint);
            mPaint.setColor(Color.BLACK);
            index.drawText(dataPoints.get(i).category + " : " + dataPoints.get(i).percentage + "% ",width/10,(width/8)+(i*(width/10)),mPaint);
        }
        return bitmap;

    }

}
