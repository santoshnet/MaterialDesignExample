package com.santoshnet.MaterialDesignExample.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.santoshnet.MaterialDesignExample.R;

import static com.santoshnet.MaterialDesignExample.activity.LoginActivity.MY_PREFS_NAME;

public class SplashScreen extends AppCompatActivity {
    ProgressBar bar;
    TextView txt;
    int total = 0;
    boolean isRunning = false;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        bar = (ProgressBar) findViewById(R.id.ProgressBar1);
        txt = (TextView) findViewById(R.id.txtrere);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String restoredText = prefs.getString("email", null);
        Toast.makeText(getApplicationContext(), restoredText, Toast.LENGTH_LONG).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    // Update the progress status
                    progressStatus += 1;

                    // Try to sleep the thread for 20 milliseconds
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            bar.setProgress(progressStatus);
                            // Show the progress on TextView
                            txt.setText("Loading..." + progressStatus + "");
                            if (progressStatus == 100) {
                                if (restoredText == null) {
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                } else {

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    });
                }
            }
        }).start(); // Start the operation
    }

}