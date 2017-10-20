package com.snail.baselibrary.base;

import android.content.Context;

/**
 * @author changsunhaipeng
 */
public class GlobalCache {
    private final static GlobalCache instance = new GlobalCache();
    private Context mContext;

    public static GlobalCache getInstance() {
        return instance;
    }

    public static Context getContext() {
        return getInstance().mContext;
    }

    public void registerContext(Context context) {
        this.mContext = context;
    }
}
