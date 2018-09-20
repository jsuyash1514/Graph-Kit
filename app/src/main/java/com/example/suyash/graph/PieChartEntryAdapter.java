package com.example.suyash.graph;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by suyash on 9/20/18.
 */

public class PieChartEntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<PieChartEntryModel> list;

    public PieChartEntryAdapter(Context context, List<PieChartEntryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_piechart_new_entries,parent,false);
        return new PieChartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PieChartEntryModel model = list.get(position);
        if (model!=null){
            ((PieChartHolder)holder).name.setText(model.getName());
            ((PieChartHolder)holder).percentage.setText(Double.toString(model.getPercentage())+'%');
            ((PieChartHolder)holder).color.setBackgroundColor(model.getColor());
        }
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (list.size() == 0) {
                arr = 0;
            } else {
                arr = list.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    class PieChartHolder extends RecyclerView.ViewHolder{
        TextView name, percentage;
        ImageView color;

        public PieChartHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pieChartEntriesName);
            percentage = itemView.findViewById(R.id.pieChartEntriesPercentage);
            color = itemView.findViewById(R.id.pieChartEntriesColor);
        }
    }
}
