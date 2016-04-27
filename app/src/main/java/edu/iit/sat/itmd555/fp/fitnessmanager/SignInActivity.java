package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.app.Activity;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;

import java.util.Objects;

public class SignInActivity extends Activity {
    private SqlHelper db;
    private EditText Email, password;
    private CheckBox rmPass;
    private Button btnSignIn;
    private String EmailValue,passwordValue;
    private SharedPreferences sp;
    private TextView link_signup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        db = new SqlHelper(this);
        //去除标题
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //获得实例对象
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        Email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPass);
        rmPass = (CheckBox) findViewById(R.id.rmPass);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        link_signup = (TextView) findViewById(R.id.link_signup);


        //判断记住密码多选框的状态
//        if(sp.getBoolean("ISCHECK", false))
//        {
//            //设置默认是记录密码状态
//            rmPass.setChecked(true);
//            Email.setText(sp.getString("Email", ""));
//            password.setText(sp.getString("PASSWORD", ""));



        //set OnclickListener for move to signup page
        link_signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });

        // set OnclickListener  useremail：li@123.com password：123
        btnSignIn.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                EmailValue = Email.getText().toString();
                passwordValue = password.getText().toString();
                if(Email.getText().length()!=0 || password.getText().length()!=0){
                    if(db.getUserByEmail(EmailValue)!=null){
                        if(Objects.equals(db.getUserByEmail(EmailValue).getPassword(), passwordValue)){
                            Toast.makeText(SignInActivity.this,"SignIn successful!", Toast.LENGTH_SHORT).show();
                            //登录成功和记住密码框为选中状态才保存用户信息
//                    if(rmPass.isChecked())
//                    {
//                        //记住用户名、密码、
//                        Editor editor = sp.edit();
//                        editor.putString("Email", EmailValue);
//                        editor.putString("PASSWORD",passwordValue);
//                        editor.commit();
//                    }
                            //跳转界面

                            Intent i = new Intent(getApplicationContext(), MainTabActivity.class);
                            startActivity(i);
                        } else if(EmailValue.equals("li@123.com")&&passwordValue.equals("123")){
                            Toast.makeText(SignInActivity.this,"SignIn successful!", Toast.LENGTH_SHORT).show();

                            Log.d("1", "yes");
                            Intent i = new Intent(getApplicationContext(), MainTabActivity.class);
                            startActivity(i);
                            Log.d("2", "yes2");
                            //finish();

                        }else{

                            Toast.makeText(SignInActivity.this,"Email or Password wrong! Please try again!", Toast.LENGTH_LONG).show();
                        }

                    }

                }  else{

                    Toast.makeText(SignInActivity.this,"Email or Password empty! Please try again!", Toast.LENGTH_LONG).show();
                }

            }
        });

        //监听记住密码多选框按钮事件
//        rmPass.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//                if (rmPass.isChecked()) {
//
//                    System.out.println("checked remember password");
//                    sp.edit().putBoolean("ISCHECK", true).commit();
//
//                }else {
//
//                    System.out.println("unchecked remember password");
//                    sp.edit().putBoolean("ISCHECK", false).commit();
//
//                }
//
//            }
//        });
    }
}

