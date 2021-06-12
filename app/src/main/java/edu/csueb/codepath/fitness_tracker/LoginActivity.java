package edu.csueb.codepath.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;


//    private EditText etfirstName;
//    private EditText etlastname;
//    private EditText etage;
//    private EditText etEmailAddress;
//    private EditText etNewWeight;
//    private EditText etNewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        //ParseUser.logOut();
        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }
        
        etUsername =  findViewById(R.id.etUsername);
        etPassword =  findViewById(R.id.etPassword1);
        btnLogin = findViewById(R.id.btnsubmit);

        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"onClick Login button clicked");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignup.setOnClickListener(view -> {
            //Toast.makeText(LoginActivity.this,"Sign up button clicked", Toast.LENGTH_SHORT).show();
            goSignupActivity();
            Animatoo.animateSwipeLeft(this);
        });
        
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
                //Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goSignupActivity(){
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
        finish();
    }


}