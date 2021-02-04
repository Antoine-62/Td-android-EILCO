package com.example.pokemonproject.jsonObject;

import android.util.Log;

import java.util.List;

public class Pokemon {
    private Color color;
    private List<FlavorTextEntry> flavor_text_entries;
    private Integer id;
    private Boolean isBaby;
    private Boolean isLegendary;
    private Boolean isMythical;
    private String name;
    private List<Name> names;

    public Pokemon(Color color, List<FlavorTextEntry> flavor_text_entries, Integer id, Boolean isBaby, Boolean isLegendary, Boolean isMythical, String name,  List<Name> names) {
        this.color = color;
        this.flavor_text_entries = flavor_text_entries;
        this.id = id;
        this.isBaby = isBaby;
        this.isLegendary = isLegendary;
        this.isMythical = isMythical;
        this.name = name;
        this.names = names;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<FlavorTextEntry> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries(List<FlavorTextEntry> flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }

    public FlavorTextEntry getFrenchText(){
        FlavorTextEntry def = this.flavor_text_entries.get(0);//retourne le premier par défaut
        for(int i = 0; i< this.flavor_text_entries.size(); i++){
            Log.v("langue:", "l"+this.flavor_text_entries.get(i).getLanguage().getName());
            if(this.flavor_text_entries.get(i).getLanguage().getName().equals("fr")){
                def = this.flavor_text_entries.get(i);
                i = this.flavor_text_entries.size();
            }
        }
        return def;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getBaby() {
        return isBaby;
    }

    public void setBaby(Boolean baby) {
        isBaby = baby;
    }

    public Boolean getLegendary() {
        return isLegendary;
    }

    public void setLegendary(Boolean legendary) {
        isLegendary = legendary;
    }

    public Boolean getMythical() {
        return isMythical;
    }

    public void setMythical(Boolean mythical) {
        isMythical = mythical;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }
}
