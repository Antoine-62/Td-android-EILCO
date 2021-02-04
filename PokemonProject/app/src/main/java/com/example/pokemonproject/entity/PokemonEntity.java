package com.example.pokemonproject.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon")
public class PokemonEntity {
    @PrimaryKey(autoGenerate = true)
    private int pid;

    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "generation")
    private int generation;

    @ColumnInfo(name = "imageURL")
    private String imageURL;

    public PokemonEntity(int number, int generation, String imageURL) {
        this.number = number;
        this.generation = generation;
        this.imageURL = imageURL;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
