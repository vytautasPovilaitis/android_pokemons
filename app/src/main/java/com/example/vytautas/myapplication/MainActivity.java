package com.example.vytautas.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    User userLogin = new User(MainActivity.this);
    final EditText etUsername = (EditText)findViewById(R.id.username);
    final EditText etPassword = (EditText)findViewById(R.id.password);
    CheckBox loginCheckBox = (CheckBox)findViewById(R.id.login_remember_me);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

      public void initBtnLogin() {
        Button loginButton=(Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
      }

    public void initCheckBox() {
        if (userLogin.getRememberMeForLogin()) {
            etUsername.setText(userLogin.getUsernameForLogin(), TextView.BufferType.EDITABLE);
            etUsername.setText(userLogin.getPasswordForLogin(), TextView.BufferType.EDITABLE);
        } else {
            etUsername.setText("", TextView.BufferType.EDITABLE);
            etPassword.setText("", TextView.BufferType.EDITABLE);
        }
    }

}

