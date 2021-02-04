package com.example.pokemonproject;

import android.app.Application;

import com.example.pokemonproject.entity.PokemonEntity;
import com.example.pokemonproject.jsonObject.Result;

import java.util.ArrayList;
import java.util.List;

public class pokemonIdListGen extends Application {
    List<Result> pokemonNames;
    ArrayList<PokemonEntity> pokemonEntities;
    int gen;

    @Override
    public void onCreate() {
        super.onCreate();
        this.pokemonNames = null;
        this.pokemonEntities = null;
        this.gen = 0;
    }

    public List<Result> getPokemonNames() {
        return pokemonNames;
    }

    public void setPokemonNames(List<Result> pokemonNames) {
        this.pokemonNames = pokemonNames;
    }

    public ArrayList<PokemonEntity> getPokemonEntities() {
        return pokemonEntities;
    }

    public void setPokemonEntities(ArrayList<PokemonEntity> pokemonEntities) {
        this.pokemonEntities = pokemonEntities;
    }

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }
}
