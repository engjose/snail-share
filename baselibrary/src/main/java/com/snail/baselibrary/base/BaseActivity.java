package com.snail.baselibrary.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.List;


/**
 * @author changsunhaipeng
 */
public abstract class BaseActivity extends FragmentActivity implements Host {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCache.getInstance().addActivity(this);
        Log.i("onCreate---", this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppCache.getInstance().removeActivity(this);
        Log.i("onDestroy---", this.getClass().getSimpleName());
    }

    @Override
    public FragmentManager getFragmentManagerWithinHost() {
        return getSupportFragmentManager();
    }

    @Override
    public Drawable getDrawableWithinHost(@DrawableRes int id) {
        Drawable drawable = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = getDrawable(id);
            } else {
                drawable = getResources().getDrawable(id);
            }
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getMessage());
        }
        return drawable;
    }

    @Override
    public String getStringWithinHost(@StringRes int resId) {
        return getString(resId);
    }

    @Override
    public Resources getResourcesWithinHost() {
        return getResources();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void startActivityForResultWithinHost(Intent intent, int requestCode, @Nullable Bundle options) {
        startActivityForResult(intent, requestCode, options);
    }

    @Override
    public void startActivityForResultWithinHost(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityWithinHost(Intent intent) {
        startActivity(intent);
    }

    @Override
    public BaseActivity getActivityWithinHost() {
        return this;
    }

    @Override
    public Context getContextWithinHost() {
        return this;
    }

    @Override
    public boolean isDestroyed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return super.isDestroyed();
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        int size = getSupportFragmentManager().getBackStackEntryCount();
        if (size > 0) {

            FragmentManager.BackStackEntry topEntry = getSupportFragmentManager().getBackStackEntryAt(size - 1);
            Fragment topFragment = getSupportFragmentManager().findFragmentByTag(topEntry.getName());
            if (topFragment != null && topFragment.isResumed() && topFragment.isVisible()) {
                if (topFragment instanceof OnKeyBackListener) {
                    boolean handled = ((OnKeyBackListener) topFragment).onKeyBack();
                    if (!handled) {
                        super.onBackPressed();
                    }
                } else {
                    getSupportFragmentManager().popBackStackImmediate();
                }
            } else {
                super.onBackPressed();
            }
        } else {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            if (fragments != null && fragments.size() > 0) {
                Fragment topFragment = fragments.get(fragments.size() - 1);
                if (topFragment instanceof OnKeyBackListener) {
                    boolean handled = ((OnKeyBackListener) topFragment).onKeyBack();
                    if (!handled) {
                        super.onBackPressed();
                    } else {
                        return;
                    }
                } else {
                    super.onBackPressed();
                }
            } else {
                super.onBackPressed();
            }
        }
    }
}
