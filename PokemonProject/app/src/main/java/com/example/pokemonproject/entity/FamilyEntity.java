package com.example.pokemonproject.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "details")
public class FamilyEntity {
    @PrimaryKey(autoGenerate = true)
    public int d_id;

    @ColumnInfo(name = "english_name")
    public String englishName;

    @ColumnInfo(name = "french_name")
    public String frenchName;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "type1")
    public String type1;

    @ColumnInfo(name = "type2")
    public String type2;

    @ColumnInfo(name = "taille")
    public int taille;

    @ColumnInfo(name = "poids")
    public int poids;

    public FamilyEntity(int d_id, String englishName, String frenchName, String description, String type1, String type2, int taille, int poids) {
        this.d_id = d_id;
        this.englishName = englishName;
        this.frenchName = frenchName;
        this.description = description;
        this.type1 = type1;
        this.type2 = type2;
        this.taille = taille;
        this.poids = poids;
    }

    @Ignore
    public FamilyEntity(String englishName, String frenchName, String description, String type1, String type2, int taille, int poids) {
        this.englishName = englishName;
        this.frenchName = frenchName;
        this.description = description;
        this.type1 = type1;
        this.type2 = type2;
        this.taille = taille;
        this.poids = poids;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getFrenchName() {
        return frenchName;
    }

    public void setFrenchName(String frenchName) {
        this.frenchName = frenchName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }
}
