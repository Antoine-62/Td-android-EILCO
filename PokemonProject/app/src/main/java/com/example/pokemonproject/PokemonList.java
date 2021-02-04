package com.example.pokemonproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonproject.database.PokemonRoomDatabase;
import com.example.pokemonproject.entity.PokemonEntity;
import com.example.pokemonproject.jsonObject.FlavorTextEntry;
import com.example.pokemonproject.jsonObject.GenListCount;
import com.example.pokemonproject.jsonObject.ListRefs;
import com.example.pokemonproject.jsonObject.Pokemon;
import com.example.pokemonproject.jsonObject.PokemonDetails;
import com.example.pokemonproject.jsonObject.Result;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonList extends AppCompatActivity {
    TabLayout tabLayout;
    int gen=1;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ArrayList<PokemonEntity> pokelist = new ArrayList<PokemonEntity>();
        super.onCreate(savedInstanceState);
        PokemonRoomDatabase poke_db = PokemonRoomDatabase.getInstance(this);
        setContentView(R.layout.pokemon_list);
        tabLayout=(TabLayout)findViewById(R.id.tabnav);
        RecyclerView rvPokemon = (RecyclerView) findViewById(R.id.pokemon);
        pokemonIdListGen app = (pokemonIdListGen) getApplicationContext();
        ArrayList<ArrayList<PokemonEntity>> test = new ArrayList<>();
       /* ArrayList<PokemonEntity> temp = new ArrayList<PokemonEntity>();
        if((app.getPokemonNames() != null) && (app.getPokemonNames().isEmpty() == false)) {
            for (Result pokemon : app.getPokemonNames()) {
                ArrayList<PokemonEntity> temp2 = new ArrayList<PokemonEntity>();
                Log.v("poke:", "i" + pokemon.getName());
                pokeGlitch.getPokemonDetails(pokemon.getName()).enqueue(new Callback<List<PokemonDetails>>() {
                    @Override
                    public void onResponse(Call<List<PokemonDetails>> call, Response<List<PokemonDetails>> response) {
                        System.out.println("pokeResNum: " + response.body().get(0).getNumber());
                        System.out.println("pokeResGen: " + response.body().get(0).getGen());
                        PokemonEntity poke = new PokemonEntity(Integer.parseInt(response.body().get(0).getNumber()), response.body().get(0).getGen(), response.body().get(0).getSprite(), response.body().get(0).getFamily().getId());
                        //poke_db.pokemonDao().insertPokemon(poke);
                        temp.add(poke);
                        System.out.println("tempsize: " + temp.size());
                        if(temp.size()>2){
                            test.add(temp);
                            System.out.println("testize: " + test.get(0).size());
                            temp.removeAll(temp);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PokemonDetails>> call, Throwable t) {
                        System.out.println("RoomWithRx: " + t.getMessage());
                    }
                                /* pokeGli -> {
                                    PokemonEntity poke = new PokemonEntity(Integer.parseInt(pokeGli.get(0).getNumber()), pokeGli.get(0).getGen(), pokeGli.get(0).getSprite(), pokeGli.get(0).getFamily().getId());
                                    pokelist.add(poke);
                                    //poke_db.pokemonDao().delete(poke).blockingAwait();
                                    //poke_db.pokemonDao().insertPokemon(poke).blockingAwait();
                                    //app.getPokemonNames().remove(pokeGli);
                                    Log.v("pokeG:", "i" + Integer.parseInt(pokeGli.get(0).getNumber()));
                                }*/

               /* });
            }
        }*/
        //if((app.getPokemonEntities() != null) && (app.getPokemonEntities().isEmpty() == false)) {
            for (int i = 0; i < app.getPokemonEntities().size(); i++) {
                ArrayList<PokemonEntity> temp = new ArrayList<PokemonEntity>();
                temp.add(app.getPokemonEntities().get(i));
                Log.v("i:", "i" + i);
                Log.v("poke:", "poke" + app.getPokemonEntities().get(i).getNumber());
                i++;
                if (i < app.getPokemonEntities().size()) {
                    temp.add(app.getPokemonEntities().get(i));
                    Log.v("i:", "i" + i);
                    Log.v("poke:", "poke" + app.getPokemonEntities().get(i).getNumber());
                }
                i++;
                if (i < app.getPokemonEntities().size()) {
                    temp.add(app.getPokemonEntities().get(i));
                    Log.v("i:", "i" + i);
                    Log.v("poke:", "poke" + app.getPokemonEntities().get(i).getNumber());
                }
                test.add(temp);
            }
            pokemonListAdaptateur adapter = new pokemonListAdaptateur(test, this);
            rvPokemon.setAdapter(adapter);
            rvPokemon.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<PokemonEntity> temp = new ArrayList<PokemonEntity>();
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    Log.v("tab:", "tab" + tab.getPosition());
                    Fragment fragment = null;
                    switch (tab.getPosition()) {
                        case 0:
                            gen=1;
                            break;
                        case 1:
                            gen=2;
                            break;
                        case 2:
                            gen=3;
                            break;
                        case 3:
                            gen=4;
                            break;
                        case 4:
                            gen=5;
                            break;
                        case 5:
                            gen=6;
                            break;
                        case 6:
                            gen=7;
                            break;
                        default:
                            gen=8;
                    }

                    poke_db.pokemonDao().getByPokeGen(gen)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                  pokemList -> {
                                      if (pokemList.isEmpty()) {
                                          Log.println(Log.VERBOSE, "t", "pas de pokemon en db pour cette gen");
                                          PokeapiGlitchService pokeGlitch = new Retrofit.Builder()
                                                  .baseUrl(PokeapiGlitchService.ENDPOINT)
                                                  .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                                  .addConverterFactory(GsonConverterFactory.create())
                                                  .build()
                                                  .create(PokeapiGlitchService.class);
                                          pokeGlitch.getPokemonGenCount().enqueue(new Callback<GenListCount>() {
                                              @Override
                                              public void onResponse(Call<GenListCount> call, Response<GenListCount> response) {
                                                  int countgen1 = response.body().getGen(gen);
                                                  Log.v("genNumber:", "i" + countgen1);
                                                  int countgenPrec = 1;
                                                  for (int i = gen; 1<i ; i--) {
                                                      countgenPrec = countgenPrec+ response.body().getGen(gen - 1);
                                                      countgen1 = countgen1 + response.body().getGen(gen - 1);
                                                  }
                                                  for (int i = countgenPrec; i < countgen1; i++) {
                                                      Log.v("pokeG:", "i" + i);
                                                      ArrayList<PokemonEntity> temp2 = new ArrayList<PokemonEntity>();
                                                      PokemonEntity poke = new PokemonEntity(i, 1, "https://pokeres.bastionbot.org/images/pokemon/" + i + ".png");
                                                      //poke_db.pokemonDao().insertPokemon(poke);
                                                      temp.add(poke);
                                                      if (i == countgen1 - 1) {
                                                          Completable.fromAction(() -> poke_db.pokemonDao().insertAll(temp))
                                                                  .observeOn(AndroidSchedulers.mainThread())
                                                                  .subscribeOn(Schedulers.single())
                                                                  .subscribe();
                                                          Collections.sort(temp, Comparator.comparing(PokemonEntity::getNumber));
                                                          for (int ii = 0; ii < app.getPokemonEntities().size(); ii++) {
                                                              ArrayList<PokemonEntity> temp = new ArrayList<PokemonEntity>();
                                                              temp.add(app.getPokemonEntities().get(ii));
                                                              Log.v("i:", "i" + ii);
                                                              Log.v("poke:", "poke" + app.getPokemonEntities().get(ii).getNumber());
                                                              ii++;
                                                              if (ii < app.getPokemonEntities().size()) {
                                                                  temp.add(app.getPokemonEntities().get(i));
                                                                  Log.v("i:", "i" + ii);
                                                                  Log.v("poke:", "poke" + app.getPokemonEntities().get(ii).getNumber());
                                                              }
                                                              ii++;
                                                              if (ii < app.getPokemonEntities().size()) {
                                                                  temp.add(app.getPokemonEntities().get(ii));
                                                                  Log.v("i:", "i" + ii);
                                                                  Log.v("poke:", "poke" + app.getPokemonEntities().get(ii).getNumber());
                                                              }
                                                              test.add(temp);
                                                          }
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
                                          app.setPokemonEntities((ArrayList<PokemonEntity>) pokemList);
                                          Intent intent = new Intent(PokemonList.this, PokemonList.class);
                                          startActivity(intent);
                                      }
                                  });
                    pokemonListAdaptateur adapter = new pokemonListAdaptateur(test, this);
                    rvPokemon.setAdapter(adapter);
                    rvPokemon.setLayoutManager(new LinearLayoutManager(this));

                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.pokemon, fragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        //}
        /* else {
        poke_db.pokemonDao().getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .forEach(
                            pokemonList -> {
                                ArrayList<ArrayList<PokemonEntity>> test3 = new ArrayList<>();
                                for (int i = 0; i < pokemonList.size(); i++) {
                                    ArrayList<PokemonEntity> temp = new ArrayList<PokemonEntity>();
                                    temp.add(pokemonList.get(i));
                                    Log.v("i:", "i" + i);
                                    Log.v("poke:", "poke" + pokemonList.get(i).getNumber());
                                    i++;
                                    if (i < pokemonList.size()) {
                                        temp.add(pokemonList.get(i));
                                        Log.v("i:", "i" + i);
                                        Log.v("poke:", "poke" + pokemonList.get(i).getNumber());
                                        Log.v("poke:", "poke" + pokemonList.get(i).getImageURL());
                                    }
                                    i++;
                                    if (i < pokemonList.size()) {
                                        temp.add(pokemonList.get(i));
                                        Log.v("i:", "i" + i);
                                        Log.v("poke:", "poke" + pokemonList.get(i).getNumber());
                                    }
                                    test3.add(temp);
                                }
                                pokemonListAdaptateur adapter = new pokemonListAdaptateur(test, this);
                                rvPokemon.setAdapter(adapter);
                                rvPokemon.setLayoutManager(new LinearLayoutManager(this));
                            });
        }*/
    }
}
