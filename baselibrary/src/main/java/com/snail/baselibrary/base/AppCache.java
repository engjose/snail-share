package com.snail.baselibrary.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changsunhaipeng
 */
public class AppCache {
    protected final static AppCache instance = new AppCache();
    private List<BaseActivity> activityList = new ArrayList<>();
    private String appVersionName;
    private int versionCode = 0;

    private AppCache() {

    }

    public static AppCache getInstance() {
        return instance;
    }

    public void addActivity(BaseActivity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
        activityList.add(activity);
    }

    public void removeActivity(BaseActivity activity) {
        activityList.remove(activity);
    }

    public BaseActivity getTopActivity() {
        if (activityList.size() > 0) {
            return activityList.get(activityList.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityList.size(); i < size; i++) {
            if (null != activityList.get(i)) {
                activityList.get(i).finish();
            }
        }
        activityList.clear();
    }


    public String getAppVersionName() {
        if (TextUtils.isEmpty(appVersionName)) {
            try {
                appVersionName = GlobalCache.getContext().getPackageManager().getPackageInfo(
                        GlobalCache.getContext().getPackageName(), 0).versionName;
                if (appVersionName.contains("_")) {
                    appVersionName = appVersionName.substring(0, appVersionName.indexOf("_"));
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("AppCahch", e.getMessage());
            }
        }
        return appVersionName;
    }

    public int getAppVersionCode() {
        if (versionCode == 0) {
            try {
                versionCode = GlobalCache.getContext().getPackageManager().getPackageInfo(
                        GlobalCache.getContext().getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("AppCahch", e.getMessage());
            }
        }
        return versionCode;
    }

    /**
     * 应用程序完全退出
     *
     * @param context 当前上下文
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context
                    .ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
