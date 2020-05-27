package com.example.lab5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.MyViewHolder>{

    ArrayList<HistoryHelper> historyHelpers;
    Activity context;

    public AdapterHistory(Activity context, ArrayList<HistoryHelper> historyHelpers) {
        this.historyHelpers = historyHelpers;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int idForItem = R.layout.rv_history_item;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(idForItem, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return historyHelpers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_cat;
        TextView tv_likeOrDislike;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_cat = itemView.findViewById(R.id.iv_cat_history);
            tv_likeOrDislike = itemView.findViewById(R.id.tv_liseOrDislike);

        }

        void bind(final int position) {
            iv_cat.setImageBitmap(historyHelpers.get(position).getImage());
            if (historyHelpers.get(position).getLike() == 1) {
                tv_likeOrDislike.setText("Like");
                tv_likeOrDislike.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
            } else {
                tv_likeOrDislike.setText("Dislike");
                tv_likeOrDislike.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
            }
        }

    }
}
