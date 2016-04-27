package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivitySport;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivityDistance;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivityWorkout;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.Goal;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.Step;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.User;


/**
 * Created by Simon on 4/19/2016.
 */
public class SqlHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "FitnessManagerDB";

    // Users table name
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ACTIVITIES = "activities";
    private static final String TABLE_DISTANCE_ACTIVITIES = "distance_activities";
    private static final String TABLE_WORKOUT_ACTIVITIES = "workout_activities";
    private static final String TABLE_GOALS = "goals";
    private static final String TABLE_STEPS = "steps";

    // Users Table Columns names
    private static final String KEY_ID_USER = "id";
    private static final String KEY_PSWD = "password";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_AGE = "age";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_METRICS_CHOICE = "metrics";

    // Goals Table Columns names
    private static final String KEY_ID_GOAL = "id";
    private static final String KEY_FK_ID_USER_FOR_GOAL = "id_user";
    private static final String KEY_TARGET_NAME = "target_name";
    private static final String KEY_TARGET_NUMBER = "target_number";

    // Activities Table Columns names
    private static final String KEY_ID_ACTIVITY = "id";
    private static final String KEY_FK_ID_USER_FOR_ACTIVITY = "id_user";
    private static final String KEY_DURATION_HOURS = "duration_hours";
    private static final String KEY_DURATION_MINUTES = "duration_minutes";
    private static final String KEY_DURATION_SECONDS = "duration_seconds";
    private static final String KEY_DATE = "date";
    private static final String KEY_FEEDBACK = "feedback";
    private static final String KEY_TYPE = "type";
    private static final String KEY_JUST_CREATED = "just_created";
    private static final String KEY_ACTIVITY_TITLE = "title";

    // DistanceActivity Table Columns names
    private static final String KEY_ID_DISTANCE_ACTIVITY = "id";
    private static final String KEY_DISTANCE_KM = "distance_kms";
    private static final String KEY_DISTANCE_MILES = "distance_miles";
    private static final String KEY_FK_ID_ACTIVITY_FOR_DISTANCE = "id_activity"; // This is the same as in ActivityWorkout but it's clearer defined this way


    // ActivityWorkout activity Table Columns names
    private static final String KEY_ID_STEPS = "steps";
    private static final String KEY_STEPS_DATE = "steps_date";
    private static final String KEY_NB_OF_STEPS = "nb_steps";
    private static final String KEY_FK_ID_USER_FOR_STEPS = "id_user";

    // Steps Table Columns names
    private static final String KEY_ID_WORKOUT_ACTIVITY = "id";
    private static final String KEY_REP_TYPE = "repetition_type";
    private static final String KEY_REP_NUMBER = "repetition_number";
    private static final String KEY_FK_ID_ACTIVITY_FOR_WORKOUT = "id_activity";

    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create users table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ( " +
                KEY_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_PSWD + " TEXT NOT NULL, " +
                KEY_USERNAME + " TEXT NOT NULL, " +
                KEY_AGE + " INTEGER, " +
                KEY_HEIGHT + " TEXT, " +
                KEY_WEIGHT + " TEXT, " +
                KEY_EMAIL + " TEXT NOT NULL, " +
                KEY_GENDER + " TEXT, " +
                KEY_METRICS_CHOICE + " TEXT NOT NULL);";

        String CREATE_GOALS_TABLE = "CREATE TABLE " + TABLE_GOALS + " ( " +
                KEY_ID_GOAL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_TARGET_NAME +" TEXT NOT NULL, " +
                KEY_TARGET_NUMBER +" INTEGER NOT NULL, " +
                KEY_FK_ID_USER_FOR_GOAL + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + KEY_FK_ID_USER_FOR_GOAL + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID_USER + "));";


        String CREATE_ACTIVITIES_TABLE = "CREATE TABLE " + TABLE_ACTIVITIES + " ( " +
                KEY_ID_ACTIVITY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_DURATION_HOURS + " INTEGER, " +
                KEY_DURATION_MINUTES + " INTEGER, " +
                KEY_DURATION_SECONDS + " INTEGER, " +
                KEY_DATE + " TEXT NOT NULL, " +
                KEY_FEEDBACK + " TEXT, " +
                KEY_TYPE + " TEXT, " +
                KEY_JUST_CREATED + " INTEGER, " +
                KEY_ACTIVITY_TITLE + " TEXT, " +
                KEY_FK_ID_USER_FOR_ACTIVITY + " INTEGER NOT NULL," +
                "FOREIGN KEY (" + KEY_FK_ID_USER_FOR_ACTIVITY + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID_USER + "));";

        String CREATE_DISTANCE_ACTIVITIES_TABLE = "CREATE TABLE " + TABLE_DISTANCE_ACTIVITIES + " ( " +
                KEY_ID_DISTANCE_ACTIVITY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_DISTANCE_KM + " TEXT, " +
                KEY_DISTANCE_MILES + " TEXT, " +
                KEY_FK_ID_ACTIVITY_FOR_DISTANCE + " INTEGER NOT NULL," +
                "FOREIGN KEY (" + KEY_FK_ID_ACTIVITY_FOR_DISTANCE + ") REFERENCES " + TABLE_ACTIVITIES + "(" + KEY_ID_ACTIVITY + "));";

        String CREATE_WORKOUT_ACTIVITIES_TABLE = "CREATE TABLE " + TABLE_WORKOUT_ACTIVITIES + " ( " +
                KEY_ID_WORKOUT_ACTIVITY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_REP_TYPE + " TEXT, " +
                KEY_REP_NUMBER + " NUMBER, " +
                KEY_FK_ID_ACTIVITY_FOR_WORKOUT + " INTEGER NOT NULL," +
                "FOREIGN KEY (" + KEY_FK_ID_ACTIVITY_FOR_WORKOUT + ") REFERENCES " + TABLE_ACTIVITIES + "(" + KEY_ID_ACTIVITY + "));";

        String CREATE_STEPS_TABLE = "CREATE TABLE " + TABLE_STEPS + " ( " +
                KEY_ID_STEPS + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STEPS_DATE +" TEXT NOT NULL, " +
                KEY_NB_OF_STEPS +" INTEGER NOT NULL, " +
                KEY_FK_ID_USER_FOR_STEPS + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + KEY_FK_ID_USER_FOR_STEPS + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID_USER + "));";

        // create users table
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_GOALS_TABLE);
        db.execSQL(CREATE_ACTIVITIES_TABLE);
        db.execSQL(CREATE_DISTANCE_ACTIVITIES_TABLE);
        db.execSQL(CREATE_WORKOUT_ACTIVITIES_TABLE);
        db.execSQL(CREATE_STEPS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISTANCE_ACTIVITIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT_ACTIVITIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STEPS);

        // create fresh tables
        this.onCreate(db);
    }

    // ------------ CRUD on USER ------------- //
    // Add a single user
    public void addUser(User user) {
        Log.d("adding user", user.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put(KEY_ID_USER, user.getId()); // get id
        values.put(KEY_PSWD, user.getPassword()); // get password
        values.put(KEY_USERNAME, user.getUsername()); // get username
        values.put(KEY_AGE, user.getAge()); // get age
        values.put(KEY_HEIGHT, user.getHeight()); // get height
        values.put(KEY_WEIGHT, user.getWeight()); // get weight
        values.put(KEY_EMAIL, user.getEmail()); // get email
        values.put(KEY_GENDER, user.getGender()); // get gender
        values.put(KEY_METRICS_CHOICE, user.getMetrics()); // get metrics choice

        // 3. insert
        db.insert(TABLE_USERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    // Get a single user
    public User getUser(int id) {
        User retrievedUser = new User();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_ID_USER + " = " + id + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedUser.setId(Integer.parseInt(cursor.getString(0)));
            retrievedUser.setPassword(cursor.getString(1));
            retrievedUser.setUsername(cursor.getString(2));
            retrievedUser.setAge(Integer.parseInt(cursor.getString(3)));
            retrievedUser.setHeight(cursor.getString(4));
            retrievedUser.setWeight(cursor.getString(5));
            retrievedUser.setEmail(cursor.getString(6));
            retrievedUser.setGender(cursor.getString(7));
            retrievedUser.setMetrics(Integer.parseInt(cursor.getString(8)));
        }
        Log.d("Retrieved user", retrievedUser.toString());
        return retrievedUser; // return the user
    }

    // Updating single user
    public int updateUser(User user, String newPassword, String newUsername, int newAge, String newHeight, String newWeight, String newEmail, String newGender, String newMetricsChoice) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_PSWD, newPassword); // password
        values.put(KEY_USERNAME, newUsername); // username
        values.put(KEY_AGE, newAge); // age
        values.put(KEY_HEIGHT, newHeight); // height
        values.put(KEY_WEIGHT, newWeight); // weight
        values.put(KEY_EMAIL, newEmail); // email
        values.put(KEY_GENDER, newGender); // gender
        values.put(KEY_METRICS_CHOICE, newMetricsChoice); // metrics

        // 3. updating row
        int i = db.update(TABLE_USERS, //table
                values, // column/value
                KEY_ID_USER + " = ?", // selections
                new String[]{String.valueOf(user.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating user ", user.toString());
        return i;

    }

    // Deleting single user
    public void deleteUser(User user) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_USERS,
                KEY_ID_USER + " = ?",
                new String[]{String.valueOf(user.getId())});

        // 3. close
        db.close();

        Log.d("deleting user ", user.toString());
    }

    // ------------ CRUD on GOAL ------------- //
    // Add a single goal
    public void addGoal(Goal goal) {
        Log.d("adding goal", goal.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put(KEY_ID_GOAL, goal.getId()); // get id
        values.put(KEY_FK_ID_USER_FOR_GOAL, goal.getIdUser()); // get id user
        values.put(KEY_TARGET_NAME, goal.getTargetName()); // get target name
        values.put(KEY_TARGET_NUMBER, goal.getTargetNumber()); // get target number

        // 3. insert
        db.insert(TABLE_ACTIVITIES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    // Get a single goal
    public Goal getGoal(int id) {
        Goal retrievedGoal = new Goal();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_GOALS + " WHERE " + KEY_ID_GOAL + " = " + id + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedGoal.setId(Integer.parseInt(cursor.getString(0)));
            retrievedGoal.setIdUser(Integer.parseInt(cursor.getString(1)));
            retrievedGoal.setTargetName(cursor.getString(2));
            retrievedGoal.setTargetNumber(Integer.parseInt(cursor.getString(3)));
        }
        Log.d("Retrieved goal", retrievedGoal.toString());
        return retrievedGoal; // return the activity
    }

    // Updating single goal
    public int updateGoal(Goal goal, String newTargetName, int newTargetNumber) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("targetName", newTargetName); // target name
        values.put("targetNumber", newTargetNumber); // target number

        // 3. updating row
        int i = db.update(TABLE_GOALS, //table
                values, // column/value
                KEY_ID_GOAL + " = ?", // selections
                new String[]{String.valueOf(goal.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating goal ", goal.toString());
        return i;

    }

    // Deleting single goal
    public void deleteGoal(Goal goal) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_GOALS,
                KEY_ID_GOAL + " = ?",
                new String[]{String.valueOf(goal.getId())});

        // 3. close
        db.close();

        Log.d("deleting goal ", goal.toString());
    }


    // ------------ CRUD on ACTIVITY ------------- //
    // Add a single activity
    public void addActivity(ActivitySport activity) {
        Log.d("adding activity", activity.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put(KEY_ID_ACTIVITY, activity.getId()); // get id
        values.put(KEY_DURATION_HOURS, activity.getDurationHours()); // get duration
        values.put(KEY_DURATION_MINUTES, activity.getDurationMinutes()); // get duration
        values.put(KEY_DURATION_SECONDS, activity.getDurationSeconds()); // get duration
        values.put(KEY_DATE, activity.getDate()); // get date
        values.put(KEY_FEEDBACK, activity.getFeedback()); // get feedback
        values.put(KEY_TYPE, activity.getType()); // get type
        values.put(KEY_JUST_CREATED, activity.getJustCreated()); // get just created boolean
        values.put(KEY_ACTIVITY_TITLE, activity.getTitle()); // get title
        values.put(KEY_FK_ID_USER_FOR_ACTIVITY, activity.getIdUser()); // get id user

        // 3. insert
        db.insert(TABLE_ACTIVITIES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    // Get a single activity
    public ActivitySport getActivity(int id) {
        ActivitySport retrievedActivity = new ActivitySport();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_ID_ACTIVITY + " = " + id + ";";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
            retrievedActivity.setDurationHours(Integer.parseInt(cursor.getString(1)));
            retrievedActivity.setDurationMinutes(Integer.parseInt(cursor.getString(2)));
            retrievedActivity.setDurationSeconds(Integer.parseInt(cursor.getString(3)));
            retrievedActivity.setDate(cursor.getString(4));
            retrievedActivity.setFeedback(cursor.getString(5));
            retrievedActivity.setType(cursor.getString(6));
            retrievedActivity.setJustCreated(Integer.parseInt(cursor.getString(7)));
            retrievedActivity.setTitle(cursor.getString(8));
            retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(9)));
        }
        Log.d("Retrieved activity", retrievedActivity.toString());
        return retrievedActivity; // return the activity
    }

    // Updating single activity
    public int updateActivity(ActivitySport activity, int newDurationHours, int newDurationMinutes, int newDurationSeconds, String newDate, String newFeedback, String newType, int newJustCreated, String newTitle) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_DURATION_HOURS, newDurationHours); // duration
        values.put(KEY_DURATION_MINUTES, newDurationMinutes); // duration
        values.put(KEY_DURATION_SECONDS, newDurationSeconds); // duration
        values.put(KEY_DATE, newDate); // date
        values.put(KEY_FEEDBACK, newFeedback); // feedback
        values.put(KEY_TYPE, newType); // type
        values.put(KEY_JUST_CREATED, newJustCreated); // justecreatd
        values.put(KEY_ACTIVITY_TITLE, newTitle); // justecreatd

        // 3. updating row
        int i = db.update(TABLE_ACTIVITIES, //table
                values, // column/value
                KEY_ID_ACTIVITY + " = ?", // selections
                new String[]{String.valueOf(activity.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating activity ", activity.toString());
        return i;

    }

    // Deleting single activity
    public void deleteActivity(ActivitySport activity) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ACTIVITIES,
                KEY_ID_ACTIVITY + " = ?",
                new String[]{String.valueOf(activity.getId())});

        // 3. close
        db.close();

        Log.d("deleting activity ", activity.toString());
    }

    // ------------ CRUD on DISTANCE ACTIVITY ------------- //
    // Add a single distance activity
    public void addActivityDistance(ActivityDistance activityDistance) {
        Log.d("adding distanceactivity", activityDistance.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put(KEY_ID_DISTANCE_ACTIVITY, activityDistance.getId()); // get id
        values.put(KEY_DISTANCE_KM, activityDistance.getDistanceKms()); // get distance kms
        values.put(KEY_DISTANCE_MILES, activityDistance.getDistanceMiles()); // get distance miles
        values.put(KEY_FK_ID_ACTIVITY_FOR_DISTANCE, activityDistance.getIdActivity()); // get id activity

        // 3. insert
        db.insert(TABLE_DISTANCE_ACTIVITIES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    // Get a single distance activity
    public ActivityDistance getActivityDistance(int id) {
        ActivityDistance retrievedDistanceActivity = new ActivityDistance();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_DISTANCE_ACTIVITIES + " WHERE " + KEY_ID_DISTANCE_ACTIVITY + " = " + id + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedDistanceActivity.setId(Integer.parseInt(cursor.getString(0)));
            retrievedDistanceActivity.setDistanceKms(cursor.getString(1));
            retrievedDistanceActivity.setDistanceMiles(cursor.getString(2));
            retrievedDistanceActivity.setIdActivity(Integer.parseInt(cursor.getString(3)));
        }
        Log.d("Retrieved distactivity", retrievedDistanceActivity.toString());
        return retrievedDistanceActivity; // return the activity
    }

    // Updating single distance activity
    public int updateDistanceActivity(ActivityDistance activityDistance, String newDistanceMiles, String newDistanceKms) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_DISTANCE_KM, newDistanceKms); // distance kms
        values.put(KEY_DISTANCE_MILES, newDistanceMiles); // distance miles


        // 3. updating row
        int i = db.update(TABLE_DISTANCE_ACTIVITIES, //table
                values, // column/value
                KEY_ID_DISTANCE_ACTIVITY + " = ?", // selections
                new String[]{String.valueOf(activityDistance.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating act dist ", activityDistance.toString());
        return i;

    }

    // Deleting single distance activity
    public void deleteDistanceActivity(ActivityDistance activityDistance) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_DISTANCE_ACTIVITIES,
                KEY_ID_DISTANCE_ACTIVITY + " = ?",
                new String[]{String.valueOf(activityDistance.getId())});

        // 3. close
        db.close();

        Log.d("deleting act distance ", activityDistance.toString());
    }

    // ------------ CRUD on WORKOUT ACTIVITY ------------- //
    // Add a single workout activity
    public void addActivityWorkout(ActivityWorkout activityWorkout) {
        Log.d("adding Workoutactivity", activityWorkout.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put(KEY_ID_DISTANCE_ACTIVITY, activityWorkout.getId()); // get id
        values.put(KEY_REP_TYPE, activityWorkout.getTypeOfRep()); // get repetition type
        values.put(KEY_REP_NUMBER, activityWorkout.getNbOfRep()); // get repetition number
        values.put(KEY_FK_ID_ACTIVITY_FOR_WORKOUT, activityWorkout.getIdActivity()); // get id activity

        // 3. insert
        db.insert(TABLE_WORKOUT_ACTIVITIES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    // Get a single workout activity
    public ActivityWorkout getActivityWorkout(int id) {
        ActivityWorkout retrievedWorkoutActivity = new ActivityWorkout();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_WORKOUT_ACTIVITIES + " WHERE " + KEY_ID_WORKOUT_ACTIVITY + " = " + id + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedWorkoutActivity.setId(Integer.parseInt(cursor.getString(0)));
            retrievedWorkoutActivity.setTypeOfRep(cursor.getString(1));
            retrievedWorkoutActivity.setNbOfRep(Integer.parseInt(cursor.getString(2)));
            retrievedWorkoutActivity.setIdActivity(Integer.parseInt(cursor.getString(3)));
        }
        Log.d("Retrieved wkoutactivity", retrievedWorkoutActivity.toString());
        return retrievedWorkoutActivity; // return the activity
    }

    // Updating single workout activity
    public int updateWorkoutActivity(ActivityWorkout activityWorkout, String newTypeOfReps, int newNbOfReps) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_REP_TYPE, newTypeOfReps); // repetition type
        values.put(KEY_REP_NUMBER, newNbOfReps); // repetition number

        // 3. updating row
        int i = db.update(TABLE_DISTANCE_ACTIVITIES, //table
                values, // column/value
                KEY_ID_WORKOUT_ACTIVITY + " = ?", // selections
                new String[]{String.valueOf(activityWorkout.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating wkout dist ", activityWorkout.toString());
        return i;

    }

    // Deleting single workout activity
    public void deleteWorkoutActivity(int id) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_WORKOUT_ACTIVITIES,
                KEY_ID_WORKOUT_ACTIVITY + " = ?",
                new String[]{String.valueOf(id)});

        // 3. close
        db.close();
        Log.d("del wkout act with id ", String.valueOf(id));

    }

    // ------------ CRUD on STEPS ACTIVITY ------------- //
    // Add a single step
    public void addSteps(Step step) {
        Log.d("adding step", step.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_STEPS_DATE, step.getStepsDate()); // get date
        values.put(KEY_NB_OF_STEPS, step.getNbOfSteps()); // get number of steps
        values.put(KEY_FK_ID_USER_FOR_STEPS, step.getIdUser()); // get id user

        // 3. insert
        db.insert(TABLE_STEPS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    // Get a single step
    public Step getStep(int id) {
        Step step = new Step();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_STEPS + " WHERE " + KEY_ID_STEPS + " = " + id + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            step.setId(Integer.parseInt(cursor.getString(0)));
            step.setStepsDate(cursor.getString(1));
            step.setNbOfSteps(Integer.parseInt(cursor.getString(2)));
            step.setIdUser(Integer.parseInt(cursor.getString(3)));
        }
        Log.d("Retrieved steps", step.toString());
        return step; // return the step
    }

    // Updating single step
    public int updateStep(Step step, String newStepsDate, int newNbOfSteps) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_STEPS_DATE, newStepsDate); // steps date
        values.put(KEY_NB_OF_STEPS, newNbOfSteps); // steps nb

        // 3. updating row
        int i = db.update(TABLE_STEPS, //table
                values, // column/value
                KEY_ID_STEPS + " = ?", // selections
                new String[]{String.valueOf(step.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating step ", step.toString());
        return i;

    }

    // Deleting single step
    public void deleteStep(int id) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_STEPS,
                KEY_ID_STEPS + " = ?",
                new String[]{String.valueOf(id)});

        // 3. close
        db.close();
        Log.d("del steps with id ", String.valueOf(id));

    }


    // ------------ SPECIAL QUERIES ON USER ---------------- //
    public User getUserByEmail(String email) {
        User user = new User();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = \'" + email + "\';";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row and add title to list
        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setPassword(cursor.getString(1));
            user.setUsername(cursor.getString(2));
            user.setAge(Integer.parseInt(cursor.getString(3)));
            user.setHeight(cursor.getString(4));
            user.setWeight(cursor.getString(5));
            user.setEmail(cursor.getString(6));
            user.setGender(cursor.getString(7));
            user.setMetrics(Integer.parseInt(cursor.getString(8)));
            Log.d("Retrieved user", user.toString());
            return user; // return the user
        } else{
            return null;
        }


    }

    public void createUser(User user){
        Log.d("adding user", user.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_PSWD, user.getPassword()); // get password
        values.put(KEY_USERNAME, user.getUsername()); // get username
        values.put(KEY_EMAIL, user.getEmail()); // get email
        values.put(KEY_METRICS_CHOICE, user.getMetrics()); // get metrics choice

        // 3. insert
        db.insert(TABLE_USERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<User>();

        User retrievedUser = new User();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_USERS + ";";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedUser.setId(Integer.parseInt(cursor.getString(0)));
            retrievedUser.setPassword(cursor.getString(1));
            retrievedUser.setUsername(cursor.getString(2));
            retrievedUser.setAge(Integer.parseInt(cursor.getString(3)));
            retrievedUser.setHeight(cursor.getString(4));
            retrievedUser.setWeight(cursor.getString(5));
            retrievedUser.setEmail(cursor.getString(6));
            retrievedUser.setGender(cursor.getString(7));
            retrievedUser.setMetrics(Integer.parseInt(cursor.getString(8)));
            users.add(retrievedUser);
        }
        Log.d("getAllUsers", users.toString());

        return users; // return activities by user
    }


    // ------------ SPECIAL QUERIES ON ACTIVITY ---------------- //
    public ActivitySport getActivityJustCreated() {
        ActivitySport retrievedActivity = new ActivitySport();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_JUST_CREATED + " = 1;";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
            retrievedActivity.setDurationHours(Integer.parseInt(cursor.getString(1)));
            retrievedActivity.setDurationMinutes(Integer.parseInt(cursor.getString(2)));
            retrievedActivity.setDurationSeconds(Integer.parseInt(cursor.getString(3)));
            retrievedActivity.setDate(cursor.getString(4));
            retrievedActivity.setFeedback(cursor.getString(5));
            retrievedActivity.setType(cursor.getString(6));
            retrievedActivity.setJustCreated(Integer.parseInt(cursor.getString(7)));
            retrievedActivity.setTitle(cursor.getString(8));
            retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(9)));
        }
        Log.d("Retrieved activity", retrievedActivity.toString());
        return retrievedActivity; // return the activity
    }

    public List<ActivitySport> getAllByUser(int idUser) {
        List<ActivitySport> activities = new LinkedList<ActivitySport>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_FK_ID_USER_FOR_ACTIVITY + " = " + idUser + ";";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ActivitySport retrievedActivity = null;

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            do {
                retrievedActivity = new ActivitySport();
                retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
                retrievedActivity.setDurationHours(Integer.parseInt(cursor.getString(1)));
                retrievedActivity.setDurationMinutes(Integer.parseInt(cursor.getString(2)));
                retrievedActivity.setDurationSeconds(Integer.parseInt(cursor.getString(3)));
                retrievedActivity.setDate(cursor.getString(4));
                retrievedActivity.setFeedback(cursor.getString(5));
                retrievedActivity.setType(cursor.getString(6));
                retrievedActivity.setJustCreated(Integer.parseInt(cursor.getString(7)));
                retrievedActivity.setTitle(cursor.getString(8));
                retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(9)));
                // Add activity to activities
                activities.add(retrievedActivity);
            } while (cursor.moveToNext());
        }

        Log.d("getAllActByUser", activities.toString());

        return activities; // return activities by user
    }

    public List<ActivitySport> getEachDateOfActivity(int idUser) {
        List<ActivitySport> activities = new LinkedList<ActivitySport>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_FK_ID_USER_FOR_ACTIVITY + " = " + idUser + " ORDER BY " + KEY_DATE + " DESC;";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ActivitySport retrievedActivity = null;

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            do {
                retrievedActivity = new ActivitySport();
                retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
                retrievedActivity.setDurationHours(Integer.parseInt(cursor.getString(1)));
                retrievedActivity.setDurationMinutes(Integer.parseInt(cursor.getString(2)));
                retrievedActivity.setDurationSeconds(Integer.parseInt(cursor.getString(3)));
                retrievedActivity.setDate(cursor.getString(4));
                retrievedActivity.setFeedback(cursor.getString(5));
                retrievedActivity.setType(cursor.getString(6));
                retrievedActivity.setJustCreated(Integer.parseInt(cursor.getString(7)));
                retrievedActivity.setTitle(cursor.getString(8));
                retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(9)));
                // Add activity to activities
                activities.add(retrievedActivity);
            } while (cursor.moveToNext());
        }

        Log.d("getEachActbyDate", activities.toString());

        return activities; // return activities by user
    }

    public List<ActivitySport> getAllByType(String type) {
        List<ActivitySport> activities = new LinkedList<ActivitySport>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_TYPE + " = " + type + ";";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ActivitySport retrievedActivity = null;

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            do {
                retrievedActivity = new ActivitySport();
                retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
                retrievedActivity.setDurationHours(Integer.parseInt(cursor.getString(1)));
                retrievedActivity.setDurationMinutes(Integer.parseInt(cursor.getString(2)));
                retrievedActivity.setDurationSeconds(Integer.parseInt(cursor.getString(3)));
                retrievedActivity.setDate(cursor.getString(4));
                retrievedActivity.setFeedback(cursor.getString(5));
                retrievedActivity.setType(cursor.getString(6));
                retrievedActivity.setJustCreated(Integer.parseInt(cursor.getString(7)));
                retrievedActivity.setTitle(cursor.getString(8));
                retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(9)));
                // Add activity to activities
                activities.add(retrievedActivity);
            } while (cursor.moveToNext());
        }

        Log.d("getAllActByUser", activities.toString());
        return activities; // return activities by type
    }

    public List<ActivitySport> getAllByDate(String date) {
        List<ActivitySport> activities = new LinkedList<ActivitySport>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_DATE + " = " + date + ";";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ActivitySport retrievedActivity = null;

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            do {
                retrievedActivity = new ActivitySport();
                retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
                retrievedActivity.setDurationHours(Integer.parseInt(cursor.getString(1)));
                retrievedActivity.setDurationMinutes(Integer.parseInt(cursor.getString(2)));
                retrievedActivity.setDurationSeconds(Integer.parseInt(cursor.getString(3)));
                retrievedActivity.setDate(cursor.getString(3));
                retrievedActivity.setFeedback(cursor.getString(4));
                retrievedActivity.setType(cursor.getString(5));
                retrievedActivity.setJustCreated(Integer.parseInt(cursor.getString(6)));
                retrievedActivity.setTitle(cursor.getString(7));
                retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(8)));
                // Add activity to activities
                activities.add(retrievedActivity);
            } while (cursor.moveToNext());
        }

        Log.d("getAllActByUser", activities.toString());
        return activities; // return activities by date
    }

    public boolean isDistanceAtDate(String date){
        boolean result = false;
        // 1. build the query
        String dist = "Distance";
        String query = "SELECT DISTINCT " + KEY_TYPE + " FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_DATE+ " = " + date + " AND " + KEY_TYPE + "=\'" + dist + "\';";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ActivitySport retrievedActivity = null;

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedActivity = new ActivitySport();
            retrievedActivity.setType(cursor.getString(0));
            if(Objects.equals(retrievedActivity.getType(), dist)){
                result = true;
            }
        }
        //Log.d("isDistanceAtDate", String.valueOf(result));
        return result; // return result
    }

    public boolean isWorkoutAtDate(String date){
        boolean result = false;
        // 1. build the query
        String wkout = "Workout";
        String query = "SELECT DISTINCT " + KEY_TYPE + " FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_DATE+ " = " + date + " AND " + KEY_TYPE + "=\'" + wkout + "\';";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ActivitySport retrievedActivity = null;

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedActivity = new ActivitySport();
            retrievedActivity.setType(cursor.getString(0));
            if(Objects.equals(retrievedActivity.getType(), wkout)){
                result = true;
            }
        }


        //Log.d("isWorkoutAtDate", String.valueOf(result));
        return result; // return result
    }

    // ------------ SPECIAL QUERIES ON GOAL ---------------- //
    public List<Goal> getAllGoalsByUser(User user) {
        List<Goal> userGoals = new LinkedList<Goal>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_GOALS + " WHERE " + KEY_FK_ID_USER_FOR_GOAL + " = " + user.getId() + ";";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build goal and add it to list
        Goal goal = null;
        if (cursor.moveToFirst()) {
            do {
                goal = new Goal();
                goal.setId(Integer.parseInt(cursor.getString(0)));
                goal.setIdUser(Integer.parseInt(cursor.getString(1)));
                goal.setTargetName(cursor.getString(2));
                goal.setTargetNumber(Integer.parseInt(cursor.getString(3)));

                // Add goal to user's goals
                userGoals.add(goal);
            } while (cursor.moveToNext());
        }

        Log.d("getAllGoalsByUser", userGoals.toString());

        return userGoals; // return user's goals
    }

    // ------------ SPECIAL QUERIES ON WORKOUT ACTIVITY ---------------- //
    public List<ActivityWorkout> getWorkoutsByActivity(int idActivity) {
        List<ActivityWorkout> workouts = new LinkedList<ActivityWorkout>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_WORKOUT_ACTIVITIES + " WHERE " + KEY_FK_ID_ACTIVITY_FOR_WORKOUT + " = " + idActivity + ";";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build workout and add it to list
        ActivityWorkout workout = null;
        if (cursor.moveToFirst()) {
            do {
                workout = new ActivityWorkout();
                workout.setId(Integer.parseInt(cursor.getString(0)));
                workout.setTypeOfRep(cursor.getString(1));
                workout.setNbOfRep(Integer.parseInt(cursor.getString(2)));
                workout.setIdActivity(Integer.parseInt(cursor.getString(3)));

                // Add goal to workouts's activity
                workouts.add(workout);
            } while (cursor.moveToNext());
        }

        Log.d("getWorkoutsByActivity", workouts.toString());

        return workouts; // return workouts's activity
    }

    // ------------ SPECIAL QUERIES ON DISTANCE ACTIVITY---------------- //
    public List<ActivityDistance> getDistancesByActivity(int idActivity) {
        List<ActivityDistance> distances = new LinkedList<ActivityDistance>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_DISTANCE_ACTIVITIES + " WHERE " + KEY_FK_ID_ACTIVITY_FOR_DISTANCE + " = " + idActivity +";";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build distance and add it to list
        ActivityDistance distance = null;
        if (cursor.moveToFirst()) {
            do {
                distance = new ActivityDistance();
                distance.setId(Integer.parseInt(cursor.getString(0)));
                distance.setDistanceMiles(cursor.getString(1));
                distance.setDistanceKms(cursor.getString(2));
                distance.setIdActivity(Integer.parseInt(cursor.getString(3)));

                // Add distance to distances's activity
                distances.add(distance);
            } while (cursor.moveToNext());
        }

        Log.d("getDistancesByActivity", distances.toString());

        return distances; // return distances's activity
    }

    // ------------ SPECIAL QUERIES ON STEPS ----------------------------//
    public List<Step> getAllStepsByUser(int idUser) {
        List<Step> steps = new LinkedList<Step>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_STEPS + " WHERE " + KEY_FK_ID_USER_FOR_STEPS + " = " + idUser + ";";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Step retrievedStep = null;

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            do {
                retrievedStep = new Step();
                retrievedStep.setId(Integer.parseInt(cursor.getString(0)));
                retrievedStep.setStepsDate(cursor.getString(1));
                retrievedStep.setNbOfSteps(Integer.parseInt(cursor.getString(2)));
                retrievedStep.setIdUser(Integer.parseInt(cursor.getString(3)));

                // Add activity to activities
                steps.add(retrievedStep);
            } while (cursor.moveToNext());
        }

        Log.d("getAllStepsByUser", steps.toString());

        return steps; // return activities by user
    }

}

