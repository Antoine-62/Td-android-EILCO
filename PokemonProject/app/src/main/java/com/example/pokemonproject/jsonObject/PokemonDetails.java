package com.example.pokemonproject.jsonObject;

import java.util.List;

public class PokemonDetails {
    private String number;
    private Family family;
    private String sprite;
    private int gen;
    private List<String> types = null;

    public PokemonDetails(String number, Family family, String sprite, int gen, List<String> types) {
        this.number = number;
        this.family = family;
        this.sprite = sprite;
        this.gen = gen;
        this.types = types;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setNames(List<String> names) {
        this.types = names;
    }
}
