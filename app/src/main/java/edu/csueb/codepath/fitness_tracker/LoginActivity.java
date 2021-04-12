package edu.csueb.codepath.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;


    private EditText etfirstName;
    private EditText etlastname;
    private EditText etage;
    private EditText etEmailAddress;
    private EditText etNewWeight;
    private EditText etNewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }
        
        etUsername =  findViewById(R.id.etUsername);
        etPassword =  findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnsubmit);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"onClick Login button clicked");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        etfirstName = findViewById(R.id.etfirstname);
        etlastname = findViewById(R.id.etlastname);
        etage = findViewById(R.id.etage);
        etNewHeight = findViewById(R.id.etNewHeight);
        etNewWeight = findViewById(R.id.etNewWeight);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to log in user" + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!= null){
                    Log.e(TAG, "Issue with login", e);
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void signup(View view){
        ParseUser user = new ParseUser();
        user.setPassword(etPassword.getText().toString());
        user.setUsername(etUsername.getText().toString());
        user.setEmail(etEmailAddress.getText().toString());
        user.put("firstname", etfirstName.getText().toString());
        user.put("lastname", etlastname.getText().toString());
        user.put("height",etNewHeight.getText().toString());
        user.put("weight",etNewWeight.getText().toString());
        user.put("age", etage.getText().toString());

        /**
         * will also need to take in:
         * email
         * height
         * weight
         * date of birth // to increment age
         * age
         *
         *
         * */


        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e!= null){
                    Toast.makeText(LoginActivity.this, "Sign Up in UNSUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Sign up Failed!");
                }else {
                    Toast.makeText(LoginActivity.this, "Sign up SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    loginUser(etUsername.toString(), etPassword.toString());
                }
            }
        });
    }
}