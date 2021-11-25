package com.huiqianlai.fitfoodapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huiqianlai.fitfoodapp.bean.HistoryBean;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {

    private Context context;
    private HistoryBean bean;
    private Callback callback;

    public HistoryRecyclerAdapter(Context context, HistoryBean historyBean, Callback callback) {
        this.context = context;
        this.bean = historyBean;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("laihuiqian", "HistoryRecyclerAdapter onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.e("laihuiqian", "HistoryRecyclerAdapter onBindViewHolder position:" + position);

//        String path = bean.getData().getData().get(position).getPath().replace("/public_uploads/meal_images/", "");
//        String newPath = "https://fit-food-heroku.herokuapp.com/public_uploads/meal_images/" + path;
//        Log.e("laihuiqian", "newPath:" + newPath);

        holder.textView.setText("This position is:" + position);

//        Glide.with(context).load(newPath).into(holder.imageView);

//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("laihuiqian", "is Click!!!!");
//            }
//        });

//        if (position == getItemCount() - 1) {
//            Log.e("laihuiqian", "HistoryRecyclerAdapter Need to Show UI");
//            callback.onSuccess();
//        }

        callback.onSuccess();
    }

    @Override
    public int getItemCount() {
        return bean.getData().getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
//            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.textview);

        }
    }
}