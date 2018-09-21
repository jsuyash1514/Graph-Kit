package com.example.suyash.graph;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by suyash on 9/21/18.
 */
@Database(entities = {PieChartEntryModel.class}, version = 1)
public abstract class PieChartEntryDatabase extends RoomDatabase {
    public abstract PieChartEntryModelDao pieChartEntryModelDao();
}
