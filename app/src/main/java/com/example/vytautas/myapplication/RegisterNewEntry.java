package com.example.vytautas.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        Spinner registerNewActivitySpinner = (Spinner) findViewById(R.id.registerNewAcivity_type_spinner);
        String[] arraySpinner = new String[]{
                "Fire", "Water", "Earth", "Metal", "Electric"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        registerNewActivitySpinner.setAdapter(adapter);
    }
}







