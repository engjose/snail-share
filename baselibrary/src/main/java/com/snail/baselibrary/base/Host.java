package com.snail.baselibrary.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;


/**
 * @author changsunhaipeng
 */
public interface Host {

    /**
     * 获取Context
     *
     * @return Context
     */
    Context getContextWithinHost();

    /**
     * 获取Activity
     *
     * @return BaseActivity
     */
    BaseActivity getActivityWithinHost();

    /**
     * 获取FragmentManager
     *
     * @return FragmentManager
     */
    FragmentManager getFragmentManagerWithinHost();

    /**
     * 跳转Activity
     *
     * @param intent Intent
     */
    void startActivityWithinHost(Intent intent);

    /**
     * 跳转Activity
     *
     * @param intent      Intent
     * @param requestCode int
     */
    void startActivityForResultWithinHost(Intent intent, int requestCode);

    /**
     * 跳转Activity
     *
     * @param intent      Intent
     * @param requestCode int
     * @param options     Bundle
     */
    void startActivityForResultWithinHost(Intent intent, int requestCode, @Nullable Bundle options);

    /**
     * 获取Resources
     *
     * @return Resources
     */
    Resources getResourcesWithinHost();

    /**
     * 获取Drawable
     *
     * @param id int
     * @return Drawable
     */
    Drawable getDrawableWithinHost(@DrawableRes int id);

    /**
     * 获取String
     *
     * @param resId int
     * @return String
     */
    String getStringWithinHost(@StringRes int resId);

}