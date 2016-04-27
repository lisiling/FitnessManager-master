package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
/**
 * Created by Simon on 4/11/2016.
 */

public class Splash extends AppCompatActivity {
    // splash time
    private static int SPLASH_TIME = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent();
                intent.setClass(Splash.this, MainActivity.class);


                Splash.this.startActivity(intent);
                Splash.this.finish();
            }
        }, SPLASH_TIME);
    }

}
