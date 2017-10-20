package com.snail.baselibrary.widget.progress;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snail.baselibrary.R;
import com.snail.baselibrary.widget.dialog.DialogParamBundle;
import com.snail.baselibrary.widget.dialog.BaseDialogFragment;

/**
 * Progress,Floating Progress with various of function
 */
public class Progress extends BaseDialogFragment {
    public static Progress getNewInstance(DialogParamBundle bundle) {
        Progress progress = new Progress();
        progress.mParamBundle = bundle;
        return progress;
    }

    private TextView tv_loadingText;
    private ImageView iv_route;
    private View layoutView;
    private static final int CHNAGE_TITLE_DELAYMILLIS = 300;
    private static final int MAX_SUFFIX_NUMBER = 3;
    private static final char SUFFIX = '.';
    private TextView tv_point;
    private String serviceKey = "";
    private boolean isShowPoint=true;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialogTag = mParamBundle.mKey;
        mContentTxt = mParamBundle.mLoadingText;
//        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.progress_dialog_style);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.progress_layout, container, false);
        layoutView.setOnClickListener(mSpaceClickListener);
        tv_loadingText = (TextView) layoutView.findViewById(R.id.tip);
        iv_route = (ImageView) layoutView.findViewById(R.id.iv_rrroute);
        tv_point = (TextView) layoutView.findViewById(R.id.tv_point);
        initAnim();
        if (!TextUtils.isEmpty(mContentTxt)) {
            tv_loadingText.setText(mContentTxt);
            tv_point.setVisibility(isShowPoint?View.VISIBLE:View.GONE);
        }
       /* View cancel_button = layoutView.findViewById(R.id.btn_cancel);
        if (!mParamBundle.isShowDismissButton) {
            cancel_button.setVisibility(View.GONE);
        } else {
            cancel_button.setOnClickListener(mSpaceClickListener);
            cancel_button.setVisibility(View.VISIBLE);
        }*/
        return layoutView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        changeTitle();
    }

    public void updateText(final String str) {
        if (str == null) {
            return;
        }
        if (tv_loadingText == null) {
            mContentTxt = str;
            isShowPoint=false;
            return;
        }
        tv_point.setVisibility(View.GONE);
        if (Looper.getMainLooper() != Looper.myLooper()) {
            tv_loadingText.post(new Runnable() {
                @Override
                public void run() {
                    updateText(str);
                }
            });
        } else {
            tv_loadingText.setText(str);
        }
    }

    public void updateText(@StringRes int strID) {
        updateText(getString(strID));
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mParamBundle != null && mParamBundle.isCancelable && !TextUtils.isEmpty(serviceKey)) {
//            BaseThreadPool.getInstance().cancelTask(serviceKey);
        }
    }

    private void initAnim() {
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_route.getDrawable();
        animationDrawable.start();
    }

    private int num = 0;

    private void changeTitle() {

        StringBuilder builder = new StringBuilder();
        if (num >= MAX_SUFFIX_NUMBER) {
            num = 0;
        }
        num++;
        for (int i = 0; i < num; i++) {
            builder.append(SUFFIX);
        }
        tv_point.setText(builder.toString());
        if (!isDetached()) {
            layoutView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeTitle();
                }
            }, CHNAGE_TITLE_DELAYMILLIS);
        } else {
            num = 0;
        }
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }
}
