package com.atguigu.mobileplayer1020.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mobileplayer1020.R;
import com.atguigu.mobileplayer1020.activity.SystemVideoPlayerActivity;
import com.atguigu.mobileplayer1020.adapter.LocalVideoAdapter;
import com.atguigu.mobileplayer1020.base.BaseFragment;
import com.atguigu.mobileplayer1020.bean.MediaItem;

import java.util.ArrayList;

/**
 * Created by 张永卫 on 2017/1/6.
 */

public class LocalVideoFragment extends BaseFragment {

    private ListView listView;
    private TextView tv_no_media;
    private LocalVideoAdapter adapter;
    /**
     * 数据集合
     */
    private ArrayList<MediaItem> mediaItems;


    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //设置适配器
            if(mediaItems!=null && mediaItems.size()> 0){

                //有数据
                //文本隐藏
                tv_no_media.setVisibility(View.GONE);
                adapter = new LocalVideoAdapter(mContext,mediaItems);
                //设置适配器
                listView.setAdapter(adapter);

            }else{

                //没有文本
                //文本显示
                tv_no_media.setVisibility(View.VISIBLE);
            }
        }
    };
    /**
     * 子类必须重写的父类的方法
     * @return
     */
    @Override
    public View initView() {
        Log.e("TAG","本地视频ui初始化了..");

        View view = View.inflate(mContext, R.layout.fragment_local_vedio,null);
        listView = (ListView) view.findViewById(R.id.listView);
        tv_no_media = (TextView) view.findViewById(R.id.tv_no_media);

        //设置监听
        listView.setOnItemClickListener(new MyOnItemClickListener());
        return view;
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            MediaItem mediaItem = mediaItems.get(position);
            //调起自定义播放器
            Intent intent = new Intent(mContext, SystemVideoPlayerActivity.class);
            Bundle bundle = new Bundle();
            //列表数据
            bundle.putSerializable("videolist",mediaItems);
            intent.putExtras(bundle);
            //传递点击的位置
            intent.putExtra("position",position);
            startActivity(intent);

        }
    }
    @Override
    protected void initData() {
        super.initData();
        Log.e("TAG","本地视频数据初始化了..");
        //在子线程中加载视频
        getDataFramLocal();
    }

    /**
     * 子线程得到视频
     */
    private void getDataFramLocal() {

        new Thread(){

            @Override
            public void run() {
                super.run();
                //初始化集合
                mediaItems = new ArrayList<MediaItem>();
                ContentResolver resolver = mContext.getContentResolver();

                //sdcard的视频路径
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DURATION,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media.ARTIST
                };

                Cursor cursor = resolver.query(uri, objs, null, null, null);
                  if(cursor!=null){

                    while (cursor.moveToNext()){

                        MediaItem mediaItem = new MediaItem();

                        //添加到集合中
                        mediaItems.add(mediaItem);//可以

                        String name = cursor.getString(0);
                        mediaItem.setName(name);
                        long duration = cursor.getLong(1);
                        mediaItem.setDuration(duration);
                        long size = cursor.getLong(2);
                        mediaItem.setSize(size);
                        String data = cursor.getString(3);//播放地址
                        mediaItem.setData(data);
                        String artist = cursor.getString(4);//艺术家
                        mediaItem.setArtist(artist);

                    }
                    cursor.close();
                }
                //发消息--切换到主线程
                handler.sendEmptyMessage(2);
            }
        }.start();
    }

    @Override
    public void onRefrshData() {
        super.onRefrshData();
    }
}
