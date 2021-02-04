package com.example.pokemonproject.jsonObject;

public class PokemonDetailsBody {
    private int weight;
    private int height;

    public PokemonDetailsBody() {
        this.weight = 10;
        this.height = 10;
    }

    public PokemonDetailsBody(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
