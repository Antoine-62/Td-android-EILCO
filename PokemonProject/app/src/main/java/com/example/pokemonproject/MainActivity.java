package com.example.pokemonproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pokemonproject.database.PokemonRoomDatabase;
import com.example.pokemonproject.entity.FamilyEntity;
import com.example.pokemonproject.entity.PokemonEntity;
import com.example.pokemonproject.jsonObject.Family;
import com.example.pokemonproject.jsonObject.FlavorTextEntry;
import com.example.pokemonproject.jsonObject.GenListCount;
import com.example.pokemonproject.jsonObject.ListRefs;
import com.example.pokemonproject.jsonObject.Name;
import com.example.pokemonproject.jsonObject.Pokemon;
import com.example.pokemonproject.jsonObject.PokemonDetails;
import com.example.pokemonproject.jsonObject.PokemonDetailsBody;
import com.example.pokemonproject.jsonObject.Result;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Result> results = new ArrayList<Result>();
    int genNumber =0;
    PokemonDetailsBody pokeStore2 = new PokemonDetailsBody();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<PokemonEntity> temp = new ArrayList<PokemonEntity>();
        PokemonRoomDatabase poke_db = PokemonRoomDatabase.getInstance(this);
        pokemonIdListGen app = (pokemonIdListGen) getApplicationContext();
        if(app.getGen() == 0){
            genNumber = 1;
        }
        else {
            genNumber = app.getGen();
        }
        poke_db.pokemonDao().getByPokeGen(genNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pokemons ->{
                    if(pokemons.isEmpty()){

                        PokeapiGlitchService pokeGlitch = new Retrofit.Builder()
                                .baseUrl(PokeapiGlitchService.ENDPOINT)
                                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(PokeapiGlitchService.class);

                        pokeGlitch.getPokemonGenCount().enqueue(new Callback<GenListCount>() {
                            @Override
                            public void onResponse(Call<GenListCount> call, Response<GenListCount> response) {
                                int countgen1 = response.body().getGen(genNumber);
                                int countgenPrec = 1;
                                for (int i = genNumber; 1<i ; i--) {
                                    countgenPrec = countgenPrec+ response.body().getGen(i - 1);
                                    countgen1 = countgen1 + response.body().getGen(i - 1);
                                }
                                for (int i = countgenPrec; i < countgen1; i++) {
                                    ArrayList<ArrayList<PokemonEntity>> test = new ArrayList<ArrayList<PokemonEntity>>();
                                    PokemonEntity poke = new PokemonEntity(i, genNumber, "https://pokeres.bastionbot.org/images/pokemon/" + i + ".png");
                                    //poke_db.pokemonDao().insertPokemon(poke);
                                    temp.add(poke);
                                    if (i == countgen1 - 1) {
                                        Completable.fromAction(() -> poke_db.pokemonDao().insertAll(temp))
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribeOn(Schedulers.single())
                                                .subscribe();
                                        Collections.sort(temp, Comparator.comparing(PokemonEntity::getNumber));
                                        for (int ii = 0; ii < 100; ii++) {
                                            ArrayList<PokemonEntity> temp2 = new ArrayList<PokemonEntity>();
                                            temp2.add(temp.get(ii));
                                            ii++;
                                            if (ii < temp.size()) {
                                                temp2.add(temp.get(ii));
                                            }
                                            ii++;
                                            if (ii < temp.size()) {
                                                temp2.add(temp.get(ii));
                                            }
                                            test.add(temp2);
                                        }
                                        app.setPokemonEntities(temp);
                                        app.setPokemonEntitiesList(test);
                                        Intent intent = new Intent(MainActivity.this, PokemonList.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<GenListCount> call, Throwable t) {
                                System.out.println("RoomWithRx: " + t.getMessage());
                            }
                    });

                    }
                    else{
                        app.setPokemonEntities((ArrayList<PokemonEntity>) pokemons);
                        Intent intent = new Intent(MainActivity.this, PokemonList.class);
                        startActivity(intent);
                    }
                }, e -> System.out.println("RoomWithRxBase: " +e.getMessage()));
    }

}