package com.example.suyash.graph;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by karthik on 9/21/18.
 */

public class LineGraphEntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ArrayList<LineGraphEntryModel> list;

    public LineGraphEntryAdapter(Context context, ArrayList<LineGraphEntryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_line_graph,parent,false);
        return new LineGraphHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        LineGraphEntryModel model = list.get(position);
        if (model!=null){
            ((LineGraphHolder)holder).x_dis.setText(Float.toString(model.getX()));
            ((LineGraphHolder)holder).y_dis.setText(Float.toString(model.getY()));
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

    class LineGraphHolder extends RecyclerView.ViewHolder {

        ImageButton delete;
        TextView x_dis, y_dis;
        Context context;

        public LineGraphHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();
            x_dis = itemView.findViewById(R.id.x_val);
            y_dis = itemView.findViewById(R.id.y_val);
            delete = itemView.findViewById(R.id.delete_pt);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}