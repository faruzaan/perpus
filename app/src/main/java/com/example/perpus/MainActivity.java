package com.example.perpus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 3000;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager  = new SessionManager(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i;
                if (sessionManager.is_login())
                {
                    i = new Intent(MainActivity.this,MenuActivity.class);
                }
                else {
                    i = new Intent(MainActivity.this, LoginActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME);
    }
}
