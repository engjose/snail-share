package com.snail.article.business.home;

import android.view.View;

import com.snail.article.bean.ArticleListBean;
import com.snail.baselibrary.widget.progress.Progress;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by changsunhaipeng on 2017/10/26.
 */

public interface HomeConstract {
    interface HomeView {
        void initView(android.view.View view);

        View getHeadView();

        Progress showProgress();

        void hideProgress();

        void refreshView();

        Banner getBanner();

        void initListener();

    }

    interface Present {
        HomeAdapter getAdapter();

        List<ArticleListBean.ArticleListInfo> getArticleList();

        void initData();
    }
}
