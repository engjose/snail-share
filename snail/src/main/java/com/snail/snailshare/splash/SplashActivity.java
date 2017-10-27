package com.snail.snailshare.splash;

import android.content.Intent;
import android.os.Bundle;

import com.snail.baselibrary.base.BaseActivity;
import com.snail.snailshare.R;
import com.snail.snailshare.main.MainActivity;

import me.wangyuwei.particleview.ParticleView;

public class SplashActivity extends BaseActivity {


    private ParticleView mParticleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        mParticleView = findViewById(R.id.particle_view);
        mParticleView.startAnim();
        mParticleView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
