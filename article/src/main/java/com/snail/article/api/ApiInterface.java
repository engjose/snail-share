package com.snail.article.api;

import com.snail.article.bean.ArticleListBean;
import com.snail.article.bean.HomeBannerBean;
import com.snail.baselibrary.net.UrlConstance;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author changsunhaipeng
 * @date 2017/10/21
 */

public interface ApiInterface {

    /**
     * 查询第三方店铺信息
     */
    interface ArtList {
        @Headers({"Accept: application/json"})
        @GET(UrlConstance.ART_LIST)
        Observable<ArticleListBean> artList(
                @Query("pageNo") int pageNo,
                @Query("pageSize") int pageSize
        );
    }

    /**
     * 首页banner
     */
    interface HomeBanner {
        @Headers({"Accept: application/json"})
        @GET(UrlConstance.HOME_BANNER)
        Observable<HomeBannerBean> banner(
                @Query("app") String app
        );
    }
}
