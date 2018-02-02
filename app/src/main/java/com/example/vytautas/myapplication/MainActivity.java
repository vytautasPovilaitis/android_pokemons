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
    Button registerButton;
    CheckBox loginRememberMe;
    User userLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userLogin = new User(MainActivity.this);
        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        loginRememberMe = (CheckBox) findViewById(R.id.login_remember_me);
        registerButton = (Button) findViewById(R.id.register_button);

        etUsername.setError(null);
        etPassword.setError(null);
        initBtnLogin();
        initCheckBox();
        initBtnRegister();
    }

    public void initBtnLogin() {
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean cancel = false;
                String sUsername = etUsername.getText().toString();
                String sPassword = etPassword.getText().toString();
                if (sUsername.isEmpty()) {
                    cancel = true;
                    etUsername.requestFocus();
                    etUsername.setError(getResources().getString(R.string.login_empty_username));
                } else if (sPassword.isEmpty()) {
                    cancel = true;
                    etPassword.requestFocus();
                    etPassword.setError(getResources().getString(R.string.login_empty_password));
                } else if (!Validation.isValidCredentials(etUsername.getText().toString())) {
                    cancel = true;
                    etUsername.requestFocus();
                    etUsername.setError(getResources().getString(R.string.login_invalid_username));
                } else if (!Validation.isValidCredentials(etPassword.getText().toString())) {
                    cancel = true;
                    etPassword.requestFocus();
                    etPassword.setError(getResources().getString(R.string.login_invalid_password));
                }
                if (!cancel) {
                    Toast.makeText(MainActivity.this, "Username: " + etUsername.getText().toString() + "\n" +
                            "Password: " + etPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                    userLogin.setUsernameForLogin(etUsername.getText().toString());
                    userLogin.setPasswordForLogin(etPassword.getText().toString());
                    if (loginRememberMe.isChecked()) {
                        userLogin.setRememberMeKeyForLogin(true);
                    } else {
                        userLogin.setRememberMeKeyForLogin(false);
                    }
                    Intent goToRegisterActivity = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(goToRegisterActivity);
                }
            }
        });
    }

    public void initCheckBox() {
        loginRememberMe.setChecked(userLogin.isRememberedForLogin());
        if (userLogin.isRememberedForLogin()) {
            etUsername.setText(userLogin.getUsernameForLogin(), TextView.BufferType.EDITABLE);
            etUsername.setText(userLogin.getPasswordForLogin(), TextView.BufferType.EDITABLE);
        } else {
            etUsername.setText("", TextView.BufferType.EDITABLE);
            etPassword.setText("", TextView.BufferType.EDITABLE);
        }
    }

    public void initBtnRegister(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRegisterActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(goToRegisterActivity);
            }
        });
    }
}

