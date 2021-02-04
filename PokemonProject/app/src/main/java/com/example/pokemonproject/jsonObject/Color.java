package com.example.pokemonproject.jsonObject;

public class Color {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Color(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
