package com.example.vytautas.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterNewEntry extends AppCompatActivity {
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_entry);
        initRegisterNewActivitySpinner();

    }

    public void initRegisterNewActivitySpinner() {
        Spinner registerNewActivitySpinner = (Spinner) findViewById(R.id.register_new_entry_type_spinner);
        String[] arraySpinner = new String[]{
                "Fire", "Water", "Earth", "Metal", "Electric"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterNewEntry.this, R.layout.register_new_entry_spinner_layout,
                arraySpinner);
        registerNewActivitySpinner.setPrompt("Select your pokemon type");
        registerNewActivitySpinner.setAdapter(adapter);

    }
}







