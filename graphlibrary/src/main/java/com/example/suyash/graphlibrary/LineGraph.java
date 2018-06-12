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
 * Created by suyash on 6/11/18.
 */

public class LineGraph extends View{
    List<DataPoint> dataPoints = new ArrayList<>();
    Paint paint;
    Canvas mCanvas;
    Bitmap mBitmap;

    int scaleX, scaleY;
    int marginX = 50;
    int marginY = 50;
    float vH,vW;

    int backGroundColor = Color.WHITE;
    int graphColor = Color.BLACK;

    public LineGraph(Context context, float vW, float vH) {
        super(context);

        this.vH = vH;
        this.vW = vW;

        paint = new Paint();
        paint.setColor(graphColor);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setTextSize(60);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged((int)vW,(int)vH,oldw,oldh);
        mBitmap = Bitmap.createBitmap((int)vW,(int)vH, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }


    public void addDataPoint(float x, float y){
        DataPoint dataPoint = new DataPoint();
        dataPoint.set(x,y);
        dataPoints.add(dataPoint);
    }

    public void setBackgroundColor(int color){
        backGroundColor = color;
    }

    public void setGraphColor(int color){
        graphColor = color;
    }


    @Override
    public void onDraw(Canvas canvas) {

        draw();

        canvas.drawBitmap(mBitmap,0,0,new Paint());
        super.onDraw(canvas);

    }

    public void draw(){

        mCanvas.drawColor(backGroundColor);

        mCanvas.drawLine(mCanvas.getWidth()/2,0,mCanvas.getWidth()/2,mCanvas.getHeight(),paint);
        mCanvas.drawLine(0,mCanvas.getHeight()/2,mCanvas.getWidth(),mCanvas.getHeight()/2,paint);
        mCanvas.translate(mCanvas.getHeight()/2, mCanvas.getWidth()/2);



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

        scaleX = String.valueOf(max_x).length()-2;
        scaleY = String.valueOf(max_y).length()-2;
        for (int i=-9;i<10;i++){
            mCanvas.drawLine(i*100, -marginY,i*100,0,paint);
            if (i%2==0){
                mCanvas.drawText(Integer.toString((int)(i*Math.pow(10,scaleX))/10),(i*100)-(15*scaleX),marginY,paint);
            }
            else {
                mCanvas.drawText(Integer.toString((int)(i*Math.pow(10,scaleX))/10),(i*100)-(15*scaleX),marginY*2,paint);

            }
            mCanvas.drawLine(0,-i*100,marginX,-i*100,paint);
            mCanvas.drawText(Integer.toString((int)(i*Math.pow(10,scaleY))/10),-marginX*scaleY, (-i*100)+(15*scaleY),paint);
        }
        scaleX = (int)Math.pow(10,(3-scaleX));
        scaleY = (int)Math.pow(10,(3-scaleY));

        mCanvas.scale(1,-1);
        if (dataPoints.size() != 0){
            for (int i=0; i<dataPoints.size(); i++){
                mCanvas.drawPoint((dataPoints.get(i).getX())*scaleX, (dataPoints.get(i).getY())*scaleY, paint);
                if (i>0){
                    mCanvas.drawLine((dataPoints.get(i-1).getX())*scaleX,(dataPoints.get(i-1).getY())*scaleY,(dataPoints.get(i).getX())*scaleX, (dataPoints.get(i).getY())*scaleY,paint );
                }
            }
        }

    }

}
