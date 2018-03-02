package com.example.vytautas.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vytautas on 2/9/2018.
 */

public class DatabaseSQLite extends SQLiteOpenHelper {

    //database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db";

    //user
    private static final String TABLE_USERS = "users";
    private static final String USER_ID = "id";
    private static final String USER_LEVEL = "userlevel";
    private static final String USER_NAME = "name";
    private static final String USER_PASSWORD = "password";
    private static final String USER_EMAIL = "email";

    //pokemon
    private static final String TABLE_POKEMONS = "pokemons";
    private static final String POKEMON_ID = "pokemon_id";
    private static final String POKEMON_NAME = "pokemon_name";
    private static final String POKEMON_HEIGHT = "pokemon_heighT";
    private static final String POKEMON_WEIGHT = "pokemon_weigHT";
    private static final String POKEMON_CP = "pokemon_cp";
    private static final String POKEMON_ABILITIES = "pokemon_abilities";
    private static final String POKEMON_TYPE = "pokemon_type";

    private static final String CREATE_POKEMON_TABLE = "CREATE TABLE "
            + TABLE_POKEMONS + "("
            + POKEMON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + POKEMON_NAME + " TEXT, "
            + POKEMON_HEIGHT + " REAL,"
            + POKEMON_WEIGHT + " REAL, "
            + POKEMON_CP + " TEXT, "
            + POKEMON_ABILITIES + " TEXT, "
            + POKEMON_TYPE + " TEXT " + ");";

    private final static String CREATE_USERS_TABLE = "CREATE TABLE "
            + TABLE_USERS + " ("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_NAME + " TEXT, "
            + USER_PASSWORD + " TEXT, "
            + USER_EMAIL + " TEXT, "
            + USER_LEVEL + " TEXT " + ");";

    public DatabaseSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_POKEMON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POKEMONS);
        // Create tables again
        onCreate(db);
    }

    //pokemonui

    public void addPokemon(Pokemon pokemon) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(POKEMON_NAME, pokemon.getName());
        values.put(POKEMON_CP, pokemon.getCp());
        values.put(POKEMON_ABILITIES, pokemon.getAbilities());
        values.put(POKEMON_TYPE, pokemon.getType());
        values.put(POKEMON_HEIGHT, pokemon.getHeight());
        values.put(POKEMON_WEIGHT, pokemon.getWeight());

        // Inserting Row
        db.insert(TABLE_POKEMONS, null, values);
        // Closing database connection
        db.close();
    }

    public List<Pokemon> getAllPokemons() {
        List<Pokemon> pokemonai = new ArrayList<Pokemon>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_POKEMONS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pokemon pokemon = new Pokemon();

                pokemon.setId(Integer.parseInt(cursor.getString(0)));
                pokemon.setName(cursor.getString(1));
                pokemon.setCp(cursor.getString(2));
                pokemon.setAbilities(cursor.getString(3));
                pokemon.setType(cursor.getString(4));
                pokemon.setWeight(cursor.getDouble(5));
                pokemon.setHeight(cursor.getDouble(6));

                // adding user to list
                pokemonai.add(pokemon);
            } while (cursor.moveToNext());
        }

        // return pokemonaiSQLite list
        return pokemonai;
    }

    public Pokemon getPokemon(int id) {
        Pokemon pokemon = new Pokemon();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_POKEMONS + " WHERE id = '" + id + "'", null);

        pokemon.setId(Integer.parseInt(cursor.getString(0)));
        pokemon.setName(cursor.getString(1));
        pokemon.setCp(cursor.getString(2));
        pokemon.setAbilities(cursor.getString(3));
        pokemon.setType(cursor.getString(4));
        pokemon.setWeight(cursor.getDouble(5));
        pokemon.setHeight(cursor.getDouble(6));

        return pokemon;
    }

    public void updatePokemon(Pokemon pokemon) {
        ContentValues cv = new ContentValues();
        cv.put(POKEMON_NAME, pokemon.getName());
        cv.put(POKEMON_CP, pokemon.getCp());
        cv.put(POKEMON_ABILITIES, pokemon.getAbilities());
        cv.put(POKEMON_TYPE, pokemon.getType());
        cv.put(POKEMON_WEIGHT, pokemon.getWeight());
        cv.put(POKEMON_HEIGHT, pokemon.getHeight());

        getReadableDatabase().update(TABLE_POKEMONS, cv, " id = " + pokemon.getId(), null);
    }

    //useriui
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USER_NAME, user.getUsernameForRegister());
        values.put(USER_PASSWORD, user.getPasswordForRegister());
        values.put(USER_EMAIL, user.getEmailForRegister());
        values.put(USER_LEVEL, user.getUserLevel());

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        // Closing database connection
        db.close();
    }

    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_USERS,
                new String[]{
                        USER_ID,
                        USER_NAME,
                        USER_PASSWORD,
                        USER_EMAIL,
                        USER_LEVEL,
                },
                USER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
        return user;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUsernameForRegister(cursor.getString(2));
                user.setPasswordForRegister(cursor.getString(3));
                user.setEmailForRegister(cursor.getString(4));
                user.setUserlevel(cursor.getString(1));
                // adding user to list
                users.add(user);
                cursor.close();
            } while (cursor.moveToNext());
        }
        // return users list
        return users;
    }

    public boolean isValidUser(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE "
                        + USER_NAME + "='" + username + "'AND " +
                        USER_PASSWORD + "='" + password + "'", null);
        if (res.getCount() > 0)
            return true;
        return false;
    }
}
