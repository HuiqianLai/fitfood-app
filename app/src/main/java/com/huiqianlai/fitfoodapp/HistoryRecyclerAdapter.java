package com.huiqianlai.fitfoodapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.huiqianlai.fitfoodapp.bean.HistoryBean;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        View view;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.textview);

        }
    }


    private Context mContext;
    private HistoryBean mBean;

    public HistoryRecyclerAdapter(Context context, HistoryBean historyBean) {
        mContext = context;
        mBean = historyBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("laihuiqian", "HistoryRecyclerAdapter onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.e("laihuiqian", "HistoryRecyclerAdapter onBindViewHolder position:" + position);

        String path = mBean.getData().getData().get(position).getPath().replace("/public_uploads/meal_images/", "");
        String newPath = "https://fit-food-heroku.herokuapp.com/public_uploads/meal_images/" + path;
        Log.e("laihuiqian", "newPath:" + newPath);

       // holder.textView.setText("This position is:" + position);

        Glide.with(mContext).load(newPath).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("laihuiqian", "is Click!!!!");
            }
        });

        if (position == getItemCount() - 1) {
            Log.e("laihuiqian", "HistoryRecyclerAdapter Need to Show UI");
//            callback.onSuccess();
        }
    }

    @Override
    public int getItemCount() {
        return mBean.getData().getData().size();
    }

}