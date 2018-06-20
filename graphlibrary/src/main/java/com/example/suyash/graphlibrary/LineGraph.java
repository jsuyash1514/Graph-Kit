package com.example.suyash.graphlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by karthik on 18/6/18.
 */

public class LineGraph extends View {

    private Bitmap mBitmap;
    private Paint mPaint, mBitmapPaint;
    private Canvas mCanvas;
    private float thickness = 8.0f;
    private boolean SCROLLABLE = false;
    private ArrayList<DataPoint> pointList, pointListScaled;
    private float vW, vH, sW, xDraw = 0, xStart = 0, xDrawStart = 0, delta;
    private int flg = 0, color;

    public LineGraph(Context context, float vW, float vH) {
        super(context);

        color = Color.BLACK;

        this.vH = vH;
        this.vW = vW;
        if (!SCROLLABLE) {
            sW = vW;
        }
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(thickness);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextSize(thickness * 2);

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        pointList = new ArrayList<>();
        pointListScaled = new ArrayList<>();

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged((int) vW, (int) vH, oldw, oldh);
        mBitmap = Bitmap.createBitmap((int) sW, (int) vH, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.translate(0, vH);
        mCanvas.translate(50, -50);
        flg = 0;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (flg == 0) {
            drawGraph();
            flg = 1;
        }
        canvas.drawBitmap(mBitmap, xDraw, 0, mBitmapPaint);
    }

    public void drawGraph() {

        mCanvas.drawColor(Color.WHITE);

        drawAxes();
        drawMarkings();
        mCanvas.scale(1, -1);
        drawLine();
        mCanvas.scale(1, -1);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (SCROLLABLE) {
            float x = event.getX();
            x -= 50;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    xStart = x;
                    xDrawStart = xDraw;
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:

                    delta = xStart - x;
                    xDraw = xDrawStart - delta;
                    if (xDraw > 0) {
                        xDraw = 0;
                    }
                    if (xDraw < -(sW - vW)) {
                        xDraw = -(sW - vW);
                    }
                    invalidate();
                    break;
            }
        }
        return true;
    }

    private void drawAxes() {

        mCanvas.drawLine(0, 0, 0, -(vH - 50), mPaint);
        mCanvas.drawLine(0, 0, sW - 50, 0, mPaint);

    }

    public void setPoints(ArrayList<DataPoint> pointList) {
        flg = 0;
        this.pointList = pointList;
    }

    private void drawLine() {

        if (pointListScaled.size() > 1) {
            for (int i = 0; i < pointListScaled.size() - 1; i++) {
                mCanvas.drawLine(pointListScaled.get(i).x, pointListScaled.get(i).y, pointListScaled.get(i + 1).x, pointListScaled.get(i + 1).y, mPaint);
            }
        }

    }

    private void drawMarkings() {

        float maxX = getMaxX();
        float maxY = getMaxY();

        float scaleX = maxX / (sW - 50);
        float scaleY = maxY / (vH - 50);

        for (int i = 0; i < pointList.size(); i++) {
            pointListScaled.add(new DataPoint(pointList.get(i).x / scaleX, pointList.get(i).y / scaleY));
        }

        int nD = getNumberOfDigits(maxY);
        float v;
        if (nD > 1) {
            v = (float) Math.pow(10, nD - 2);
        } else {
            v = (float) Math.pow(10, 0);
        }

        for (float i = v / scaleY; i < vH; i += (v / scaleY)) {
            mPaint.setColor(Color.LTGRAY);
            mPaint.setStrokeWidth(thickness / 2);
            mCanvas.drawLine(0, -i, sW, -i, mPaint);
            mPaint.setStrokeWidth(thickness);
            mPaint.setColor(color);
            mCanvas.drawLine(-5, -i, 5, -i, mPaint);
            String mark = Math.round(scaleY * i) + "";
            Rect bounds = new Rect();
            mPaint.getTextBounds(mark, 0, mark.length(), bounds);
            mCanvas.drawText(mark, -bounds.width() - 15, -(i + mPaint.ascent() / 2), mPaint);

        }

        nD = getNumberOfDigits(maxX);
        if (nD > 1) {
            v = (float) Math.pow(10, nD - 2);
        } else {
            v = (float) Math.pow(10, 0);
        }

        for (float i = v / scaleX; i < sW; i += (v / scaleX)) {
            mPaint.setColor(Color.LTGRAY);
            mPaint.setStrokeWidth(thickness / 2);
            mCanvas.drawLine(i, 0, i, -vH, mPaint);
            mPaint.setStrokeWidth(thickness);
            mPaint.setColor(color);
            mCanvas.drawLine(i, -5, i, 5, mPaint);
            String mark = Math.round(scaleX * i) + "";
            Rect bounds = new Rect();
            mPaint.getTextBounds(mark, 0, mark.length(), bounds);
            mCanvas.drawText(mark, i - bounds.width() / 2, -2 * (mPaint.ascent()), mPaint);
        }


    }

    private float getMaxX() {
        float maxX = pointList.get(0).x;
        for (int i = 0; i < pointList.size(); i++) {
            if (pointList.get(i).x > maxX) {
                maxX = pointList.get(i).x;
            }
        }
        return maxX;
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

    public void setScrollable(boolean scrollable) {
        SCROLLABLE = scrollable;

        if (SCROLLABLE) {
            float x = getMaxX();
            int n = getNumberOfDigits(x);
            if (n <= 1) {
                SCROLLABLE = !SCROLLABLE;
            } else {
                x = Float.parseFloat(Float.toString(x).substring(0, 2));
                sW = x * 100;
            }
        }
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

    public void setGraphColor(int color) {
        this.color = color;
    }

    private ArrayList<DataPoint> sortPoints(ArrayList<DataPoint> points) {
        Collections.sort(points, new Comparator<DataPoint>() {
            @Override
            public int compare(DataPoint dataPoint, DataPoint t1) {
                return Float.toString(dataPoint.x).compareTo(Float.toString(t1.x));
            }
        });
        return points;
    }
}
