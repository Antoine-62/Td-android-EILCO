package com.example.pokemonproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pokemonproject.DAO.FamilyDao;
import com.example.pokemonproject.DAO.PokemonDao;
import com.example.pokemonproject.entity.FamilyEntity;
import com.example.pokemonproject.entity.PokemonEntity;

@Database(entities = {PokemonEntity.class, FamilyEntity.class}, version = 1)
public abstract class PokemonRoomDatabase extends RoomDatabase {
    private static final String DB_NAME = "pokemon_db";
    private static PokemonRoomDatabase instance;

    public static PokemonRoomDatabase getInstance(Context context){
        if(instance == null){
            synchronized (PokemonRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), PokemonRoomDatabase.class,DB_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }

    public abstract PokemonDao pokemonDao();
    public abstract FamilyDao familyDao();
}
