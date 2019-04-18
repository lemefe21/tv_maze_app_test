package com.leme.tvmazeapptest.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.leme.tvmazeapptest.R;

import static com.leme.tvmazeapptest.utils.AppValues.DELAY_SPLASH_SCREEN;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        showSplashScreen();

    }

    private void showSplashScreen() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMainActivity();
            }
        }, DELAY_SPLASH_SCREEN);

    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
