package com.example.suyash.graph;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by suyash on 9/20/18.
 */
@Entity
public class PieChartEntryModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "percentage")
    double percentage;

    @ColumnInfo(name = "color")
    int color;

    public PieChartEntryModel(String name, double percentage, int color) {
        this.name = name;
        this.percentage = percentage;
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
