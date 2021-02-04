package com.example.pokemonproject.DAO;
import com.example.pokemonproject.entity.PokemonEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface PokemonDao {

    @Query("SELECT * FROM pokemon ORDER BY number ASC")
    Observable<List<PokemonEntity>> getAll();

    @Query("SELECT * FROM pokemon where number = :id")
    Observable<List<PokemonEntity>> getByPokeId(int id);

    @Query("SELECT * FROM pokemon where generation = :gen ORDER BY number ASC")
    Observable<List<PokemonEntity>> getByPokeGen(int gen);

    @Query("SELECT * FROM pokemon WHERE generation LIKE :generationName")
    Observable<List<PokemonEntity>> getAllByGen(String generationName);

    @Query("SELECT count() FROM pokemon")
    int getCount();

    @Delete
    Completable delete(PokemonEntity pokemon);

    @Query("DELETE FROM pokemon")
    Completable deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPokemon(PokemonEntity pokemon);

    @Transaction@Insert
    public void insertAll(List<PokemonEntity> pokemonEntities);

}
