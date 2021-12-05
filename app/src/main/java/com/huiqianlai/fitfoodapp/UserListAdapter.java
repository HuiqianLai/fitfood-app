package com.huiqianlai.fitfoodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class UserListAdapter extends ArrayAdapter<HashMap> {

    private int resourceId;
    private List<HashMap> mapList;


    public UserListAdapter(Context context, int textViewResourceId,
                           List<HashMap> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        mapList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HashMap<String, String> hashMap = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.result = (TextView) view.findViewById(R.id.result);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }

        for (String key : hashMap.keySet()) {
            viewHolder.name.setText(key.toString());
            viewHolder.result.setText(String.valueOf(hashMap.get(key)));
        }

        return view;
    }

    class ViewHolder {

        TextView name;

        TextView result;

    }
}
