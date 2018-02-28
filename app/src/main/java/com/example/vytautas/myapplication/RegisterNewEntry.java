package com.example.vytautas.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterNewEntry extends AppCompatActivity {
    DatabaseSQLite databaseSQLite;
    Button btAddPokemon;
    EditText etPokemonName, etPokemonHeigth, etPokemonWeigth;
    RadioButton rbCpWeak, rbCpMedium, rbCpStrong;
    CheckBox cbAbilities1, cbAbilities2, cbAbilities3;
    Spinner registerNewActivitySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_entry);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        databaseSQLite = new DatabaseSQLite(getApplicationContext());
        btAddPokemon = (Button) findViewById(R.id.register_new_entry_bt_add);
        etPokemonName = (EditText) findViewById(R.id.register_new_entry_et_name);
        etPokemonHeigth = (EditText) findViewById(R.id.register_new_entry_et_heigth);
        etPokemonWeigth = (EditText) findViewById(R.id.register_new_entry_et_weigth);
        rbCpWeak = (RadioButton) findViewById(R.id.register_new_entry_radioBt_weak);
        rbCpMedium = (RadioButton) findViewById(R.id.register_new_entry_radioBt_medium);
        rbCpStrong = (RadioButton) findViewById(R.id.register_new_entry_radioBt_strong);
        cbAbilities1 = (CheckBox) findViewById(R.id.register_new_entry_cb_vegan);
        cbAbilities2 = (CheckBox) findViewById(R.id.register_new_entry_cb_twoHeads);
        cbAbilities3 = (CheckBox) findViewById(R.id.register_new_entry_cb_notFat);
        registerNewActivitySpinner = (Spinner) findViewById(R.id.register_new_entry_type_spinner);
        initRegisterNewEntryBtAdd();
        initRegisterNewEntrySpinner();

    }

    void initRegisterNewEntrySpinner() {
        String[] arraySpinner = new String[]{
                "Fire", "Water", "Earth", "Metal", "Electric"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterNewEntry.this, R.layout.register_new_entry_spinner_layout,
                arraySpinner);
        registerNewActivitySpinner.setPrompt("Select your pokemon type");
        registerNewActivitySpinner.setAdapter(adapter);
    }

    void initRegisterNewEntryBtAdd() {
        btAddPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer str = new StringBuffer();
                String cp = "";
                if (rbCpWeak.isChecked()) {
                    cp = "weak";
                } else if (rbCpMedium.isChecked()) {
                    cp = "medium";
                } else if (rbCpStrong.isChecked()) {
                    cp = "strong";
                }
                if (cbAbilities1.isChecked()) {
                    str.append("vegan");
                }
                if (cbAbilities2.isChecked()) {
                    str.append("two heads");
                }
                if (cbAbilities3.isChecked()) {
                    str.append("not fat");
                }
                if ((!Validation.isValidDouble(etPokemonHeigth.getText().toString()))) {
                    etPokemonHeigth.requestFocus();
                    etPokemonHeigth.setError("Error");
                } else if (!Validation.isValidDouble(etPokemonWeigth.getText().toString())) {
                    etPokemonWeigth.requestFocus();
                    etPokemonWeigth.setError("Error");
                } else if (!Validation.isValidCredentials(etPokemonName.getText().toString())) {
                    etPokemonName.requestFocus();
                    etPokemonName.setError("Error");
                } else {
                    Pokemon pokemon = new Pokemon(etPokemonName.getText().toString(), etPokemonHeigth.getText().toString(), etPokemonWeigth.getText().toString(), cp, str.toString(), registerNewActivitySpinner.getSelectedItem().toString());
                    databaseSQLite.addPokemon(pokemon);
                    String message = databaseSQLite.getAllPokemons();
                    Toast.makeText(RegisterNewEntry.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}







