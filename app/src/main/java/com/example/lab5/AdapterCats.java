package com.example.lab5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterCats extends RecyclerView.Adapter<AdapterCats.MyViewHolder>{

    private Cats catInfo;
    private TableHistory tableHistory;

    public AdapterCats(Context context) {
        catInfo =  Cats.getInstance();
        tableHistory = new TableHistory(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int idForItem = R.layout.rv_item;

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
        return catInfo.getImageURLs().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_cat;
        Button btn_like;
        Button btn_dislike;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_cat = itemView.findViewById(R.id.iv_cat);
            btn_like = itemView.findViewById(R.id.btn_like);
            btn_dislike = itemView.findViewById(R.id.btn_dislike);

        }

        void bind(final int position) {
            if (position < catInfo.getImage().size()) {
                iv_cat.setImageBitmap(catInfo.getImage().get(position));
                btn_like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tableHistory.insert(1, catInfo.getImage().get(position));
                    }
                });
                btn_dislike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tableHistory.insert(0, catInfo.getImage().get(position));
                    }
                });
            }

        }

    }
}