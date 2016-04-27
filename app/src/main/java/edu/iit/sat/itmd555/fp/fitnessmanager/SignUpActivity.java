package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.content.Intent;

import java.util.Objects;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.User;

//public class SignUpActivity extends ActivitySport
//{
//    EditText etUserName,editTextPassword,editTextConfirmPassword;
//    Button btnCreateAccount;
//
//    LoginDataBaseAdapter loginDataBaseAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up_screen);
//
//        // get Instance  of Database Adapter
//        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
//        loginDataBaseAdapter=loginDataBaseAdapter.open();
//
//        // Get Refferences of Views
//        etUserName=(EditText)findViewById(R.id.etUserName);
//        editTextPassword=(EditText)findViewById(R.id.etPass);
//        editTextConfirmPassword=(EditText)findViewById(R.id.etcPass);
//
//        btnCreateAccount=(Button)findViewById(R.id.btnSignUp);
//        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//                String userName=etUserName.getText().toString();
//                String password=editTextPassword.getText().toString();
//                String confirmPassword=editTextConfirmPassword.getText().toString();
//
//                // check if any of the fields are vaccant
//                if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
//                {
//                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                // check if both password matches
//                if(!password.equals(confirmPassword))
//                {
//                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                else
//                {
//                    // Save the Data in Database
//                    loginDataBaseAdapter.insertEntry(userName, password);
//                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
//    @Override
//    protected void onDestroy() {
//        // TODO Auto-generated method stub
//        super.onDestroy();
//
//        loginDataBaseAdapter.close();
//    }
//}


public class SignUpActivity extends Activity implements OnClickListener {
    private SqlHelper db;
    private Button btnSignUp;
    private EditText Email, username, password, confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        db = new SqlHelper(this);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        Email = (EditText) findViewById(R.id.etEmail);
        username = (EditText) findViewById(R.id.etUserName);
        password = (EditText) findViewById(R.id.etPass);
        confirmPass = (EditText) findViewById(R.id.etcPass);
        btnSignUp.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        User newUser = new User();
        db.getAllUsers();
        Log.d("Email", Email.getText().toString());
        if(db.getUserByEmail(Email.getText().toString())==null){
            Log.d("Email is free", "yes !");
            if(Objects.equals(password.getText().toString(), confirmPass.getText().toString())){
                Log.d("password is ok", "yes !");
                Log.d("username", username.getText().toString());
                Log.d("password", password.getText().toString());
                newUser.setEmail(Email.getText().toString());
                newUser.setPassword(password.getText().toString());
                newUser.setUsername(username.getText().toString());
                newUser.setMetrics(0);
                db.addUser(newUser);
                Intent i = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(i);
            } else{
                Toast.makeText(SignUpActivity.this, "Passwords don't match !", Toast.LENGTH_LONG).show();
            }

        }else{
            Log.d("Email is free", "no !");
            Toast.makeText(SignUpActivity.this, "Email already used!", Toast.LENGTH_LONG).show();
        }
        Log.d("user", newUser.toString())
;    }
}