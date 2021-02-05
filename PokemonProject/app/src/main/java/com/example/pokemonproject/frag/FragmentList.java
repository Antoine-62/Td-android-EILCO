package com.example.pokemonproject.frag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonproject.PokeapiGlitchService;
import com.example.pokemonproject.R;
import com.example.pokemonproject.database.PokemonRoomDatabase;
import com.example.pokemonproject.entity.PokemonEntity;
import com.example.pokemonproject.jsonObject.GenListCount;
import com.example.pokemonproject.pokemonIdListGen;
import com.example.pokemonproject.pokemonListAdaptateur;
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


public class FragmentList  extends Fragment {

   // public int Tgen;
    ArrayList<PokemonEntity> pokelist = new ArrayList<PokemonEntity>();
    int gen;

    public FragmentList(int gen){
        this.gen = gen;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        pokemonIdListGen app = (pokemonIdListGen) getActivity().getApplicationContext();
        int[] genTab = new int[] {151,100,135,107,156,72,86, 88};
        View view = inflater.inflate(R.layout.pokemon_list, container, false);
       // super.onCreate(savedInstanceState);
        PokemonRoomDatabase poke_db = PokemonRoomDatabase.getInstance(getActivity().getApplicationContext());
        ArrayList<ArrayList<PokemonEntity>> test = new ArrayList<>();
        ArrayList<PokemonEntity> temp = new ArrayList<PokemonEntity>();
        //bug inconnu ici
        /*if(gen == 0) {
            List<PokemonEntity> tempor = (List<PokemonEntity>) poke_db.pokemonDao().getByPokeGen(0);
            for (int ii = 0; ii < temp.size(); ii++) {
                ArrayList<PokemonEntity> temp2 = new ArrayList<PokemonEntity>();
                temp2.add(tempor.get(ii));
                ii++;
                if (ii < temp.size()) {
                    temp2.add(tempor.get(ii));
                }
                ii++;
                if (ii < temp.size()) {
                    temp2.add(tempor.get(ii));
                }
                test.add(temp2);
            }
        }
        else{*/
        Log.v("Avant debut:", "i");
                   Log.v("debut:", "i"+this.gen);
                   int countgen1 = genTab[this.gen];
                   int countgenPrec = 1;
                    for (int i = this.gen; 1 < i; i--) {
                        countgenPrec = countgenPrec + genTab[i-1];
                        countgen1 = countgen1 + genTab[i-1];
                    }
                    Log.v("countgen1:", "i" + countgen1);
                    Log.v("countgenPrec:", "i" + countgenPrec);
                   for (int j = countgenPrec; j < countgen1; j++) {
                       Log.v("j:", "i" + j);
                       PokemonEntity poke = new PokemonEntity(j, this.gen, "https://pokeres.bastionbot.org/images/pokemon/" + j + ".png");
                       temp.add(poke);
                       if (j == countgen1 - 1) {
                           for (int ii = 0; ii < temp.size(); ii++) {
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
                           Log.v("finif:", "tableau fini");
                       }
                       Log.v("finif2:", "tableau fini");
                  // }
        }

        Log.v("finif3:", "tableau fini");


        Log.v("adattttttttttttttt:", "adattttttttttttttt:");
        pokemonListAdaptateur adapter = new pokemonListAdaptateur(test, view.getContext());
        RecyclerView rvPokemon = (RecyclerView) view.findViewById(R.id.pokemon);
        rvPokemon.setAdapter(adapter);
        rvPokemon.setLayoutManager(new LinearLayoutManager(view.getContext()));
        Log.v("viewwwwww:", view.toString());
        return view;
    }
}
