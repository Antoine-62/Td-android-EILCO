package com.example.pokemonproject;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import com.example.pokemonproject.database.PokemonRoomDatabase;
import com.example.pokemonproject.entity.PokemonEntity;
import com.example.pokemonproject.jsonObject.GenListCount;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

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
    ViewPager viewPager;
    int gen=1;
    ArrayList<ArrayList<PokemonEntity>> test;
    ArrayList<PokemonEntity> temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        PokemonRoomDatabase poke_db = PokemonRoomDatabase.getInstance(this);
        pokemonIdListGen app = (pokemonIdListGen) getApplicationContext();
            setContentView(R.layout.tab_layout);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.ViewPager);

        final FragmentPokeAdaptateur adaptateur = new FragmentPokeAdaptateur(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adaptateur);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.v("i:", "tttttttttttttttttttttttttttttttttttttttttttttttt");
                Log.v("i:", "tttt"+tab.getPosition());

                if(tab.getPosition()>0)
                    app.setInit(false);
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
