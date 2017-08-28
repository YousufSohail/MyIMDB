package com.yousufsohail.myimdb.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yousufsohail.myimdb.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initializeSplashHandler();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, MovieListActivity.class));
            finish();
        }
    };

    private void initializeSplashHandler() {
        int SPLASH_DISPLAY_LENGTH = 1500;
        new Handler().postDelayed(mRunnable, SPLASH_DISPLAY_LENGTH);
    }
}
