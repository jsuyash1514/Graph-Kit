package com.mdgiitr.suyash.graphkit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by suyash on 6/12/18.
 */

public class PieChart extends View {
    Paint mPaint, mBitmapPaint;

    ArrayList<DataPoint> dataPoints;
    ArrayList<Float> quantityList;
    float width = 0, height = 0;
    float diameter;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int LABEL_SIZE = 40;

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PieChart, 0, 0);
        LABEL_SIZE = typedArray.getInteger(R.styleable.PieChart_label_text_size, 40);


        dataPoints = new ArrayList<>();
        quantityList = new ArrayList<>();

    }

    public PieChart(Context context, float width, float height) {
        super(context);

        this.width = width;
        this.height = height;


        dataPoints = new ArrayList<>();

    }

    public void setLabelTextSize(int LABEL_SIZE) {
        this.LABEL_SIZE = LABEL_SIZE;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged((int) width, (int) height, oldw, oldh);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (width == 0 && height == 0) {
            width = this.getMeasuredWidth();
            height = this.getMeasuredHeight();

            Log.d("TAG: width = ", "" + width);
            Log.d("TAG: height = ", "" + height);


            boolean widthMatchParent = (ViewGroup.LayoutParams.MATCH_PARENT == getLayoutParams().width || ViewGroup.LayoutParams.WRAP_CONTENT == getLayoutParams().width);
            if (!widthMatchParent) {
                width = width / 2;
            }
            boolean heightMatchParent = (ViewGroup.LayoutParams.MATCH_PARENT == getLayoutParams().height || ViewGroup.LayoutParams.WRAP_CONTENT == getLayoutParams().height);
            if (!heightMatchParent) {
                height = height / 2;
            }
        }

        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        mPaint.setTextSize(60);

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        mBitmap = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.WHITE);
        Bitmap bitmap1 = drawPieChart();
        Bitmap bitmap2 = drawIndex();
//        canvas.drawBitmap(bitmap1, 0, 0, mBitmapPaint);
//        canvas.drawBitmap(bitmap2, 0, height / 2, mBitmapPaint);
        mCanvas.drawBitmap(bitmap1, 0, 0, mBitmapPaint);
        mCanvas.drawBitmap(bitmap2, 0, height / 2, mBitmapPaint);
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
    }


    public void setPoints(ArrayList<DataPoint> pointList) {
        float sum = 0;
        for (int i = 0; i < pointList.size(); i++) {
            sum += pointList.get(i).getData();
        }
        for (int i = 0; i < pointList.size(); i++) {
            float per = (((pointList.get(i).getData()) / sum) * 100);
            DecimalFormat dec = new DecimalFormat("#0.00");
            this.dataPoints.add(new DataPoint(pointList.get(i).getName(), Float.valueOf(dec.format(per)), pointList.get(i).getColor()));
            this.quantityList.add(pointList.get(i).getData());
        }
        invalidate();
    }

    private Bitmap drawPieChart() {
        if (height >= (2 * width)) diameter = width;
        else diameter = height / 2;
        Bitmap bitmap = Bitmap.createBitmap((int) width, (int) height / 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        RectF oval = new RectF();
        oval.set(-28 * diameter / 75, -diameter / 3, 22 * diameter / 75, diameter / 3);
        float startAngle = 0;
        for (int i = 0; i < dataPoints.size(); i++) {
            mPaint.setColor(dataPoints.get(i).getColor());
            float sweepAngle = (dataPoints.get(i).getData() * 360) / 100;
            canvas.drawArc(oval, startAngle, sweepAngle, true, mPaint);
            startAngle += sweepAngle;
        }
        return bitmap;
    }

    private Bitmap drawIndex() {
        if (height >= (2 * width)) diameter = width;
        else diameter = height / 2;
        Bitmap bitmap = Bitmap.createBitmap((int) width, (int) height / 2, Bitmap.Config.ARGB_8888);
        Canvas index = new Canvas(bitmap);
        mCanvas.translate(diameter / 25, 0);
        index.drawColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(diameter / 100);
        mPaint.setTextSize(LABEL_SIZE);

        for (int i = 0; i < dataPoints.size(); i++) {
            mPaint.setColor(dataPoints.get(i).getColor());
            index.drawCircle(diameter / 25, (diameter / 10) + (i * (diameter / 10)), diameter / 25, mPaint);
            mPaint.setColor(Color.BLACK);
            index.drawText(dataPoints.get(i).getName() + " : " + quantityList.get(i) + " : " + dataPoints.get(i).getData() + "% ", diameter / 10, (diameter / 8) + (i * (diameter / 10)), mPaint);
        }
        return bitmap;

    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

}
