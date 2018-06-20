package com.example.suyash.graphlibrary;

/**
 * Created by suyash on 6/11/18.
 */

public class DataPoint {
    float x,y;
    String category;
    float percentage;
    int color;

    public DataPoint(float x, float y){
        this.x = x;
        this.y = y;
    }
    public DataPoint(){}


    public DataPoint(String category, float percentage, int color){
        this.color = color;
        this.percentage = percentage;
        this.category = category;
    }
}
