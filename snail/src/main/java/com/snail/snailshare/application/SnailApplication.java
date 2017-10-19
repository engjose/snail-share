package com.snail.snailshare.application;

import android.app.Application;

import com.snail.baselibrary.config.Config;
import com.snail.snailshare.R;

/**
 * Created by changsunhaipeng on 2017/10/19.
 */

public class SnailApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Config.changeEnv(Integer.parseInt(getString(R.string.meta_env)));
    }
}
