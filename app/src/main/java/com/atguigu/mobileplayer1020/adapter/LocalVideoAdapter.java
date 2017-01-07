package com.atguigu.mobileplayer1020.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.mobileplayer1020.bean.MediaItem;

import java.util.ArrayList;

/**
 * Created by 张永卫 on 2017/1/7.
 */

public class LocalVideoAdapter extends BaseAdapter {

    private Context mContext;

    private ArrayList<MediaItem> datas;

    public LocalVideoAdapter(Context mContext,ArrayList<MediaItem> mediaItems){

        this.mContext = mContext;
        this.datas = mediaItems;
    }
    @Override
    public int getCount() {
        return datas.size();
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
        TextView textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        MediaItem mediaItem = datas.get(position);

        textView.setText(mediaItem.toString());

        return textView;
    }
}
