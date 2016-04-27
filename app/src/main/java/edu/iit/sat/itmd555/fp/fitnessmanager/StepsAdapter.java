package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.Step;

/**
 * Created by Simon on 4/25/2016.
 */
public class StepsAdapter extends BaseAdapter {

    private ImageView activityType1;
    private ImageView activityType2;
    private ImageView forward;
    private TextView dateActivity;
    private TextView numberSteps;

    private Context mContext;
    private LayoutInflater mInflator;
    private List<Step> mListSteps;

    private SqlHelper db;

    public StepsAdapter(Context c, List<Step> steps) {
        mContext = c;
        mInflator = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListSteps = steps;
    }

    @Override
    public int getCount() {
        return mListSteps.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        db = new SqlHelper(mContext);

        if(convertView == null) {
            convertView = mInflator.inflate(R.layout.history_list_row, parent,
                    false);
        }

        numberSteps = (TextView) convertView.findViewById(R.id.nbSteps);
        String stepsString = "Steps: " + String.valueOf(mListSteps.get(position).getNbOfSteps());
        numberSteps.setText(stepsString);

        activityType1 = (ImageView) convertView.findViewById(R.id.activityType1);
        if(db.isDistanceAtDate(mListSteps.get(position).getStepsDate())){

            activityType1.setVisibility(View.VISIBLE);
        }
        activityType2 = (ImageView) convertView.findViewById(R.id.activityType2);
        if(db.isWorkoutAtDate(mListSteps.get(position).getStepsDate())){

            activityType2.setVisibility(View.VISIBLE);
        }
        dateActivity = (TextView) convertView.findViewById(R.id.dateActivity);
        dateActivity.setText(mListSteps.get(position).getStepsDate());

        if(activityType2.getVisibility() == View.GONE && activityType1.getVisibility()== View.GONE){
            forward = (ImageView) convertView.findViewById(R.id.forward);
            forward.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }
}
