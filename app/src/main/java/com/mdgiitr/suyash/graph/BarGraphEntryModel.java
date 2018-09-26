package com.mdgiitr.suyash.graph;


/**
 * Created by karthik on 9/21/18.
 */

public class BarGraphEntryModel {


    String name;

    float data;

    int color;

    public BarGraphEntryModel(String name, float data, int color) {
        this.name = name;
        this.data = data;
        this.color = color;
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