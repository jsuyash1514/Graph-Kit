package com.mdgiitr.suyash.graphkit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by karthik on 18/6/18.
 */

public class LineGraph extends View {

    boolean pointSetflg = false, initflg = false;
    private Bitmap mBitmap;
    private Paint mPaint, mBitmapPaint;
    private Canvas mCanvas;
    private float thickness = 8.0f;
    private boolean SCROLLABLE_X = false, SCROLLABLE_Y = false;
    private ArrayList<DataPoint> pointList, pointListScaled;
    private float vW = 0, vH = 0, sW, sH, xDraw = 0, xStart = 0, xDrawStart = 0, yStart = 0, yDrawStart = 0, yDraw = 0, delta;
    private int color;
    private int GRID_COLOR = Color.LTGRAY;
    private int originShift = 50;
    private int topScaleMargin = originShift;
    private int rightScaleMargin = originShift;
    private int LABEL_SIZE = 20;
    private int MAX_DIV = 50;

    public LineGraph(Context context, AttributeSet attrs) {

        super(context, attrs);

        color = Color.BLACK;

        if (!SCROLLABLE_X) {
            sW = vW;
        }
        if (!SCROLLABLE_Y) {
            sH = vH;
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

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LineGraph, 0, 0);

        SCROLLABLE_X = typedArray.getBoolean(R.styleable.LineGraph_scrollablex, false);
        SCROLLABLE_Y = typedArray.getBoolean(R.styleable.LineGraph_scrollabley, false);
        color = typedArray.getColor(R.styleable.LineGraph_graph_color, Color.BLACK);
        GRID_COLOR = typedArray.getColor(R.styleable.LineGraph_grid_color, Color.LTGRAY);
        Log.d("color", color + "");
        thickness = typedArray.getFloat(R.styleable.LineGraph_line_thickness, 8.0f);
        LABEL_SIZE = typedArray.getInteger(R.styleable.LineGraph_label_text_size, 20);
        MAX_DIV = typedArray.getInteger(R.styleable.LineGraph_max_divisions,50);

    }

    public LineGraph(Context context, float vW, float vH) {
        super(context);

        color = Color.BLACK;

        this.vH = vH;
        this.vW = vW;
        if (!SCROLLABLE_X) {
            sW = vW;
        }
        if (!SCROLLABLE_Y) {
            sH = vH;
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

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (vH == 0 && vW == 0) {
            vW = this.getMeasuredWidth();
            vH = this.getMeasuredHeight();
            boolean widthMatchParent = (ViewGroup.LayoutParams.MATCH_PARENT == getLayoutParams().width || ViewGroup.LayoutParams.WRAP_CONTENT == getLayoutParams().width);
            if (!widthMatchParent) {
                vW = vW / 2;
            }
            boolean heightMatchParent = (ViewGroup.LayoutParams.MATCH_PARENT == getLayoutParams().height || ViewGroup.LayoutParams.WRAP_CONTENT == getLayoutParams().height);
            if (!heightMatchParent) {
                vH = vH / 2;
            }
            setScrollableX(SCROLLABLE_X);
            setScrollableY(SCROLLABLE_Y);
            if (!SCROLLABLE_X) {
                sW = vW;
            }
            if (!SCROLLABLE_Y) {
                sH = vH;
            }
            Log.d("vH = ", vH + "");
            Log.d("vW = ", vW + "");
        }

        if (pointSetflg)
            if (!initflg) {
                mBitmap = Bitmap.createBitmap((int) sW, (int) sH, Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mBitmap);
                mCanvas.translate(0, sH);


                String mark = Float.toString(getMaxY());
                mPaint.setTextSize(LABEL_SIZE);
                Rect bounds = new Rect();
                mPaint.getTextBounds(mark, 0, mark.length(), bounds);
                originShift = bounds.width() + 20;
                Log.d("TAG", originShift + "");

                mCanvas.translate(originShift, -originShift);
                drawGraph();
                yDraw = vH - sH;
                initflg = true;
            }
        Bitmap croppedBitmap = Bitmap.createBitmap(mBitmap, -(int) xDraw, -(int) yDraw, (int) vW, (int) vH);
        canvas.drawBitmap(croppedBitmap, 0, 0, mBitmapPaint);
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
        float x = event.getX();
        float y = event.getY();
        int c[] = new int[2];
        getLocationInWindow(c);
        boolean b = (x < c[0] + vW) && (y < c[1] + vH);

        if (SCROLLABLE_X && b) {

            x -= originShift;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    xStart = x;
                    xDrawStart = xDraw;
//                    invalidate();
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
//                    invalidate();
                    break;
            }
        }
        if (SCROLLABLE_Y && b) {

            y -= originShift;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    yStart = y;
                    yDrawStart = yDraw;
//                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:

                    delta = yStart - y;
                    yDraw = yDrawStart - delta;
                    if (yDraw > 0) {
                        yDraw = 0;
                    }
                    if (yDraw < -(sH - vH)) {
                        yDraw = -(sH - vH);
                    }
//                    invalidate();
                    break;

            }

        }
        invalidate();
        return true;
    }

    private void drawAxes() {

        mPaint.setStrokeWidth(thickness/2);
        mPaint.setColor(Color.BLACK);
        mCanvas.drawLine(0, 0, 0, -(sH - originShift), mPaint);
        mCanvas.drawLine(0, 0, sW - originShift, 0, mPaint);

    }

    public void setPoints(ArrayList<DataPoint> pointList) {
        pointSetflg = true;
        this.pointList = pointList;
    }

    private void drawLine() {
        mPaint.setColor(color);
        if (pointListScaled.size() > 1) {
            for (int i = 0; i < pointListScaled.size() - 1; i++) {
                mCanvas.drawLine(pointListScaled.get(i).x, pointListScaled.get(i).y, pointListScaled.get(i + 1).x, pointListScaled.get(i + 1).y, mPaint);
            }
        }

    }

    private void drawMarkings() {

        mPaint.setTextSize(LABEL_SIZE);

        float maxX = getMaxX();
        float maxY = getMaxY();

        topScaleMargin = rightScaleMargin = originShift - 10;

        float scaleX = maxX / (sW - originShift - rightScaleMargin);
        float scaleY = maxY / (sH - originShift - topScaleMargin);




        int nD1 = getNumberOfDigits(maxY);
        float v1;
        if (nD1 > 1 && maxY <= Math.pow(10, nD1 - 1)) {
            v1 = (float) Math.pow(10, nD1 - 2);
        } else if (nD1 > 1) {
            v1 = (float) Math.pow(10, nD1 - 1);
        } else {
            v1 = (float) Math.pow(10, 0);
        }

        float nY = v1/scaleY;
        float inc1 = nY;
        if(sH/nY > MAX_DIV){
            inc1 = (sH/nY)*inc1/(float)MAX_DIV;
        }

        float nD2 = getNumberOfDigits(maxX);
        float v2;
        if (nD2 > 1 && maxX <= Math.pow(10, nD2 - 1)) {
            v2 = (float) Math.pow(10, nD2 - 2);
        } else if (nD2 > 1) {
            v2 = (float) Math.pow(10, nD2 - 1);
        } else {
            v2 = (float) Math.pow(10, 0);
        }

        float nX = v2/scaleX;
        float inc2 = nX;
        float noXD = sW/nX;
        if(noXD > MAX_DIV){
            inc2 = (sW/nX)*inc2/(float)MAX_DIV;
            noXD = MAX_DIV;
        }


        float id = Math.min(v1/scaleY,v2/scaleX);
        float inc = Math.min(inc1,inc2);
        float scale = Math.max(scaleX,scaleY);

        for (int i = 0; i < pointList.size(); i++) {
            pointListScaled.add(new DataPoint(pointList.get(i).x / scaleX, pointList.get(i).y / scaleY));
        }

        boolean sizeChangeText = false;
        String mark1 = Float.toString(Math.round(scale * sW));
        Rect boundsSizeChange = new Rect();
        mPaint.getTextBounds(mark1, 0, mark1.length(), boundsSizeChange);
        if(boundsSizeChange.width()*noXD + 5 > sW){
            sizeChangeText = true;
        }

        for (float i = v1/scaleY; i < sH; i += (inc1)) {
            mPaint.setColor(GRID_COLOR);
            mPaint.setStrokeWidth(thickness / 2);
            mCanvas.drawLine(0, -i, sW, -i, mPaint);
            mPaint.setStrokeWidth(thickness);
            mPaint.setColor(Color.BLACK);
            mCanvas.drawLine(-5, -i, 5, -i, mPaint);
            String mark = Float.toString(Math.round(scaleY * i));
            Rect bounds = new Rect();
            mPaint.getTextBounds(mark, 0, mark.length(), bounds);
            mCanvas.drawText(mark, -bounds.width() - 15, -(i + mPaint.ascent() / 2), mPaint);

        }



        for (float i = v2/scaleX; i < sW; i += (inc2)) {
            mPaint.setTextSize(LABEL_SIZE);
            mPaint.setColor(GRID_COLOR);
            mPaint.setStrokeWidth(thickness / 2);
            mCanvas.drawLine(i, 0, i, -sH, mPaint);
            mPaint.setStrokeWidth(thickness);
            mPaint.setColor(Color.BLACK);
            mCanvas.drawLine(i, -5, i, 5, mPaint);
            String mark = Float.toString(Math.round(scaleX * i));
            Rect bounds = new Rect();
            mPaint.getTextBounds(mark, 0, mark.length(), bounds);
            if(sizeChangeText){
                mPaint.setTextSize(LABEL_SIZE - 10);
                mPaint.getTextBounds(mark, 0, mark.length(), bounds);
                mCanvas.drawText(mark, i - bounds.width() / 2, -2 * (mPaint.ascent())+mPaint.ascent()/2, mPaint);
            }
            else {
                mPaint.setTextSize(LABEL_SIZE);
                mCanvas.drawText(mark, i - bounds.width() / 2, -2 * (mPaint.ascent())+mPaint.ascent()/2, mPaint);
            }
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

    public void setScrollableX(boolean scrollable) {
        SCROLLABLE_X = scrollable;

        if (SCROLLABLE_X) {
            float x = getMaxX();
            int n = getNumberOfDigits(x);
            if (n <= 1) {
                SCROLLABLE_X = !SCROLLABLE_X;
            } else {
                x = Float.parseFloat(Float.toString(x).substring(0, 2));
                sW = x * 100;
            }
        }
    }

    public void setScrollableY(boolean scrollable) {
        SCROLLABLE_Y = scrollable;

        if (SCROLLABLE_Y) {
            float y = getMaxY();
            int n = getNumberOfDigits(y);
            if (n <= 1) {
                SCROLLABLE_Y = !SCROLLABLE_Y;
            } else {
                y = Float.parseFloat(Float.toString(y).substring(0, 2));
                sH = y * 100;
            }
        }
    }

    public void setGridColor(int c) {
        GRID_COLOR = c;
        invalidate();
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

    public void setLabelTextSize(int size) {

        LABEL_SIZE = size;

    }

    public void setMaxDivisions(int d){
        MAX_DIV = d;
    }

    public Bitmap getBitmap() {

        return mBitmap;
    }
}
