package com.mdgiitr.suyash.graph;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by karthik on 9/21/18.
 */

public class LineGraphEntryAdapter extends RecyclerView.Adapter<LineGraphEntryAdapter.LineGraphHolder> implements ItemTouchHelperAdapter {
    private Context context;
    private ArrayList<LineGraphEntryModel> list;

    public LineGraphEntryAdapter(Context context, ArrayList<LineGraphEntryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LineGraphHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_line_graph, parent, false);
        return new LineGraphHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LineGraphHolder holder, int position) {
        LineGraphEntryModel model = list.get(position);
        if (model != null) {
            holder.x_dis.setText(Float.toString(model.getX()));
            holder.y_dis.setText(Float.toString(model.getY()));
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

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(list, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    class LineGraphHolder extends RecyclerView.ViewHolder {

        ImageButton menu;
        TextView x_dis, y_dis;
        Context context;

        public LineGraphHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();
            x_dis = itemView.findViewById(R.id.x_val);
            y_dis = itemView.findViewById(R.id.y_val);
            menu = itemView.findViewById(R.id.delete_pt);

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context, menu);
                    popupMenu.inflate(R.menu.bar_graph_entries_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.edit:
                                    Intent intent = new Intent(itemView.getContext(), LinePointNew.class);
                                    intent.putExtra("x", list.get(getAdapterPosition()).getX());
                                    intent.putExtra("y", list.get(getAdapterPosition()).getY());
                                    intent.putExtra("edit", true);
                                    list.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    ((Activity) context).finish();
                                    itemView.getContext().startActivity(intent);
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