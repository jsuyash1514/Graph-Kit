package com.mdgiitr.suyash.graphkit;

/**
 * Created by suyash on 6/11/18.
 */

public class DataPoint {
    float x, y;
    String name;
    float data;
    int color;

    public DataPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public DataPoint(String name, float data, int color) {
        this.color = color;
        this.data = data;
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public float getData() {
        return data;
    }

    public int getColor() {
        return color;
    }
}
