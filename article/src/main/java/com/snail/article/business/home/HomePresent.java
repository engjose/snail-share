package com.snail.article.business.home;

import android.util.Log;

import com.snail.article.R;
import com.snail.article.api.ApiServeice;
import com.snail.article.bean.ArticleListBean;
import com.snail.article.bean.HomeBannerBean;
import com.snail.baselibrary.widget.progress.Progress;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by changsunhaipeng on 2017/10/27.
 */

public class HomePresent implements HomeConstract.Present {
    private HomeConstract.HomeView view;
    private List<ArticleListBean.ArticleListInfo> articleList = new ArrayList<>();

    public HomePresent(HomeConstract.HomeView view) {
        this.view = view;
    }

    @Override
    public HomeAdapter getAdapter() {
        return new HomeAdapter(R.layout.item_article_list, getArticleList());
    }

    @Override
    public List<ArticleListBean.ArticleListInfo> getArticleList() {
        return articleList;
    }

    @Override
    public void initData() {
        Progress progress = view.showProgress();
        ApiServeice.getInstance()
                .getArtList(1)
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.hideProgress();
                    }
                })
                .subscribe(new Consumer<ArticleListBean>() {
                    @Override
                    public void accept(ArticleListBean articleListBean) throws Exception {
                        List<ArticleListBean.ArticleListInfo> list = articleListBean.data.list;
                        articleList.clear();
                        articleList.addAll(list);
                        view.refreshView();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("HomePresent", throwable.getMessage());
                    }
                });

        ApiServeice.getInstance().getHomeBanner()
                .map(new Function<HomeBannerBean, List<String>>() {
                    @Override
                    public List<String> apply(@NonNull HomeBannerBean bannerBean) throws Exception {
                        List<HomeBannerBean.BannerInfo> data = bannerBean.data;
                        List<String> list = new ArrayList<String>();
                        list.clear();
                        for (int i = 0; i < data.size(); i++) {
                            list.add(data.get(i).imgUrl);
                        }
                        return list;
                    }
                })
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        view.getBanner().update(strings);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("HomePresent", throwable.getMessage());
                    }
                });

    }
}
