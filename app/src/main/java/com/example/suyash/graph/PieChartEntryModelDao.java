package com.example.suyash.graph;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by suyash on 9/21/18.
 */
@Dao
public interface PieChartEntryModelDao {

    @Query("SELECT * FROM pieChartEntryModel")
    List<PieChartEntryModel> getAllPieChartEntries();

    @Insert
    void insert(PieChartEntryModel... pieChartEntries);

    @Delete
    void delete(PieChartEntryModel... pieChartEntries);

}
