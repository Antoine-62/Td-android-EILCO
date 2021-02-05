package com.example.pokemonproject;

import android.app.Application;

import com.example.pokemonproject.entity.FamilyEntity;
import com.example.pokemonproject.entity.PokemonEntity;
import com.example.pokemonproject.jsonObject.Result;
import com.example.pokemonproject.entity.FamilyEntity;

import java.util.ArrayList;
import java.util.List;

public class pokemonIdListGen extends Application {
    List<Result> pokemonNames;
    ArrayList<PokemonEntity> pokemonEntities;
    ArrayList<ArrayList<PokemonEntity>> pokemonEntitiesList;
    FamilyEntity entityDetails;
    int gen;
    boolean init;
    boolean select;

    @Override
    public void onCreate() {
        super.onCreate();
        this.pokemonNames = null;
        this.pokemonEntities = null;
        this.gen = 0;
        this.init = true;
        this.select = false;
        this.pokemonEntitiesList= null;
        this.entityDetails = null;
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

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public ArrayList<ArrayList<PokemonEntity>> getPokemonEntitiesList() {
        return pokemonEntitiesList;
    }

    public void setPokemonEntitiesList(ArrayList<ArrayList<PokemonEntity>> pokemonEntitiesList) {
        this.pokemonEntitiesList = pokemonEntitiesList;
    }

    public FamilyEntity getEntityDetails() {
        return entityDetails;
    }

    public void setEntityDetails(FamilyEntity entityDetails) {
        this.entityDetails = entityDetails;
    }
}
