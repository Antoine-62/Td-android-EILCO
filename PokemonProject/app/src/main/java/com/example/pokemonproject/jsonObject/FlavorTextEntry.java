package com.example.pokemonproject.jsonObject;

public class FlavorTextEntry {
    private String flavor_text;
    private Language language;
    private Version version;


    public Language getLanguage() {
        return language;
    }

    public String getFlavor_text() {
        return flavor_text;
    }

    public void setFlavor_text(String flavor_text) {
        this.flavor_text = flavor_text;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public FlavorTextEntry(String flavor_text, Language language, Version version) {
        this.flavor_text = flavor_text;
        this.language = language;
        this.version = version;
    }
}
