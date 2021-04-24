package edu.csueb.codepath.fitness_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

        submit = findViewById(R.id.btnsubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "Clicked the submit btn", Toast.LENGTH_SHORT).show();

                ParseUser user = new ParseUser();
                user.setPassword(password.getText().toString());
                user.setUsername(username.getText().toString());
                user.setEmail(emailAddress.getText().toString());
                user.put("firstname", firstName.getText().toString());
                user.put("lastname", lastName.getText().toString());
                user.put("height", height.getText().toString());
                user.put("weight", weight.getText().toString());
                user.put("age", age.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.e(TAG, " Success on Sign up!", e);
                        } else {
                            ParseUser.logOut();
                            Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                back.setOnClickListener(v -> finish());


            }});


  } // on Create

} // class


/**
 *  <EditText
 *         android:id="@+id/etfirstname"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:hint="New User: First Name" />
 *
 *
 *     <EditText
 *     android:id="@+id/etlastname"
 *     android:layout_width="match_parent"
 *     android:layout_height="wrap_content"
 *     android:hint="New User: lastname" />
 *
 *
 *     <EditText
 *         android:id="@+id/etage"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:hint="New User: Age" />
 *
 *
 *
 *     <EditText
 *         android:id="@+id/etNewWeight"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:layout_marginTop="4dp"
 *         android:hint="New User: Weight" />
 *
 *
 *     <EditText
 *         android:id="@+id/etNewHeight"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:layout_marginTop="4dp"
 *         android:hint="New User: Height" />
 *
 *     <EditText
 *         android:id="@+id/etEmailAddress"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:hint="New User: Email Address"
 *         android:inputType="textEmailAddress"/>
 *
 */



