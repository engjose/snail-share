package com.snail.article.bean;

import com.snail.baselibrary.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by changsunhaipeng on 2017/10/26.
 */

public class ArticleListBean extends BaseBean {

    public ArticleListData data;

    public class ArticleListData {
        public int total;
        public List<ArticleListInfo> list = new ArrayList<>();
    }

    public class ArticleListInfo {
        public int id;

        public String title;

        public String shareIcon;

        public int contentId;

        public String shareUrl;

        public String shareDesc;

        public int tagId;

        public String tagName;

        public int userId;

        public String userIcon;

        public String loginName;

        public int scanCount;

        public int collectCount;

        public int commentCount;

        public int starCount;

        public String createAt;

        public String updateAt;
    }
}
