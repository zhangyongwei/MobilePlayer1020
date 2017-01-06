package com.atguigu.mobileplayer1020.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.mobileplayer1020.base.BaseFragment;

/**
 * Created by 张永卫 on 2017/1/6.
 */

public class NetAudioFragment extends BaseFragment {
    private TextView textView;
    /**
     * 子类必须重写的父类的方法
     * @return
     */
    @Override
    public View initView() {
        Log.e("TAG","网络音乐UI初始化了..");
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e("TAG","网络音乐数据初始化了..");
        textView.setText("网络音乐");
    }
}
