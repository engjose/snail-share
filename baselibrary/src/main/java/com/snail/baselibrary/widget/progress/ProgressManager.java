package com.snail.baselibrary.widget.progress;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

import com.snail.baselibrary.base.BaseFragment;
import com.snail.baselibrary.base.Host;
import com.snail.baselibrary.widget.dialog.DialogParamBundle;

/**
 * ProgressManager
 */
public class ProgressManager {

    public static String getDefaultTag(Object ob) {
        return ob.getClass().getName() + "progress";
    }

    public static Progress showProgress(Host host) {
        return showProgress(host, getDefaultTag(host), "", true);
    }

    public static Progress showProgressUncancel(Host host) {
        return showProgress(host, getDefaultTag(host), "", false);
    }

    public static Progress showProgressUncancel(Host host, String loadingText) {
        return showProgress(host, getDefaultTag(host), loadingText, false);
    }

    public static Progress showProgress(Host host, @StringRes int loadingText) {
        return showProgress(host, getDefaultTag(host), host.getStringWithinHost(loadingText), true);
    }

    public static Progress showProgress(Host host, String loadingText) {
        return showProgress(host, getDefaultTag(host), loadingText, true);
    }

    public static Progress showProgress(Host host, String loadingText, boolean cancelable) {
        return showProgress(host, getDefaultTag(host), loadingText, cancelable);
    }

    public static Progress showProgress(Host host, String key, String loadingText, boolean cancelable) {
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setActivity(host.getActivityWithinHost())
                .setKey(key)
                .setLoadingText(loadingText)
                .setCancelable(cancelable);
        if (host instanceof BaseFragment) {
            builder.setFragment((BaseFragment) host);
        }
        return showProgress(builder.build());
    }

    public static Progress showProgress(DialogParamBundle bundle) {
        Progress progress = Progress.getNewInstance(bundle);
        FragmentManager fragmentManager = bundle.mFragmentManager;

        if (fragmentManager != null) {
            if (bundle.mFragmentRef != null && bundle.mFragmentRef.get() != null) {
                progress.setTargetFragment(bundle.mFragmentRef.get(), bundle.DIALOG_REQUEST_CODE);
            }
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(progress, bundle.mKey);
            ft.commitAllowingStateLoss();
//            fragmentManager.executePendingTransactions();
        }
        return progress;
    }

    public static void closeProgress(Host host) {
        closeProgress(host, getDefaultTag(host));
    }

    public static void closeProgress(Host host, String key) {
        closeProgress(host.getFragmentManagerWithinHost(), key);
    }

    public static void closeProgress(FragmentManager fragmentManager, String key) {
        removeFragment(fragmentManager, key);
    }

    private static void removeFragment(FragmentManager fragmentManager, String tag) {
        if (fragmentManager != null && !TextUtils.isEmpty(tag) && !fragmentManager.isDestroyed()) {
            Fragment targment = fragmentManager.findFragmentByTag(tag);
            if (targment != null) {
                try {
                    fragmentManager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } catch (Exception e) {
                    Log.e("ProgressManager", e.getMessage());
                }
                FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();

                localFragmentTransaction.remove(targment);
                localFragmentTransaction.commitAllowingStateLoss();
                try {
                    fragmentManager.executePendingTransactions();
                } catch (Exception e) {
                    Log.e("ProgressManager", e.getMessage());
                }
            }
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null && !fragment.isDetached()) {
                FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                localFragmentTransaction.remove(fragment);
                localFragmentTransaction.commitAllowingStateLoss();
                try {
                    fragmentManager.executePendingTransactions();
                } catch (Exception e) {
                    Log.e("ProgressManager", e.getMessage());
                }
            }
        }
    }
}
