package com.snail.snailshare.main;

import android.os.Bundle;
import android.support.annotation.IdRes;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.snail.article.business.chat.ChatFragment;
import com.snail.article.business.friendcircle.FriendCircleFragment;
import com.snail.article.business.home.HomeFragment;
import com.snail.article.business.personcenter.PersonCenterFragment;
import com.snail.baselibrary.base.BaseActivity;
import com.snail.baselibrary.base.FragmentController;
import com.snail.snailshare.R;

public class MainActivity extends BaseActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        iniData();
        initListener();
    }

    private void initView() {
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
    }

    private void iniData() {

    }

    private void initListener() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        FragmentController.replaceFragment(getSupportFragmentManager(), HomeFragment.newInstance(), HomeFragment.TAG, R.id.main_container);
                        break;
                    case R.id.tab_friend_circle:
                        FragmentController.replaceFragment(getSupportFragmentManager(), new FriendCircleFragment(), "FriendCircleFragment", R.id.main_container);
                        break;
                    case R.id.tab_chat:
                        FragmentController.replaceFragment(getSupportFragmentManager(), new ChatFragment(), "ChatFragment", R.id.main_container);
                        break;
                    case R.id.tab_person_center:
                        FragmentController.replaceFragment(getSupportFragmentManager(), new PersonCenterFragment(), "PersonCenterFragment", R.id.main_container);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
