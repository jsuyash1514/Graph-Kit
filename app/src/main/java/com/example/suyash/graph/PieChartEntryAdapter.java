package com.example.suyash.graph;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        PieChartEntryModel model = list.get(position);
        if (model!=null){
            ((PieChartHolder)holder).name.setText(model.getName());
            ((PieChartHolder)holder).percentage.setText(Double.toString(model.getPercentage())+'%');
            ((PieChartHolder)holder).color.setBackgroundColor(model.getColor());
//            ((PieChartHolder)holder).menu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    PopupMenu popupMenu = new PopupMenu(context,((PieChartHolder) holder).menu);
//                    popupMenu.inflate(R.menu.pie_chart_entries_menu);
//                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item) {
//                            switch (item.getItemId()){
//                                case R.id.edit:
//
//                                    break;
//                                case R.id.delete:
//                                    list.remove(holder.getAdapterPosition());
//                                    notifyItemRemoved(holder.getAdapterPosition());
//                                    return true;
//                            }
//                            return false;
//                        }
//                    });
//                    popupMenu.show();
//                }
//            });
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

    class PieChartHolder extends RecyclerView.ViewHolder {
        TextView name, percentage;
        ImageView color;
        ImageButton menu;
        Context context;

        public PieChartHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.pieChartEntriesName);
            percentage = itemView.findViewById(R.id.pieChartEntriesPercentage);
            color = itemView.findViewById(R.id.pieChartEntriesColor);
            menu = itemView.findViewById(R.id.pieChartEntriesMenu);

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context,menu);
                    popupMenu.inflate(R.menu.pie_chart_entries_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.edit:
                                    Intent intent = new Intent(itemView.getContext(),PieChartAddEntry.class);
                                    intent.putExtra("editName",list.get(getAdapterPosition()).getName());
                                    Log.d("Holder:","Sent " + list.get(getAdapterPosition()).getName());
                                    intent.putExtra("editPercentage",list.get(getAdapterPosition()).getPercentage());
                                    Log.d("Holder:","Sent " + list.get(getAdapterPosition()).getPercentage());
                                    intent.putExtra("editColor",list.get(getAdapterPosition()).getColor());
                                    Log.d("Holder:","Sent " + list.get(getAdapterPosition()).getColor());
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    
                                    itemView.getContext().startActivity(intent);
                                    list.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    break;
                                case R.id.delete:
                                    list.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    return true;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });
        }
    }
}
