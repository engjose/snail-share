package com.snail.article.business.personcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snail.article.R;
import com.snail.baselibrary.base.BaseFragment;

/**
 * Created by changsunhaipeng on 2017/10/21.
 */

public class PersonCenterFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_center, container, false);
    }
}
