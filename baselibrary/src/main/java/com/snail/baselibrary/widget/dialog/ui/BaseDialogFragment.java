package com.snail.baselibrary.widget.dialog.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.snail.baselibrary.R;
import com.snail.baselibrary.widget.dialog.DialogParamBundle;

import java.lang.reflect.Field;

/**
 * BaseDialogFragment
 */
public class BaseDialogFragment extends DialogFragment {
    public static final String ARGUMENTS_KEY = "BaseDialogFragment";
    public boolean bIsBackable;// 是否back取消
    public DialogParamBundle mParamBundle = new DialogParamBundle();
    protected String mDialogTag;// 标记
    protected String mContentTxt;// 内容
    protected View.OnClickListener mSpaceClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (mParamBundle != null && mParamBundle.isCancelable) {
                dismissSelf();
                if (mParamBundle.cancelClick != null) {
                    mParamBundle.cancelClick.response();
                }
            }
        }
    };

    public BaseDialogFragment() {

    }

    public static BaseDialogFragment getInstance(Bundle bundle) {
        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogTheme);
        if (mParamBundle != null) {
            mDialogTag = mParamBundle.mKey;
            bIsBackable = mParamBundle.isCancelable;
            mContentTxt = mParamBundle.mLoadingText;
        }
        setCancelable(bIsBackable);
    }

    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        return show(transaction, tag, true);
    }

    public int show(FragmentTransaction transaction, String tag, boolean allowStateLoss) {
        transaction.add(this, tag);
        int mBackStackId = allowStateLoss ? transaction.commitAllowingStateLoss() : transaction.commit();
        return mBackStackId;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        // TODO Auto-generated method stub
        super.onCancel(dialog);
        if (mParamBundle != null && mParamBundle.negitiveveClick != null) {
            mParamBundle.negitiveveClick.response();
        }
    }

    @Override
    public void onDetach() {
        if (mParamBundle != null && mParamBundle.dismissListener != null) {
            mParamBundle.dismissListener.response();
        }
        super.onDetach();
    }

    public void dismissSelf() {
        removeFragment(getFragmentManager(), this);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (manager.isDestroyed()) {
            return;
        }
        try {
            Field mDismissed = DialogFragment.class.getDeclaredField("mDismissed");
            Field mShownByMe = DialogFragment.class.getDeclaredField("mShownByMe");
            mDismissed.setAccessible(true);
            mShownByMe.setAccessible(true);
            mDismissed.set(this, false);
            mShownByMe.set(this, true);
        } catch (NoSuchFieldException e) {
            Log.e("BaseDialogFragment", e.getMessage());
        } catch (IllegalAccessException e) {
            Log.e("BaseDialogFragment", e.getMessage());
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    private void removeFragment(FragmentManager fragmentManager, Fragment targetFragment) {
        // TODO Auto-generated method stub
        if (fragmentManager != null && !fragmentManager.isDestroyed()) {
            String tag = targetFragment.getTag();

            try {
                if (fragmentManager.findFragmentByTag(tag) != null) {
                    fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            } catch (Exception e) {
                Log.e("BaseDialogFragment", e.getMessage());
                try {
                    FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                    localFragmentTransaction.remove(targetFragment);
                    localFragmentTransaction.commitAllowingStateLoss();
                    fragmentManager.executePendingTransactions();
                } catch (Exception e2) {
                    Log.e("BaseDialogFragment", e.getMessage());
                }
            }

        }
    }
}