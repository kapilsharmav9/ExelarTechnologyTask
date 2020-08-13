package com.example.exelartechnologytask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    private static int time = 2500;
    TextView logo_text;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, time);
    }

    private void initView() {
        handler = new Handler();
        logo_text = findViewById(R.id.logo_txt);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.uptodown);
        logo_text.setAnimation(animation);
    }
}