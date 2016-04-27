package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivitySport;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.Step;

/**
 * Created by Simon on 4/23/2016.
 */
public class ViewHistoryActivity extends AppCompatActivity {
    private SqlHelper db;
    private List<ActivitySport> activities;
    private List<Step> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhistory);

        db = new SqlHelper(this);
/*
        for(int i=0; i<10; i++){
            Step step = new Step();
            step.setIdUser(1);
            String date = "2016/04/0" + String.valueOf(i);
            step.setStepsDate(date);
            step.setNbOfSteps(i*i);
            db.addSteps(step);
        }
        */
        ListView listContent = (ListView) findViewById(R.id.activityList);
        //activities = db.getEachDateOfActivity(1);
        steps = db.getAllStepsByUser(1);

        //get data from the table by the ListAdapter
        //ActivityAdapter customAdapter = new ActivityAdapter(this,  activities);

        StepsAdapter customAdapter = new StepsAdapter(this, steps);
        listContent.setAdapter(customAdapter);

        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView arg0, View arg1, int position,
                                    long arg3) {
                TextView date = (TextView) findViewById(R.id.dateActivity);
                Log.d("Date: ", steps.get(position).getStepsDate());
                Log.d("position: ", String.valueOf(position));

                //Intent i = new Intent(ViewHistoryActivity.this, ViewDateDetails.class);
                //i.putExtra("date", steps.get(position).getStepsDate());
                //startActivity(i);
            }
        });
    }
}
