package com.snail.article.api;

import com.snail.article.bean.ArticleListBean;
import com.snail.article.bean.HomeBannerBean;
import com.snail.baselibrary.net.RetrofitHelper;
import com.snail.baselibrary.net.UrlConstance;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by changsunhaipeng on 2017/10/21.
 */

public class ApiServeice {
    private static ApiServeice instance = null;

    public static ApiServeice getInstance() {
        synchronized (ApiServeice.class) {
            if (instance == null) {
                instance = new ApiServeice();
            }
        }
        return instance;
    }

    private static final int pageSize = 10;

    /**
     * 获取第三方门店信息
     *
     * @return
     */
    public Observable<ArticleListBean> getArtList(int pageNo) {
        return RetrofitHelper.getInstance()
                .getApiService(UrlConstance.HOST_URL, ApiInterface.ArtList.class, null)
                .artList(pageNo, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * 首页Banner
     *
     * @return
     */
    public Observable<HomeBannerBean> getHomeBanner() {
        return RetrofitHelper.getInstance()
                .getApiService(UrlConstance.CMS_HOST_URL, ApiInterface.HomeBanner.class, null)
                .banner(Constance.SHARE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


}
