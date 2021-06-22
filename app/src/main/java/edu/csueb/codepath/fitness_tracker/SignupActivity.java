package edu.csueb.codepath.fitness_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    public static final String TAG = "Signup Activity";

    private EditText firstName;
    private EditText lastName;
    private EditText emailAddress;
    private EditText weight;
    private EditText height;
    private EditText age;

    private EditText username;
    private EditText password;

    private Button submit;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
        back = findViewById(R.id.back);
        firstName = findViewById(R.id.etfirstname);
        lastName = findViewById(R.id.etlastname);
        emailAddress = findViewById(R.id.etEmailAddress);
        weight = findViewById(R.id.etWeight);
        height = findViewById(R.id.etHeight);
        age = findViewById(R.id.etAge);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);

        submit = findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "Successful submit click");

                Toast.makeText(SignupActivity.this, "Clicked the submit btn", Toast.LENGTH_SHORT).show();

                ParseUser user = new ParseUser();
                user.setPassword(password.getText().toString());
                user.setUsername(username.getText().toString());
                user.setEmail(emailAddress.getText().toString());
                user.put("firstname", firstName.getText().toString());
                user.put("lastname", lastName.getText().toString());
                user.put("height", Integer.parseInt(height.getText().toString()));
                user.put("weight", Integer.parseInt(weight.getText().toString()));
                user.put("age", Integer.parseInt(age.getText().toString()));

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Log.e(TAG, "User create unsuccessful",e);
                            Toast.makeText(SignupActivity.this, "Log in is unsuccessful", Toast.LENGTH_LONG).show();
                            return;

                        } else {
                            Log.i(TAG, " Success on Sign up!");
                            Toast.makeText(SignupActivity.this, "Log in is Successful", Toast.LENGTH_LONG).show();
                            goMainActivity();
                        }
                    }
                });






            }});

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "button clicked on back", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
                Animatoo.animateSlideRight(SignupActivity.this);
                finish();
            }
        });


  } // on Create
    private void goMainActivity() {
        Intent i = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }



} // class





