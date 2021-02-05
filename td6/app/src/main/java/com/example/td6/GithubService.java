package com.example.td6;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {
    public static final String ENDPOINT = "https://api.github.com";
    @GET("/users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);
    @GET("/search/repositories")
    Observable<Repos> searchRepos(@Query("q") String query);
}
