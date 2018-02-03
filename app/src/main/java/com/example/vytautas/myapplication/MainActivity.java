package com.example.vytautas.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;

    Button loginButton;
    User user;
    CheckBox loginRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User(MainActivity.this);
        //iš MainActivity
        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        loginRememberMe = (CheckBox) findViewById(R.id.login_remember_me);
        //iš registerActivity

        etUsername.setError(null);
        etPassword.setError(null);

        initBtnLogin();//login
        initRememberMeCheckBox();//logine rememberMe
        initBtnRegister();//Logine register

    }

    public void initBtnLogin() {
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (etUsername.getText().toString().isEmpty()) {
                    etUsername.requestFocus();
                    etUsername.setError(getResources().getString(R.string.register_empty_username));
                } else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.requestFocus();
                    etPassword.setError(getResources().getString(R.string.login_empty_password));
                } else if (!Validation.isValidCredentials(etUsername.getText().toString())) {
                    etUsername.requestFocus();
                    etUsername.setError(getResources().getString(R.string.login_invalid_username));
                } else if (!Validation.isValidCredentials(etPassword.getText().toString())) {
                    etPassword.requestFocus();
                    etPassword.setError(getResources().getString(R.string.login_invalid_password));
                } else {
                    Toast.makeText(MainActivity.this, "Username: " + etUsername.getText().toString() + "\n" +
                            "Password: " + etPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                    user.setUsernameForLogin(etUsername.getText().toString());
                    user.setPasswordForLogin(etPassword.getText().toString());

                    if (loginRememberMe.isChecked()) {
                        user.setRememberMeKeyForLogin(true);
                    } else {
                        user.setRememberMeKeyForLogin(false);
                    }
                    Intent goToRegisterActivity = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(goToRegisterActivity);
                }
            }
        });
    }

    public void initRememberMeCheckBox() {
        loginRememberMe.setChecked(user.isRememberedForLogin());
        if (user.isRememberedForLogin()) {
            etUsername.setText(user.getUsernameForLogin(), TextView.BufferType.EDITABLE);
            etUsername.setText(user.getPasswordForLogin(), TextView.BufferType.EDITABLE);
        } else {
            etUsername.setText("", TextView.BufferType.EDITABLE);
            etPassword.setText("", TextView.BufferType.EDITABLE);
        }

    }

    public void initBtnRegister() {
        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRegisterActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(goToRegisterActivity);
            }
        });
    }


}

