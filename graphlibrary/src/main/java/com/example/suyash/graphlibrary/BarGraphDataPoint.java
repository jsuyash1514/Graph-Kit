package com.example.suyash.graphlibrary;

/**
 * Created by karthik on 20/7/18.
 */

public class BarGraphDataPoint {
    String x;
    float y;
    int color;

    public BarGraphDataPoint(String x, float y, int color){

        this.x = x;
        this.y = y;
        this.color = color;

    }

    public void addPoint(String x, float y,int color){

        this.x = x;
        this.y = y;
        this.color = color;

    }

    public float getY() {
        return y;
    }

    public String getX() {
        return x;
    }

    public int getColor() {
        return color;
    }
}
