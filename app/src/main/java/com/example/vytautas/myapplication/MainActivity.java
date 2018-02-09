package com.example.vytautas.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        etUsername = (EditText) findViewById(R.id.login_et_username);
        etPassword = (EditText) findViewById(R.id.login_et_password);
        loginButton = (Button) findViewById(R.id.login_bt_login);
        loginRememberMe = (CheckBox) findViewById(R.id.login_cb_remember_me);

        etUsername.setError(null);
        etPassword.setError(null);

        initBtnLogin();//login mygtukas
        initRememberMeCheckBox();//logine rememberMe checkBox
        initBtnRegister();//Logine register butonas
    }

    public void initBtnLogin() {
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean cancel = false;
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
                    DatabaseSQLite databaseSQLite = new DatabaseSQLite(getApplicationContext());
                    if (databaseSQLite.isValidUser(etUsername.getText().toString(),
                            etPassword.getText().toString())) { // rado vartotoją
                    } else { // nerado vartotojo
                        Toast.makeText(MainActivity.this, "No such user in database",
                                Toast.LENGTH_SHORT).show();
                        cancel = true;
                    }
                }
                if (!cancel) {
                    Toast.makeText(MainActivity.this, "Welcome back, " + etUsername.getText().toString(), Toast.LENGTH_SHORT).show();
                    user.setUsernameForLogin(etUsername.getText().toString());
                    user.setPasswordForLogin(etPassword.getText().toString());
                    if (loginRememberMe.isChecked()) {
                        user.setRememberMeKeyForLogin(true);
                    } else {
                        user.setRememberMeKeyForLogin(false);
                    }
                    Intent goToRegisterActivity = new Intent(MainActivity.this, RegisterNewEntry.class);
                    startActivity(goToRegisterActivity);
                }

            }
        });
    }

    public void initRememberMeCheckBox() {
        loginRememberMe.setChecked(user.isRememberedForLogin());
        if (user.isRememberedForLogin()) {
            etUsername.setText(user.getUsernameForLogin(), TextView.BufferType.EDITABLE);
            etPassword.setText(user.getPasswordForLogin(), TextView.BufferType.EDITABLE);
        } else {
            etUsername.setText("", TextView.BufferType.EDITABLE);
            etPassword.setText("", TextView.BufferType.EDITABLE);
        }
    }

    public void initBtnRegister() {
        Button registerButton = (Button) findViewById(R.id.login_bt_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRegisterActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(goToRegisterActivity);
            }
        });
    }


}

