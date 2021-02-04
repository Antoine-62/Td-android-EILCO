package com.example.pokemonproject.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemonproject.entity.FamilyEntity;

import java.util.List;

@Dao
public interface FamilyDao {
    @Query("SELECT * FROM details WHERE d_id is :pokemonId")
    List<FamilyEntity> getAllByPokeId(int pokemonId);

    @Insert
    void insertFamily(FamilyEntity details);

    @Delete
    void delete(FamilyEntity details);
}
