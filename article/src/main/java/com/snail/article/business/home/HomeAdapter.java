package com.snail.article.business.home;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.snail.article.R;
import com.snail.article.bean.ArticleListBean;
import com.snail.baselibrary.base.GlobalCache;

import java.util.List;

/**
 * Created by changsunhaipeng on 2017/10/26.
 */

public class HomeAdapter extends BaseQuickAdapter<ArticleListBean.ArticleListInfo, BaseViewHolder> {

    public HomeAdapter(@LayoutRes int layoutResId, @Nullable List<ArticleListBean.ArticleListInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleListBean.ArticleListInfo item) {
        helper.setText(R.id.tv_title, item.title);
        helper.setText(R.id.tv_desc, item.shareDesc);
        ImageView thumb = helper.getView(R.id.iv_thumb);
        Glide.with(GlobalCache.getContext()).load(item.shareIcon).into(thumb);
    }
}
