package com.mdgiitr.suyash.graphkit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by karthik on 15/5/18.
 */

public class EditGraphView extends View {

    public static int RED = 0xFFFF0000;
    public static int BLUE = 0xFF0000FF;
    public static int GREEN = 0xFF00FF00;
    public static int BLACK = 0xFF000000;
    public static int YELLOW = 0xFFFFFF00;
    public static int ORANGE = 0xFFFFA500;
    public static int VIOLET = 0xFF8A2BE2;
    public static int GRID_SPACING = 100;
    public ArrayList<PointF> points_On_Graph, normalized_points;
    Context context;
    float vW, vH;
    int flg = 0;
    boolean touch_dot = false;
    ArrayList<PointF> edit_points, first_control_points, second_control_points;
    PointF current_point;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint, mPaint, dotPaint;
    private int thickness = 12;
    private int TOUCH_TOLERANCE = 20;
    private int color = BLACK;
    private PointF first_pt, last_pt;

    public EditGraphView(Context c, AttributeSet attrs) {

        super(c, attrs);
        context = c;
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(thickness);
        dotPaint = new Paint(Paint.DITHER_FLAG);
        dotPaint.setColor(color);
        dotPaint.setStrokeWidth(thickness + 4);
        dotPaint.setStyle(Paint.Style.FILL);

        first_control_points = new ArrayList<>();
        second_control_points = new ArrayList<>();
        points_On_Graph = new ArrayList<>();
        normalized_points = new ArrayList<>();

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EditGraphView, 0, 0);
        color = typedArray.getColor(R.styleable.EditGraphView_graph_color, Color.BLACK);
        lineColor(color);
        thickness = (int) typedArray.getFloat(R.styleable.EditGraphView_line_thickness, 12);
        lineThickness(thickness);
        TOUCH_TOLERANCE = typedArray.getInteger(R.styleable.EditGraphView_touch_tolerance, 20);
        setTouchTolerance(TOUCH_TOLERANCE);

    }

    public EditGraphView(Context c, float vgW, float vgH) {
        super(c);
        context = c;
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(thickness);
        dotPaint = new Paint(Paint.DITHER_FLAG);
        dotPaint.setColor(color);
        dotPaint.setStrokeWidth(thickness + 4);
        dotPaint.setStyle(Paint.Style.FILL);
        vW = vgW;
        vH = vgH;
        first_pt = new PointF();
        last_pt = new PointF();
        first_pt.x = 0;
        first_pt.y = vH;
        last_pt.x = vW - 100.0f;
        last_pt.y = 100.0f;
        edit_points = new ArrayList<>();
        edit_points.add(first_pt);
        edit_points.add(new PointF(vW / 3.0f, vH * 2.0f / 3.0f));
        edit_points.add(new PointF(vW * 2.0f / 3.0f, vH / 3.0f));
        edit_points.add(last_pt);

        first_control_points = new ArrayList<>();
        second_control_points = new ArrayList<>();
        points_On_Graph = new ArrayList<>();
        normalized_points = new ArrayList<>();

        mBitmap = Bitmap.createBitmap((int) vW, (int) vH, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.translate(50, -50);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if (vH == 0 && vW == 0) {

            vH = this.getMeasuredHeight();
            vW = this.getMeasuredWidth();

            boolean widthMatchParent = (ViewGroup.LayoutParams.MATCH_PARENT == getLayoutParams().width || ViewGroup.LayoutParams.WRAP_CONTENT == getLayoutParams().width);
            if (!widthMatchParent) {
                vW = vW / 2;
            }
            boolean heightMatchParent = (ViewGroup.LayoutParams.MATCH_PARENT == getLayoutParams().height || ViewGroup.LayoutParams.WRAP_CONTENT == getLayoutParams().height);
            if (!heightMatchParent) {
                vH = vH / 2;
            }

            mBitmap = Bitmap.createBitmap((int) vW, (int) vH, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            mCanvas.translate(50, -50);

            first_pt = new PointF();
            last_pt = new PointF();

            first_pt.x = 0;
            first_pt.y = vH;
            last_pt.x = vW - 100.0f;
            last_pt.y = 100.0f;
            edit_points = new ArrayList<>();
            edit_points.add(first_pt);
            edit_points.add(new PointF(vW / 3.0f, vH * 2.0f / 3.0f));
            edit_points.add(new PointF(vW * 2.0f / 3.0f, vH / 3.0f));
            edit_points.add(last_pt);

            first_control_points = new ArrayList<>();
            second_control_points = new ArrayList<>();
            points_On_Graph = new ArrayList<>();
            normalized_points = new ArrayList<>();

            mPath = new Path();
        }

        mPaint.setStrokeWidth(thickness);
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        if (flg == 0) {

            mPath.moveTo(edit_points.get(0).x, edit_points.get(0).y);
            BezierSpline.GetCurveControlPoints(edit_points, first_control_points, second_control_points);//get control points to plot cubic bezier
            for (int i = 0; i < first_control_points.size(); i++) {
                mPath.cubicTo(first_control_points.get(i).x, first_control_points.get(i).y, second_control_points.get(i).x, second_control_points.get(i).y, edit_points.get(i + 1).x, edit_points.get(i + 1).y);
            }
            drawGraph();
            flg = 1;
            calcPoints();
        }
        super.onDraw(canvas);
    }

    public void resetGraph() {

//        first_pt = new PointF();
//        last_pt = new PointF();
//        first_pt.x = 0;
//        first_pt.y = vH;
//        last_pt.x = vW - 100.0f;
//        last_pt.y = 100.0f;
//        edit_points = new ArrayList<>();
//        edit_points.add(first_pt);
//        edit_points.add(new PointF(vW / 3.0f, vH * 2.0f / 3.0f));
//        edit_points.add(new PointF(vW * 2.0f / 3.0f, vH / 3.0f));
//        edit_points.add(last_pt);
        vH = 0;
        vW = 0;
        flg = 0;
        invalidate();
        Log.d("TAG", "reset called");

    }


    private void touch_start(float x, float y) {

        if (onPath(x, y)) {

            for (PointF check_point : edit_points) {
                if (Math.sqrt(Math.pow(check_point.x - x, 2.0) + Math.pow(check_point.y - y, 2.0)) <= TOUCH_TOLERANCE) {
                    touch_dot = true;
                    current_point = check_point;
                    drawGraph();
                }
            }
            if (touch_dot != true) {
                current_point = new PointF(x, y);
                edit_points.add(current_point);
                sortEditPoints(edit_points);
                first_control_points = new ArrayList<>();
                second_control_points = new ArrayList<>();
                BezierSpline.GetCurveControlPoints(edit_points, first_control_points, second_control_points);//get control points to plot cubic bezier
                mPath.reset();
                mPath.moveTo(edit_points.get(0).x, edit_points.get(0).y);

                for (int i = 0; i < first_control_points.size(); i++) {
                    mPath.cubicTo(first_control_points.get(i).x, first_control_points.get(i).y, second_control_points.get(i).x, second_control_points.get(i).y, edit_points.get(i + 1).x, edit_points.get(i + 1).y);
                }

                drawGraph();
                touch_dot = true;
                Log.d("edit_pt: ", current_point.toString());
            }

        }

    }

    private void touch_up() {
        if (touch_dot) {
            calcPoints();
        }
        touch_dot = false;
    }

    private void touch_move(float xc, float yc) {
        if (touch_dot) {

            sortEditPoints(edit_points);

            first_control_points = new ArrayList<>();
            second_control_points = new ArrayList<>();
            BezierSpline.GetCurveControlPoints(edit_points, first_control_points, second_control_points);//get control points to plot cubic bezier

            mPath.reset();

            if (current_point.x == 0 || current_point.x == (int) (vW - 100)) {
                current_point.y = yc;
            } else {
                current_point.x = xc;
                current_point.y = yc;
            }

            mPath.moveTo(edit_points.get(0).x, edit_points.get(0).y);
            for (int i = 0; i < first_control_points.size(); i++) {
                Log.d("fcp: " + i, first_control_points.get(i).x + "" + first_control_points.get(i).y);
                Log.d("scp: " + i, second_control_points.get(i).x + "" + second_control_points.get(i).y);
            }

            for (int i = 0; i < first_control_points.size(); i++) {
                mPath.cubicTo(first_control_points.get(i).x, first_control_points.get(i).y, second_control_points.get(i).x, second_control_points.get(i).y, edit_points.get(i + 1).x, edit_points.get(i + 1).y);

            }
        }
        drawGraph();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        x -= 50;
        y += 50;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (x <= vW - 100 && y <= vH && x >= 0 && y >= 100)
                    touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (x <= vW - 100 && y <= vH && x >= 0 && y >= 100)
                    touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                calcPoints();
                break;
        }

        return true;
    }

    public void lineThickness(int thickness) {
        this.thickness = thickness;
    }

    public void lineColor(int color) {
        this.color = color;
        mPaint.setColor(color);
        dotPaint.setColor(color);

    }

    private void calcPoints() {
        PathMeasure pathMeasure = new PathMeasure(mPath, false);
        float[] pos = new float[2];
        float[] tan = new float[2];
        points_On_Graph = new ArrayList<>();
        normalized_points = new ArrayList<>();
        for (int i = 0; i <= pathMeasure.getLength(); i++) {
            pathMeasure.getPosTan(i, pos, tan);
            points_On_Graph.add(new PointF(pos[0], pos[1]));
            normalized_points.add(new PointF(pos[0] / (vW - 100.0f), 1.0f - (pos[1] / vH)));

        }
    }

    private void sortEditPoints(ArrayList<PointF> edit_points) {
        Collections.sort(edit_points, new Comparator<PointF>() {//sort edit_points according to x coordinate
            @Override
            public int compare(PointF o1, PointF o2) {
                return Float.compare(o1.x, o2.x);
            }
        });
    }

    private void drawGraph() {

        mCanvas.drawColor(Color.WHITE);
        Paint aPaint = new Paint();
        aPaint.setDither(true);
        aPaint.setAntiAlias(true);
        aPaint.setColor(Color.BLACK);
        aPaint.setStrokeWidth(5);

        mCanvas.drawLine(0, 0, 0, vH, aPaint);
        mCanvas.drawLine(0, vH, vW, vH, aPaint);
        aPaint.setColor(Color.LTGRAY);
        for (int i = 0; i < (int) vW / GRID_SPACING; i++) {
            mCanvas.drawLine(GRID_SPACING * (i + 1), 0, GRID_SPACING * (i + 1), vH, aPaint);
        }
        for (int i = 0; i < (int) vH / GRID_SPACING; i++) {
            mCanvas.drawLine(0, vH - GRID_SPACING * (i + 1), vW, vH - GRID_SPACING * (i + 1), aPaint);
        }

        mCanvas.drawPath(mPath, mPaint);

        for (PointF editPt : edit_points) {
            mCanvas.drawCircle(editPt.x, editPt.y, 20, dotPaint);
        }

    }

    public ArrayList<PointF> getPointsOnGraph() {
        return normalized_points;
    }

    private boolean onPath(float x, float y) {

        for (int i = 0; i < points_On_Graph.size(); i++) {

            if ((x > points_On_Graph.get(i).x - TOUCH_TOLERANCE && x < points_On_Graph.get(i).x + TOUCH_TOLERANCE) && (y > points_On_Graph.get(i).y - TOUCH_TOLERANCE && y < points_On_Graph.get(i).y + TOUCH_TOLERANCE)) {

                return true;
            }
        }

        return false;
    }

    public float getYFromX(float x) {
        int s = normalized_points.size();
        if (s <= 0) {
            try {
                throw new Exception("no points yet!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (x < 0 || x > 1.0f) {
            try {
                throw new Exception("x-value needs to be between 0 and 1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int l = s - 1;

        if (x > normalized_points.get(l).x) {
            float slope = (normalized_points.get(l).y - normalized_points.get(l - 1).y) / (normalized_points.get(l).x - normalized_points.get(l - 1).x);
            float c = normalized_points.get(l).y - slope * normalized_points.get(l).x;
            float y = slope * x + c;
            return y;
        } else if (x < normalized_points.get(0).x) {
            float slope = (normalized_points.get(0).y - normalized_points.get(1).y) / (normalized_points.get(0).x - normalized_points.get(1).x);
            float c = normalized_points.get(0).y - slope * normalized_points.get(0).x;
            float y = slope * x + c;
            return y;
        }
        for (int a = 0; a < normalized_points.size() - 1; a++) {

            if (x > normalized_points.get(a).x && x < normalized_points.get(a + 1).x) {
                float y1 = (normalized_points.get(a).y + normalized_points.get(a + 1).y) / 2.0f;
                return y1;
            }

        }

        return 0;

    }

    public void setTouchTolerance(int t) {
        TOUCH_TOLERANCE = t;
    }

}




