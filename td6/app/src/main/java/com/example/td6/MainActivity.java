package com.example.td6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paramSearch app = (paramSearch) getApplicationContext();
        String search = app.getSearch();
        setContentView(R.layout.repos_list);

        RecyclerView rvRepos = (RecyclerView) findViewById(R.id.rvRepos);




        GithubService githubService = new Retrofit.Builder()
                .baseUrl(GithubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);
        githubService.searchRepos(search).enqueue(new Callback<Repos>() {
            @Override
            public void onResponse(Call<Repos> call, Response<Repos> response)
            {
                afficherRepos2(response.body());
            }
            @Override
            public void onFailure(Call<Repos> call, Throwable t) {
            }
        });
        /*githubService.listRepos("Antoine-62").enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response)
            {
                afficherRepos(response.body());
            }
            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
            }
        });*/
    }
    public void afficherRepos(List<Repo> repos) {
        RecyclerView rvRepos = (RecyclerView) findViewById(R.id.rvRepos);
        RepoAdapter adaptater = new RepoAdapter(repos, this);
        rvRepos.setAdapter(adaptater);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));
        Toast.makeText(this,"nombre de dépots : "+repos.size(),
                Toast.LENGTH_SHORT).show();
        System.out.println(repos.size());
    }
    public void afficherRepos2(Repos repos) {
        RecyclerView rvRepos = (RecyclerView) findViewById(R.id.rvRepos);
        RepoAdapter adaptater = new RepoAdapter(repos.getItems(), this);
        rvRepos.setAdapter(adaptater);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));
        Toast.makeText(this,"nombre de dépots : "+repos.getItems().size(),
                Toast.LENGTH_SHORT).show();
    }
}
