package com.snail.article.business.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.snail.article.R;
import com.snail.baselibrary.base.BaseFragment;
import com.snail.baselibrary.widget.progress.Progress;
import com.snail.baselibrary.widget.progress.ProgressManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by changsunhaipeng on 2017/10/21.
 */

public class HomeFragment extends BaseFragment implements HomeConstract.HomeView {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView mRv;
    private HomePresent homePresent;
    private LinearLayoutManager layoutManager;
    private HomeAdapter mAdapter;
    private Banner banner;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresent = new HomePresent(this);
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        homePresent.initData();
        initListener();
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(homePresent.getArticleList().get(position).shareUrl);
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://www.baidu.com");
                intent.setData(content_url);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initView(android.view.View view) {
        mRv = view.findViewById(R.id.rv_home);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        mAdapter = homePresent.getAdapter();
        mAdapter.addHeaderView(getHeadView());
        mRv.setAdapter(mAdapter);
    }

    @Override
    public View getHeadView() {

        List<String> imgList = new ArrayList<>();
        imgList.clear();
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.banner_home, null);
        banner = headView.findViewById(R.id.banner_home);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imgList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        return headView;
    }

    @Override
    public Banner getBanner() {
        return banner;
    }

    @Override
    public Progress showProgress() {
        return ProgressManager.showProgress(this, "加载中", false);
    }

    @Override
    public void hideProgress() {
        ProgressManager.closeProgress(this);
    }

    @Override
    public void refreshView() {
        mAdapter.notifyDataSetChanged();
    }
}
