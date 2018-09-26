package com.mdgiitr.suyash.graphkit;

import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by karthik on 22/5/18.
 */

public class BezierSpline {

    public static void GetCurveControlPoints(ArrayList<PointF> knots, ArrayList<PointF> first_control_points, ArrayList<PointF> second_control_points) {

        if (knots == null) {
            throw new NullPointerException("Points cannot be null.");
        }
        int n = knots.size() - 1;
        if (n < 1) {
            throw new IllegalArgumentException("At least two knot Points required.");
        }

        if (n == 1) {
            //Special Case: Bezier curve is a straight line
            PointF p = new PointF();
            p.x = ((2 * knots.get(0).x) + (knots.get(1).x)) / 3;
            p.y = ((2 * knots.get(0).y) + (knots.get(1).y)) / 3;
            first_control_points.add(p);
            p = new PointF();
            p.x = (2 * first_control_points.get(0).x) - (knots.get(0).x);
            p.y = (2 * first_control_points.get(0).y) - (knots.get(0).y);
            second_control_points.add(p);
            return;
        }

        float[] rhs = new float[n];

        //set rhs x-values
        for (int i = 1; i < (n - 1); ++i) {
            rhs[i] = (4 * knots.get(i).x) + (2 * knots.get(i + 1).x);
            rhs[0] = knots.get(0).x + 2 * knots.get(1).x;
            rhs[n - 1] = ((8 * knots.get(n - 1).x) + (knots.get(n).x)) / 2.0f;
        }

        //get first control points x-values
        float[] x = GetFirstControlPoints(rhs);

        //set rhs y-values
        for (int i = 1; i < (n - 1); ++i) {
            rhs[i] = (4 * knots.get(i).y) + (2 * knots.get(i + 1).y);
            rhs[0] = (knots.get(0).y) + (2 * knots.get(1).y);
            rhs[n - 1] = ((8 * knots.get(n - 1).y) + (knots.get(n).y)) / 2.0f;
        }

        //get first control points y-values
        float[] y = GetFirstControlPoints(rhs);

        //fill output ArrayLists

        for (int i = 0; i < n; ++i) {
            PointF p = new PointF();
            p.x = x[i];
            p.y = y[i];
            first_control_points.add(p);

            if (i < (n - 1)) {
                p = new PointF();
                p.x = (2 * knots.get(i + 1).x) - (x[i + 1]);
                p.y = (2 * knots.get(i + 1).y) - (y[i + 1]);
                second_control_points.add(p);
            } else {
                p = new PointF();
                p.x = ((knots.get(n).x) + (x[n - 1])) / 2;
                p.y = ((knots.get(n).y) + (y[n - 1])) / 2;
                second_control_points.add(p);
            }
        }


    }

    //solves a tridiagonal system for one of the coordinates (x or y) of first Bezier control points
    private static float[] GetFirstControlPoints(float[] rhs) {
        int n = rhs.length;
        float[] x = new float[n];//solution vector
        float[] tmp = new float[n];//temporary workspace

        float b = 2.0f;
        x[0] = rhs[0] / b;
        for (int i = 1; i < n; i++) {//decomposition and forward substitution
            tmp[i] = (1 / b);
            b = (i < (n - 1) ? 4.0f : 3.5f) - tmp[i];
            x[i] = (rhs[i] - x[i - 1]) / b;
        }
        for (int i = 1; i < n; i++) {
            x[n - i - 1] -= tmp[n - i] * x[n - i];//backsubstitution
        }
        return x;
    }

}
