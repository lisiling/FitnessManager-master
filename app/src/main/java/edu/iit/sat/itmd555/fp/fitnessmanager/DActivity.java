package edu.iit.sat.itmd555.fp.fitnessmanager;

/**
 * Created by lisiling on 4/24/16.-----------PreferenceActivity
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.preference.PreferenceActivity;

//public class DActivity extends ActivitySport{
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        TextView tv = new TextView(this);
//        tv.setText("This is D ActivitySport!");
//        tv.setGravity(Gravity.CENTER);
//        setContentView(tv);
//    }
//
//}

public class DActivity extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }
}
