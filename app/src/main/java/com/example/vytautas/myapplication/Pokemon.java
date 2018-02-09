package com.example.vytautas.myapplication;

/**
 * Created by Vytautas on 2/9/2018.
 */

public class Pokemon {
    private int id;
    private String name;
    private String heigth;
    private String weigth;
    private String cp;
    private String abilities;
    private String type;

    public Pokemon() {
    }

    public Pokemon(String name, String heigth, String weigth, String cp, String abilities, String type) {
        this.name = name;
        this.heigth = heigth;
        this.weigth = weigth;
        this.cp = cp;
        this.abilities = abilities;
        this.abilities = abilities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeigth() {
        return heigth;
    }

    public void setHeigth(String heigth) {
        this.heigth = heigth;
    }

    public String getWeigth() {
        return weigth;
    }

    public void setWeigth(String weigth) {
        this.weigth = weigth;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }
}
