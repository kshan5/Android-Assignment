package com.sapient.mymusic;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    EditText emailId, password;
    public static TextView creatAccount, errorView, errorView1;
    Button loginBtn;


    LoginAndRegisterHelper loginAndRegisterHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        loginAndRegisterHelper = new LoginAndRegisterHelper(DatabaseHelper.getInstance(this.getApplicationContext()));
        emailId = (EditText)findViewById(R.id.emailId);
        password = (EditText)findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.signIn);
        creatAccount = (TextView)findViewById(R.id.creaateAccount);
        errorView = (TextView)findViewById(R.id.errorView);
        errorView1 = (TextView)findViewById(R.id.errorView1);

        creatAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                emailId.setText("");
                password.setText("");
                Intent intent = new Intent(getBaseContext(), RegisterPage.class);
                startActivity(intent);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (emailId.getText().length() == 0) {
                    emailId.requestFocus();
                    emailId.setError("Email should not be empty!");
                    emailId.setFocusable(true);
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailId.getText()).matches()) {
                    emailId.setError("Enter valid address!");
                    emailId.requestFocus();
                    emailId.setFocusable(true);
                    return;
                }

                if(password.getText().length() ==0){

                    password.setFocusable(true);
                    password.requestFocus();
                    password.setError("Password should not be empty!");
                    return;
                }
                boolean success =  loginAndRegisterHelper.loginUser(emailId.getText().toString(), password.getText().toString());

                if(success)
                {

                    Toast.makeText(LoginActivity.this, "Successfully Login!!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), AlubmListView.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }



    /*public void loginToMainPage(View view){

        loginBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {


                   if (emailId.getText().length() == 0) {
                       emailId.requestFocus();
                       emailId.setError("Email should not be empty!");
                       emailId.setFocusable(true);
                       return;
                   } else if (!Patterns.EMAIL_ADDRESS.matcher(emailId.getText()).matches()) {
                       emailId.setError("Enter valid address!");
                       emailId.requestFocus();
                       emailId.setFocusable(true);
                       return;
                   }

                if(password.getText().length() ==0){

                    password.setFocusable(true);
                    password.requestFocus();
                    password.setError("Password should not be empty!");
                    return;
                }
                  boolean success =  loginAndRegisterHelper.loginUser(emailId.getText().toString(), password.getText().toString());

                if(success)
                {
                    Toast.makeText(LoginActivity.this, "Successfully Login!!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), AlubmListView.class);
                    startActivity(intent);
                }


            }
        });
    }
*/


    public boolean validation() {


        return true;

    }


}
