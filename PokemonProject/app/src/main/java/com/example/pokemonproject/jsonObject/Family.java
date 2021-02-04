package com.example.pokemonproject.jsonObject;

import java.util.List;

public class Family {
    private Integer id;
    private Integer evolutionStage;
    private List<String> evolutionLine = null;

    public Family(Integer id, Integer evolutionStage, List<String> evolutionLine) {
        this.id = id;
        this.evolutionStage = evolutionStage;
        this.evolutionLine = evolutionLine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEvolutionStage() {
        return evolutionStage;
    }

    public void setEvolutionStage(Integer evolutionStage) {
        this.evolutionStage = evolutionStage;
    }

    public List<String> getEvolutionLine() {
        return evolutionLine;
    }

    public void setEvolutionLine(List<String> evolutionLine) {
        this.evolutionLine = evolutionLine;
    }
}
