package com.example.td6;

import java.util.List;

public class Repos {
    private int total_count;
    private boolean incomplete_results;
    private List<Repo> items;

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public void setItems(List<Repo> items) {
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public List<Repo> getItems() {
        return items;
    }

    public Repos(int total_count, boolean incomplete_results, List<Repo> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }
}
