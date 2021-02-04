package com.example.pokemonproject;

import com.example.pokemonproject.jsonObject.GenListCount;
import com.example.pokemonproject.jsonObject.PokemonDetails;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeapiGlitchService {
    public static final String ENDPOINT = "https://pokeapi.glitch.me/v1/";
    @GET("pokemon/{pokemonName}")//to get generation, sprite and types
    Call<List<PokemonDetails>> getPokemonDetails(@Path("pokemonName") String pokemonName);

    @GET("pokemon/counts")//to get counts for each generation
    Call<GenListCount> getPokemonGenCount();
}
