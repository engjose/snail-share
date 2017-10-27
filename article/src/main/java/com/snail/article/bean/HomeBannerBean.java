package com.snail.article.bean;

import com.snail.baselibrary.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by changsunhaipeng on 2017/10/27.
 */

public class HomeBannerBean extends BaseBean {
    public List<BannerInfo> data = new ArrayList<>();

    public class BannerInfo {
        public int id;
        public String imgUrl;
        public String title;
        public String content;
        public String jumpUrl;
        public String app;
        public String upateAt;
        public String createAt;
        public String status;
        public String priority;
    }
}
