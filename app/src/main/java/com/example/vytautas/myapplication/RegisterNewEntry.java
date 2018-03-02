package com.example.vytautas.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.vytautas.myapplication.PokemonAdapter.ENTRY_ID;

public class RegisterNewEntry extends AppCompatActivity {
    DatabaseSQLite databaseSQLite;
    Button btAddPokemon, btnUpdate;
    EditText etPokemonName, etPokemonHeight, etPokemonWeight;
    RadioButton rbCpWeak, rbCpMedium, rbCpStrong;
    CheckBox cbAbilities1, cbAbilities2, cbAbilities3;
    Spinner registerNewActivitySpinner;
    Pokemon pokemon;
    ArrayAdapter adapter;
    String[] arraySpinner = new String[]{
            "Fire", "Water", "Earth", "Metal", "Electric"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_entry);

        databaseSQLite = new DatabaseSQLite(getApplicationContext());
        btAddPokemon = (Button) findViewById(R.id.register_new_entry_bt_add);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        etPokemonName = (EditText) findViewById(R.id.register_new_entry_et_name);
        etPokemonHeight = (EditText) findViewById(R.id.register_new_entry_et_height);
        etPokemonWeight = (EditText) findViewById(R.id.register_new_entry_et_weight);

        rbCpWeak = (RadioButton) findViewById(R.id.register_new_entry_radioBt_weak);
        rbCpMedium = (RadioButton) findViewById(R.id.register_new_entry_radioBt_medium);
        rbCpStrong = (RadioButton) findViewById(R.id.register_new_entry_radioBt_strong);

        cbAbilities1 = (CheckBox) findViewById(R.id.register_new_entry_cb_vegan);
        cbAbilities2 = (CheckBox) findViewById(R.id.register_new_entry_cb_twoHeads);
        cbAbilities3 = (CheckBox) findViewById(R.id.register_new_entry_cb_notFat);

        registerNewActivitySpinner = (Spinner) findViewById(R.id.register_new_entry_type_spinner);
        adapter = new ArrayAdapter(this, R.layout.register_new_entry_spinner_layout, arraySpinner);
        registerNewActivitySpinner.setPrompt("Select your pokemon type");
        registerNewActivitySpinner.setAdapter(adapter);

        initUpdate();
        initRegisterNewEntryBtAdd();

        int entryID = -1;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            entryID = extras.getInt(ENTRY_ID);

            if (!extras.isEmpty()) {
                entryID = extras.getInt(ENTRY_ID);
            }
        } else { // jeigu yra naujas irasas, id = -1, jeigu egzistuojantis, bus teigiamas
            entryID = (Integer) savedInstanceState.getSerializable(ENTRY_ID);
        }

        if (entryID == -1) {
            setTitle(R.string.new_entry_label);
        } else {
            setTitle(R.string.entry_update_label);
        }

        if (entryID == -1) { //naujas irasas - disable update button
            btnUpdate.setEnabled(false);
            btAddPokemon.setEnabled(true);
        } else { // egzistuojantis irasas - disable submit
            btnUpdate.setEnabled(true);
            btAddPokemon.setEnabled(false);
        }

        pokemon = new Pokemon();
        if (entryID == -1) { //naujas irasas
            pokemon.setId(-1);
            pokemon.setName("");
            pokemon.setAbilities("Vegan");
            pokemon.setCp("Medium");
            pokemon.setType("Water");
            pokemon.setHeight(0);
            pokemon.setWeight(0);
        } else { // egzistuojantis irasas
            pokemon = databaseSQLite.getPokemon(entryID);
        }

        fillFields(pokemon);
    }

    void initRegisterNewEntryBtAdd() {
        btAddPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFields();
                databaseSQLite.addPokemon(pokemon);
                Intent goToSearchActivity = new Intent(RegisterNewEntry.this, SearchActivity.class);
                startActivity(goToSearchActivity);
                Toast.makeText(getApplicationContext(), pokemon.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    void initUpdate() {

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFields();

                databaseSQLite.updatePokemon(pokemon);

                Intent goToSearchActivity = new Intent(RegisterNewEntry.this, SearchActivity.class);
                startActivity(goToSearchActivity);
            }
        });
    }

    private void getFields() {
        String name = etPokemonName.getText().toString();
        double height = Double.parseDouble(etPokemonHeight.getText().toString());
        double weight = Double.parseDouble(etPokemonWeight.getText().toString());
        String spinnerType = registerNewActivitySpinner.getSelectedItem().toString();
        StringBuilder strCp = new StringBuilder();
        String cp = "";

        if (rbCpWeak.isChecked()) {
            cp = "weak";
        } else if (rbCpMedium.isChecked()) {
            cp = "medium";
        } else if (rbCpStrong.isChecked()) {
            cp = "strong";
        }

        if (cbAbilities1.isChecked()) {
            strCp.append("vegan");
        }
        if (cbAbilities2.isChecked()) {
            strCp.append("two heads");
        }
        if (cbAbilities3.isChecked()) {
            strCp.append("not fat");
        }
        pokemon.setName(name);
        pokemon.setHeight(height);
        pokemon.setWeight(weight);
        pokemon.setType(spinnerType);
        pokemon.setCp(cp);
        pokemon.setAbilities(strCp.toString());
    }


    private void fillFields(Pokemon pokemon) {
        etPokemonName.setText(pokemon.getName());
        etPokemonHeight.setText(String.valueOf(pokemon.getHeight()));
        etPokemonWeight.setText(String.valueOf(pokemon.getWeight()));

        cbAbilities1.setChecked(pokemon.getAbilities().contains("Vegan"));
        cbAbilities2.setChecked(pokemon.getAbilities().contains("Two heads"));
        cbAbilities3.setChecked(pokemon.getAbilities().contains("Not fat"));

        rbCpWeak.setChecked(pokemon.getCp().equals("Medium"));
        rbCpMedium.setChecked(pokemon.getCp().equals("Strong"));
        rbCpStrong.setChecked(pokemon.getCp().equals("Weak"));

        registerNewActivitySpinner.setSelection(adapter.getPosition(pokemon.getType()));
    }
}









