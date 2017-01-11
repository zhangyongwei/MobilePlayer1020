package com.atguigu.mobileplayer1020.app;

        import android.app.Application;

        import org.xutils.x;

/**
 * Created by 张永卫 on 2017/1/10.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
