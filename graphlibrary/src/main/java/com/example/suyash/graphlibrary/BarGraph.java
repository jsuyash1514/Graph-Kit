package com.example.suyash.graphlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by karthik on 20/7/18.
 */

public class BarGraph extends View {

    ArrayList<BarGraphDataPoint> pointList;
    boolean pointSetflg = false, initflg = false;
    int numberOfFields = 0;
    int vH = 0, vW = 0;
    Bitmap mBitmap;
    Paint mPaint;
    Canvas mCanvas;
    public int MARKING_COLOR = Color.BLACK;
    public int thickness = 6;
    float maxY;
    private int barWidth = 0;
    private int originShift = 50;
    private float scaleY = 1;
    private int topScaleMargin = 10;
    private int space = 10;

    public BarGraph(Context context, AttributeSet attrs){
        super(context,attrs);
        if(!pointSetflg){pointList = new ArrayList<>();}

    }

    public BarGraph(Context context, int vW, int vH){
        super(context);
        if(!pointSetflg){pointList = new ArrayList<>();}
        this.vH = vH;
        this.vW = vW;
    }

    public void setPoints(ArrayList<BarGraphDataPoint> pointList){
        this.pointList = pointList;
        pointSetflg = true;
        numberOfFields = pointList.size();
        maxY = getMaxY();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){

        super.onDraw(canvas);

        if(vH == 0 && vW == 0){
            vW = this.getMeasuredWidth();
            vH = this.getMeasuredHeight();
            Log.d("vH = ",vH+"");
            Log.d("vW = ",vW+"");
        }
        if(pointSetflg)
        if(!initflg) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setColor(MARKING_COLOR);
            mBitmap = Bitmap.createBitmap(vW, vH, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            mCanvas.translate(0, vH);
            mCanvas.translate(originShift, -originShift);
            drawGraph();
            initflg = true;
        }

        canvas.drawBitmap(mBitmap,0,0,new Paint(Paint.DITHER_FLAG));

    }

    public void drawGraph() {

        mCanvas.drawColor(Color.WHITE);
        barWidth = (vW-originShift)/numberOfFields;

        mCanvas.scale(1, -1);
        drawBars();
        mCanvas.scale(1, -1);

        drawMarkings();
        drawAxes();

    }

    private void drawAxes() {

        mPaint.setStrokeWidth(thickness);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mCanvas.drawLine(0, 0, 0, -(vH - originShift), mPaint);
        mCanvas.drawLine(0, 0, vW - originShift, 0, mPaint);

    }

    private  void drawMarkings(){

        for(int i = barWidth,j = 0;i<=vW;i+=barWidth,j++){
            Rect bounds = new Rect();
            mPaint.getTextBounds(pointList.get(j).getX(), 0, pointList.get(j).getX().length(), bounds);
            mCanvas.drawText(pointList.get(j).getX(),i - bounds.width()/2 - barWidth/2,-2*(mPaint.ascent()),mPaint);
        }

        int nD = getNumberOfDigits(maxY);
        float v;
        if (nD > 1) {
            v = (float) Math.pow(10, nD - 2);
        } else {
            v = (float) Math.pow(10, 0);
        }

        for (float i = v / scaleY; i < vH; i += (v / scaleY)) {

            mPaint.setStrokeWidth(thickness/2);
            mPaint.setColor(MARKING_COLOR);
            mCanvas.drawLine(-5, -i, 5, -i, mPaint);
            String mark = Math.round(scaleY * i) + "";
            Rect bounds = new Rect();
            mPaint.getTextBounds(mark, 0, mark.length(), bounds);
            mCanvas.drawText(mark, -bounds.width() - 15 , -(i + mPaint.ascent() / 2), mPaint);

        }

    }

    public void drawBars(){

        scaleY = maxY / (vH - originShift - topScaleMargin);

        for(int i = 0,j=0;i<=vW-barWidth;i+=barWidth,j++){
            Rect rect = new Rect(i+space,(int)(pointList.get(j).getY()/scaleY),i+barWidth-space,0);
            Paint rPaint = new Paint();
            rPaint.setColor(pointList.get(j).getColor());
            rPaint.setStyle(Paint.Style.FILL);
            mCanvas.drawRect(rect,rPaint);
        }

    }

    private float getMaxY() {
        float maxY = pointList.get(0).y;
        for (int i = 0; i < pointList.size(); i++) {
            if (pointList.get(i).y > maxY) {
                maxY = pointList.get(i).y;
            }
        }
        return maxY;
    }

    private int getNumberOfDigits(float n) {
        int x = (int) n;
        int count = 0;
        while (x != 0) {
            x /= 10;
            count++;
        }
        return count;
    }

}