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
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;


/**
 * @author changsunhaipeng
 */
public abstract class BaseFragment extends Fragment implements Host, OnKeyBackListener {
    public String TAG = this.getClass().getName();

    protected View rootView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
    }

    public String getName() {
        return BaseFragment.class.getName();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public boolean onKeyBack() {
        return false;
    }


    @Override
    public FragmentManager getFragmentManagerWithinHost() {
        return getFragmentManager();
    }

    @Override
    public Drawable getDrawableWithinHost(@DrawableRes int id) {
        Drawable drawable = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = getActivity().getDrawable(id);
            } else {
                drawable = getActivity().getResources().getDrawable(id);
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
        getActivity().startActivityForResult(intent, requestCode, options);
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
        return (BaseActivity) getActivity();
    }

    @Override
    public Context getContextWithinHost() {
        return getActivity();
    }

}
