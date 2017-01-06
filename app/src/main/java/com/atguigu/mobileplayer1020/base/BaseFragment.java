package com.atguigu.mobileplayer1020.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 张永卫 on 2017/1/6.
 * 本地视频，本地音乐，网络音频，网络视频的父类
 */

public abstract class BaseFragment extends Fragment {
    /**
     * 上下文
     */
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
    }

    /**
     * 当创建View的时候回调
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 抽象类，强制让孩子实现，实现自己特有的UI
     * @return
     */
    public abstract View initView() ;

    /**
     *当Activity创建好的时候回调
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化数据用的，当子类需要联网请求得到数据额时候，重写该方法
     */
    protected  void initData(){

    }
}
