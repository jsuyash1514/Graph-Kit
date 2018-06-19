package com.example.suyash.graphlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by karthik on 18/6/18.
 */

public class LineGraph extends View {

    private Bitmap mBitmap;
    private Paint mPaint,mBitmapPaint;
    private Canvas mCanvas;
    private float thickness = 8.0f;
    private int GRAPH_COLOR = Color.BLACK;

    private ArrayList<DataPoint> pointList,pointListScaled;

    float vW,vH;
    public LineGraph(Context context, float vW, float vH){
        super(context);

        this.vH = vH;
        this.vW = vW;

        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setColor(GRAPH_COLOR);
        mPaint.setStrokeWidth(thickness);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextSize(thickness*2);

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        pointList = new ArrayList<>();
        pointListScaled = new ArrayList<>();

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged((int)vW,(int)vH,oldw,oldh);
        mBitmap = Bitmap.createBitmap((int)vW,(int)vH, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.translate(0,vH);
        mCanvas.translate(50,-50);

    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,0,0,mBitmapPaint);
        drawGraph();
    }

    public void drawGraph(){

        mCanvas.drawColor(Color.WHITE);

        drawAxes();
        drawMarkings();
        mCanvas.scale(1,-1);
        drawLine();

    }

    private void drawAxes(){

        mCanvas.drawLine(0,0,0,-(vH-50),mPaint);
        mCanvas.drawLine(0,0,vW-50,0,mPaint);

    }

    public void setPoints(ArrayList<DataPoint> pointList){
        this.pointList = pointList;
    }

    private void drawLine(){

        if(pointListScaled.size() > 1) {
            for (int i = 0; i < pointListScaled.size() - 1; i++) {
                mCanvas.drawLine(pointListScaled.get(i).x, pointListScaled.get(i).y, pointListScaled.get(i + 1).x, pointListScaled.get(i + 1).y, mPaint);
            }
        }

    }

    private void drawMarkings(){

        float maxX = getMaxX();
        float maxY = getMaxY();

        float scaleX = maxX/(vW-50);
        float scaleY = maxY/(vH-50);

        for(int i = 0;i<pointList.size();i++){
            pointListScaled.add(new DataPoint(pointList.get(i).x/scaleX,pointList.get(i).y/scaleY));
        }

        for (int i = 100;i<vH-50;i+=100){

            mCanvas.drawLine(-5,-i,5,-i,mPaint);
            String mark = Math.round(scaleY*i)+"";
            Rect bounds = new Rect();
            mPaint.getTextBounds(mark,0,mark.length(),bounds);
            mCanvas.drawText(mark,-bounds.width()-15,-(i+mPaint.ascent()/2),mPaint);

        }
        for (int i = 100;i<vW-50;i+=100){
            mCanvas.drawLine(i,-5,i,5,mPaint);
            String mark = Math.round(scaleX*i)+"";
            Rect bounds = new Rect();
            mPaint.getTextBounds(mark,0,mark.length(),bounds);
            mCanvas.drawText(mark,i-bounds.width()/2,-2*(mPaint.ascent()),mPaint);
        }


    }

    private float getMaxX(){
        float maxX = pointList.get(0).x;
        for(int i = 0; i < pointList.size();i++){
            if(pointList.get(i).x > maxX){maxX = pointList.get(i).x;}
        }
        return maxX;
    }

    private float getMaxY(){
        float maxY = pointList.get(0).y;
        for(int i = 0; i < pointList.size();i++){
            if(pointList.get(i).y > maxY){maxY = pointList.get(i).y;}
        }
        return maxY;
    }

    public void setGraphColor(int color){
        GRAPH_COLOR = color;
    }

}
