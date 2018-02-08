package com.example.vytautas.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText etNewUsername;
    EditText etNewPassword1;
    EditText etNewPassword2;
    EditText etNewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNewUsername = (EditText) findViewById(R.id.register_et_username);
        etNewPassword1 = (EditText) findViewById(R.id.register_et_password1);
        etNewPassword2 = (EditText) findViewById(R.id.register_et_password2);
        etNewEmail = (EditText) findViewById(R.id.register_et_email);

        initBtRegisterNewUser();//register new user
        initBtRegisterBack();//back to login
    }

    public void initBtRegisterNewUser() {
        Button btRegisterNewUser = (Button) findViewById(R.id.register_bt_register);
        btRegisterNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNewUsername.getText().toString().isEmpty()) {
                    etNewUsername.requestFocus();
                    etNewUsername.setError(getResources().getString(R.string.register_empty_username));
                } else if (etNewPassword1.getText().toString().isEmpty()) {
                    etNewPassword1.requestFocus();
                    etNewPassword1.setError(getResources().getString(R.string.register_empty_password));
                } else if (etNewPassword2.getText().toString().isEmpty()) {
                    etNewPassword2.requestFocus();
                    etNewPassword2.setError(getResources().getString(R.string.register_empty_password));
                } else if (etNewEmail.getText().toString().isEmpty()) {
                    etNewEmail.requestFocus();
                    etNewEmail.setError(getResources().getString(R.string.register_empty_email));
                } else if (!Validation.isValidCredentials(etNewUsername.getText().toString())) {
                    etNewUsername.requestFocus();
                    etNewUsername.setError(getResources().getString(R.string.register_invalid_username));
                } else if (!Validation.isValidCredentials(etNewPassword1.getText().toString())) {
                    etNewPassword1.requestFocus();
                    etNewPassword1.setError(getResources().getString(R.string.register_invalid_password));
                } else if (!Validation.isValidCredentials(etNewPassword2.getText().toString())) {
                    etNewPassword2.requestFocus();
                    etNewPassword2.setError(getResources().getString(R.string.register_invalid_password));
                } else if (!etNewPassword1.getText().toString().matches(etNewPassword2.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (!Validation.isValidEmail(etNewEmail.getText().toString())) {
                    etNewEmail.requestFocus();
                    etNewEmail.setError(getResources().getString(R.string.register_invalid_email));
                } else {
                    DatabaseSQLite databaseSQLite = new DatabaseSQLite(getApplicationContext());
                    User user = new User(etNewUsername.getText().toString(),
                            etNewPassword1.getText().toString(), etNewEmail.getText().toString(),"1");
                    databaseSQLite.addUser(user);

                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent goToMainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(goToMainActivity);
                }
            }
        });
    }

    public void initBtRegisterBack() {
        Button registerBackButton = (Button) findViewById(R.id.register_bt_back);
        registerBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(goToMainActivity);
            }
        });
    }
}
