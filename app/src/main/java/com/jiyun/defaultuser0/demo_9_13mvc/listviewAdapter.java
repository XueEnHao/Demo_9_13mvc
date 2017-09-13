package com.jiyun.defaultuser0.demo_9_13mvc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by defaultuser0 on 2017/9/13.
 */

public class listviewAdapter extends BaseAdapter {
    private Context context;
    private List<bean.ResultBean.ListBean> list;
    public listviewAdapter(MainActivity mainActivity, List<bean.ResultBean.ListBean> list) {
        this.context=mainActivity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.buju,null);
            holder.img = convertView.findViewById(R.id.img);
            holder.tv_source = convertView.findViewById(R.id.tv_source);
            holder.tv_text = convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_source.setText(list.get(position).getSource());
        holder.tv_text.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getFirstImg()).into(holder.img);

        return convertView;
    }

    class ViewHolder{
        ImageView img;
        TextView tv_source;
        TextView tv_text;
    }
}
