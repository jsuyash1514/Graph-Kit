package com.example.suyash.graph;



/**
 * Created by suyash on 9/20/18.
 */
public class PieChartEntryModel {


    String name;


    double percentage;


    int color;

    public PieChartEntryModel(String name, double percentage, int color) {
        this.name = name;
        this.percentage = percentage;
        this.color = color;
    }


    public String getName() {
        return name;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getColor() {
        return color;
    }
}
