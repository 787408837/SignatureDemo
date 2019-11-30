package common;

import android.app.Application;

import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * @author Klein
 * @version 1.1.5
 * @date 2018/12/6 11:34
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ScreenAdapterTools.init(this);
    }

}
