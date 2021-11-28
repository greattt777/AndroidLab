package com.example.lab3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;
    private ListView listView;

    public MyAdapter(List<String> data, Context context, ListView listView){
        this.data = data;
        this.context = context;
        this.listView = listView;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            viewHolder.iv = convertView.findViewById(R.id.iv);
            viewHolder.tv = convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv.setImageResource(R.drawable.cat);
        viewHolder.tv.setText(data.get(position));
        if(listView.isItemChecked(position)){
            convertView.setBackgroundColor(Color.parseColor("#00BCD4"));
        }else{
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        return convertView;
    }

    private final class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
