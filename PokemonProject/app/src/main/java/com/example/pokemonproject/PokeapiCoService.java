package com.example.pokemonproject;

import com.example.pokemonproject.jsonObject.ListRefs;
import com.example.pokemonproject.jsonObject.Pokemon;
import com.example.pokemonproject.jsonObject.PokemonDetailsBody;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeapiCoService {
    public static final String ENDPOINT = "https://pokeapi.co/api/v2/";
    @GET("pokemon/")//to get all pokemon list
    Call<ListRefs> listPokemon(@Query("offset") String limitA, @Query("limit") String limitB);
    @GET("pokemon/{pokemonID}")//to get height and weight
    Call<PokemonDetailsBody> searchPokemon(@Path("pokemonID") String pokemonId);
    @GET("pokemon-species/{pokemonNumber}")//to get some details such as description, names, color, etc...
    Call<Pokemon> searchPokemonSpecie(@Path("pokemonNumber") String pokemonNumber);
}
