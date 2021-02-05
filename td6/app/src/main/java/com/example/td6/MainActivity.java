package com.example.td6;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.reactivex.rxjava3.internal.operators.observable.ObservableBlockingSubscribe.subscribe;


public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paramSearch app = (paramSearch) getApplicationContext();
        String search = app.getSearch();
        setContentView(R.layout.repos_list);

        RecyclerView rvRepos = (RecyclerView) findViewById(R.id.rvRepos);




        GithubService githubService = new Retrofit.Builder()
                .baseUrl(GithubService.ENDPOINT)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);

        int count;
        githubService.searchRepos(search)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(searchRepo ->
                        {
                            searchRepo.getItems().forEach((repo) ->
                                    Log.e("coco", repo.getFull_name())
                            );
                            return searchRepo;
                        }
                ).subscribe(searchRepo ->
                //Ici vous obtenez vos resultat dans la variable listrepo
                afficherRepos2(searchRepo)
        );

        /*githubService.searchRepos(search).enqueue(new Callback<Repos>() {
            @Override
            public void onResponse(Call<Repos> call, Response<Repos> response)
            {
                afficherRepos2(response.body());
            }
            @Override
            public void onFailure(Call<Repos> call, Throwable t) {
            }
        });µ/
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
