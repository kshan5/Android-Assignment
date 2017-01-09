package com.sapient.mymusic;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText firstName, lastName, email, mobile, password;

    RadioGroup radioAgeGroup;
    RadioButton radioButton;

    Spinner ageSelectorSpinner;

    CheckBox ckBoxTa, ckBoxHi, ckBoxEn;

    LoginAndRegisterHelper loginAndRegisterHelper;
    Button regButton;

    boolean validator = false;
    TextView error;
    StringBuilder stringBuilder;

    String selectedItemText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        initializeInputFields();

        ageSelectorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage();
                if(validation()){
                    int selectedId = radioAgeGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    //get language selected by the user
                    getLang();
                    loginAndRegisterHelper.registerUserData(reqParameters());
                    clearInputValues();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        });

        //validate enter user is already register user
        mobile.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int flag = loginAndRegisterHelper.checkUserAvailabilty(email.getText().toString());
                if(flag>0)
                {
                    email.requestFocus();
                    email.setError("User already available");
                    email.setFocusable(true);
                }
            }
        });

    }

    // get language selected by the user
        private void getLang() {

        if(ckBoxEn.isChecked()){
            stringBuilder.append("English");
        }
            if(ckBoxTa.isChecked()){
            stringBuilder.append(",").append("Tamil");
        }
            if(ckBoxTa.isChecked()){
            stringBuilder.append(",").append("Hindi");
        }
    }

    // initialize view elements
    public void initializeInputFields(){

        stringBuilder = new StringBuilder();

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastname);

        email = (EditText)findViewById(R.id.emailId);
        mobile = (EditText)findViewById(R.id.mobilenumber);
        password = (EditText)findViewById(R.id.password);
        error = (TextView)findViewById(R.id.errorView);
        radioAgeGroup = (RadioGroup)findViewById(R.id.genderGroup);

        ckBoxEn = (CheckBox)findViewById(R.id.ln_en);
        ckBoxTa = (CheckBox)findViewById(R.id.ln_ta);
        ckBoxHi = (CheckBox)findViewById(R.id.ln_hi);

        regButton = (Button)findViewById(R.id.register);

        ageSelectorSpinner = (Spinner)findViewById(R.id.age_spinner);
        loginAndRegisterHelper = new LoginAndRegisterHelper(DatabaseHelper.getInstance(this.getApplicationContext()));
    }

    public void changeBackground(View view){
        int initColor = view.getSolidColor();
        int finalColor = Color.BLUE;
    }


    public boolean addImage(){

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", 1002);
        contentValues.put("ALBUM_NAME", "Nothing But The");
        contentValues.put("ARTIST", "David Gustin");
       // contentValues.put("ALBUM_IMAGE", imgArrayValue);
        contentValues.put("ALBUM_RELEASE_DATE", "September 03, 2001");
        contentValues.put("PRODUCER", "Russ Titelman");
        contentValues.put("GENRES", "Rock music, Blues rock, Country blues, Folk music, Pop rock, Folk rock");
        contentValues.put("SONGS", "I Will be with You");
        contentValues.put("RATING", "3.0");
        contentValues.put("AWARDS", "None");

        boolean result = loginAndRegisterHelper.storeAlbums(contentValues);
        return true;
    }


    public static byte[] getCompressFormat(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, outputStream);
        return outputStream.toByteArray();
    }
    /*

    // First Click not working

    public void registerCustomer(View view){

        regButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


            if(validation()){
               //   result = databaseHelper.registerUserData(email.getText().toString(), password.getText().toString(), mobile.getText().toString());
                  Toast.makeText(RegisterPage.this, "Data Inserted Successfully..", Toast.LENGTH_LONG).show();
              }else
              {
                  Toast.makeText(RegisterPage.this, "Error", Toast.LENGTH_LONG).show();
              }

            }
        });
        if(result){
            clearInputValues();
            intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }else {
            clearInputValues();
            error = (TextView)findViewById(R.id.errorView);
            error.setTextColor(Color.RED);
            error.setText("User Already Available!!!");
            Toast.makeText(RegisterPage.this, ""+result , Toast.LENGTH_LONG).show();
            }
    }
*/
    // clear all view elements
    public void clearInputValues(){
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        mobile.setText("");
        password.setText("");
    }

    // validate mandatory fields
    public boolean validation() {

        if (firstName.getText().length() == 0) {
            firstName.requestFocus();
            firstName.setError("FirstName should not be empty!");
            firstName.setFocusable(true);
            return validator;
        }

        if (email.getText().length() == 0) {
            email.requestFocus();
            email.setError("Email should not be empty!");
            email.setFocusable(true);
            return validator;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
            email.setError("Enter valid address!");
            email.requestFocus();
            email.setFocusable(true);
            return validator;
        }

        if(mobile.getText().length()==0){
            mobile.setFocusable(true);
            mobile.requestFocus();
            mobile.setError("Mobile should not be empty");
            return validator;
        }else if(mobile.getText().length()<10){
            mobile.setFocusable(true);
            mobile.requestFocus();
            mobile.setError("Please enter valid mobile number");
            return validator;
        }

        if(password.getText().length() ==0){
            password.setFocusable(true);
            password.requestFocus();
            password.setError("Password should not be empty!");
            return validator;
           }
        return true;
    }

// set input values to map for send data to insert function
    public Map reqParameters(){

        Map userInputs = new HashMap();
        userInputs.put("FirstName", firstName.getText().toString());
        userInputs.put("LastName", lastName.getText().toString());
        userInputs.put("Email", email.getText().toString());
        userInputs.put("Mobile", mobile.getText().toString());
        userInputs.put("Password", password.getText().toString());
        userInputs.put("Gender", radioButton.getText().toString());
        userInputs.put("Lang", stringBuilder.toString());
        userInputs.put("AgeGroup", selectedItemText);

        return userInputs;
    }

}
