package com.example.suyash.graphlibrary;

/**
 * Created by suyash on 6/11/18.
 */

public class DataPoint {
    float x,y;
    String catagory;
    float percentage;
    int color;


    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void set(String catagory, float percentage, int color){
        this.catagory = catagory;
        this.percentage = percentage;
        this.color = color;
    }

    public String getCatagory() {
        return catagory;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getColor() {
        return color;
    }
}
