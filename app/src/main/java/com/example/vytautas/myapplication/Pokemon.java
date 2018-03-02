package com.example.vytautas.myapplication;

/**
 * Created by Vytautas on 2/9/2018.
 */

public class Pokemon {
    private int id;
    private String name;
    private String cp;
    private String abilities;
    private String type;
    private double heigth;
    private double weight;

    public Pokemon() {

    }

    public Pokemon(String name, String cp, String abilities, String type, double heigth, double weigth) {
        this.name = name;
        this.heigth = heigth;
        this.weight = weigth;
        this.cp = cp;
        this.abilities = abilities;
        this.abilities = abilities;
        this.type = type;
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

    public double getHeight() {
        return heigth;
    }

    public void setHeight(double heigth) {
        this.heigth = heigth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weigth) {
        this.weight = weigth;
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

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cp='" + cp + '\'' +
                ", abilities='" + abilities + '\'' +
                ", type='" + type + '\'' +
                ", heigth=" + heigth +
                ", weight=" + weight +
                '}';
    }
}
