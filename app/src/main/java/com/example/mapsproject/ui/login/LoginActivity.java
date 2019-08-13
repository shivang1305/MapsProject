package com.example.mapsproject.ui.login;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapsproject.MapsActivity;
import com.example.mapsproject.MyData;
import com.example.mapsproject.R;
import com.example.mapsproject.SignupActivity;
import com.example.mapsproject.ui.login.LoginViewModel;
import com.example.mapsproject.ui.login.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {
    String str="";
    Button btn_signup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         final LoginViewModel loginViewModel;
         final EditText passwordEditText = findViewById(R.id.password);
         final Button loginButton = findViewById(R.id.login);
         final ProgressBar loadingProgressBar = findViewById(R.id.loading);
         final EditText usernameEditText = findViewById(R.id.username);

        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);


        SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
        if(!sp.getString("id","kuchbhi").equals("kuchbhi"))
        {
            Intent in =new Intent(LoginActivity.this,MapsActivity.class);
            in.putExtra("userId",sp.getString("id","kuchbhi"));
            startActivity(in);
            Toast.makeText(this, "Welcome! User", Toast.LENGTH_SHORT).show();
        }

        btn_signup=findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(in);
            }
        });

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                   // usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
               // finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
                MyData md=new MyData();
                boolean result=md.login(usernameEditText.getText().toString(),passwordEditText.getText().toString());
                try
                {
                    if(result)
                    {
                        str=""+usernameEditText.getText().toString();
                        SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
                        SharedPreferences.Editor ed=sp.edit();
                        ed.putString("id",str);
                        ed.commit();
                        Intent in = new Intent(LoginActivity.this,MapsActivity.class);
                        in.putExtra("userId",str);
                        startActivity(in);
                        Toast.makeText(LoginActivity.this, "Welcome! User", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid ID or password", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Error : "+e.getMessage());
                }
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + "  User....";
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
  }
}
