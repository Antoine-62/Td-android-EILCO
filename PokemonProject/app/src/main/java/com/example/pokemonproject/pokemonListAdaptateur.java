package com.example.pokemonproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemonproject.database.PokemonRoomDatabase;
import com.example.pokemonproject.entity.FamilyEntity;
import com.example.pokemonproject.entity.PokemonEntity;
import com.example.pokemonproject.jsonObject.GenListCount;
import com.example.pokemonproject.jsonObject.ListRefs;
import com.example.pokemonproject.jsonObject.Pokemon;
import com.example.pokemonproject.jsonObject.PokemonDetails;
import com.example.pokemonproject.jsonObject.PokemonDetailsBody;

import java.util.ArrayList;
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

public class pokemonListAdaptateur extends RecyclerView.Adapter<pokemonListAdaptateur.ViewHolder>{

    private final ArrayList<ArrayList<PokemonEntity>> mPokemon;
    private Context context;

    public pokemonListAdaptateur(@NonNull ArrayList<ArrayList<PokemonEntity>>mPokemon, Context context) {
        this.mPokemon = mPokemon;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.pokemon_item, parent, false);
        return new ViewHolder(contactView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PokemonEntity pokemon1 = mPokemon.get(position).get(0);
        ImageView imageUrl = holder.imageurl;
        // System.out.println("pokeAdapt: " +pokemon1.getImageURL());
        Glide.with(context).load(pokemon1.getImageURL()).placeholder(R.drawable.pokedex).into(imageUrl);
        imageUrl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), pokemonInfo.class);
                PokemonRoomDatabase poke_db = PokemonRoomDatabase.getInstance(context);

                List<FamilyEntity> detailsDb = poke_db.familyDao().getAllByPokeId(pokemon1.getNumber());

                if(detailsDb.isEmpty())
                {
                    PokeapiCoService pokeCo = new Retrofit.Builder()
                            .baseUrl(PokeapiCoService.ENDPOINT)
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(PokeapiCoService.class);

                    PokeapiGlitchService pokeGlitch = new Retrofit.Builder()
                            .baseUrl(PokeapiGlitchService.ENDPOINT)
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(PokeapiGlitchService.class);


                    pokeGlitch.getPokemonDetails(Integer.toString(pokemon1.getNumber())).enqueue(new Callback<List<PokemonDetails>>() {
                        @Override
                        public void onResponse(Call<List<PokemonDetails>> call, Response<List<PokemonDetails>> response) {
                            PokemonDetails pdet = response.body().get(0);
                            pokeCo.searchPokemon(Integer.toString(pokemon1.getNumber())).enqueue(new Callback<PokemonDetailsBody>() {
                                @Override
                                public void onResponse(Call<PokemonDetailsBody> call, Response<PokemonDetailsBody> response) {
                                    PokemonDetailsBody detailsBody = response.body();

                                    pokeCo.searchPokemonSpecie(Integer.toString(pokemon1.getNumber())).enqueue(new Callback<Pokemon>() {
                                        @Override
                                        public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                                            Pokemon specyDetails = response.body();
                                            String type2 = pdet.getTypes().get(0);
                                            if(pdet.getTypes().size() == 2){
                                                type2 = pdet.getTypes().get(1);
                                            }
                                            String evol = "";
                                            for(int ind =0; ind < pdet.getFamily().getEvolutionLine().size(); ind++){
                                                if(ind == 0){
                                                    evol = pdet.getFamily().getEvolutionLine().get(ind);
                                                }
                                                else{
                                                    evol = evol + ", "+pdet.getFamily().getEvolutionLine().get(ind);
                                                }
                                            }

                                            FamilyEntity details = new FamilyEntity(pokemon1.getNumber(), specyDetails.getName(), specyDetails.getFrenchName().getName(),  specyDetails.getFrenchText().getFlavor_text(), pdet.getTypes().get(0), type2, detailsBody.getHeight(), detailsBody.getWeight(), evol);
                                            Completable.fromAction(() -> poke_db.familyDao().insertFamily(details))
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribeOn(Schedulers.single())
                                                    .subscribe();
                                            //pokemonIdListGen app = (pokemonIdListGen) context.getApplicationContext();

                                            intent.putExtra("number", pokemon1.getNumber());
                                            intent.putExtra("englishName", specyDetails.getName());
                                            intent.putExtra("frenchName", specyDetails.getFrenchName().getName());
                                            intent.putExtra("frenchText", specyDetails.getFrenchText().getFlavor_text());
                                            intent.putExtra("type1", pdet.getTypes().get(0));
                                            intent.putExtra("type2", type2);
                                            intent.putExtra("height", detailsBody.getHeight());
                                            intent.putExtra("weight", detailsBody.getWeight());
                                            intent.putExtra("evol", evol);
                                            context.startActivity(intent);
                                            //((Activity)context).finish();

                                        }
                                        @Override
                                        public void onFailure(Call<Pokemon> call, Throwable t) {
                                            System.out.println("fail to load pokemon: " + t.getMessage());
                                        }
                                    });
                                }
                                @Override
                                public void onFailure(Call<PokemonDetailsBody> call, Throwable t) {
                                    System.out.println("fail to load pokemon2:: " + t.getMessage());
                                }
                            });

                        }
                        @Override
                        public void onFailure(Call<List<PokemonDetails>> call, Throwable t) {
                            System.out.println("fail to load pokemon2:: " + t.getMessage());
                        }
                    });
                }
                else{
                    intent.putExtra("number", detailsDb.get(0).getNumber());
                    intent.putExtra("englishName", detailsDb.get(0).getEnglishName());
                    intent.putExtra("frenchName", detailsDb.get(0).getFrenchName());
                    intent.putExtra("frenchText", detailsDb.get(0).getDescription());
                    intent.putExtra("type1", detailsDb.get(0).getType1());
                    intent.putExtra("type2", detailsDb.get(0).getType2());
                    intent.putExtra("height", detailsDb.get(0).getTaille());
                    intent.putExtra("weight", detailsDb.get(0).getPoids());
                    intent.putExtra("evol", detailsDb.get(0).getEvolutions());
                    context.startActivity(intent);
                }


            }
        });

        if(mPokemon.get(position).size()>1){
            PokemonEntity pokemon2 = mPokemon.get(position).get(1);
            ImageView imageUrl2 = holder.imageurl2;
            // System.out.println("pokeAdapt: " +pokemon1.getImageURL());
            Glide.with(context).load(pokemon2.getImageURL()).placeholder(R.drawable.pokedex).into(imageUrl2);
            imageUrl2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), pokemonInfo.class);
                    PokemonRoomDatabase poke_db = PokemonRoomDatabase.getInstance(context);

                    List<FamilyEntity> detailsDb = poke_db.familyDao().getAllByPokeId(pokemon2.getNumber());

                    if(detailsDb.isEmpty())
                    {
                        PokeapiCoService pokeCo = new Retrofit.Builder()
                                .baseUrl(PokeapiCoService.ENDPOINT)
                                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(PokeapiCoService.class);

                        PokeapiGlitchService pokeGlitch = new Retrofit.Builder()
                                .baseUrl(PokeapiGlitchService.ENDPOINT)
                                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(PokeapiGlitchService.class);


                        pokeGlitch.getPokemonDetails(Integer.toString(pokemon2.getNumber())).enqueue(new Callback<List<PokemonDetails>>() {
                            @Override
                            public void onResponse(Call<List<PokemonDetails>> call, Response<List<PokemonDetails>> response) {
                                PokemonDetails pdet = response.body().get(0);
                                pokeCo.searchPokemon(Integer.toString(pokemon2.getNumber())).enqueue(new Callback<PokemonDetailsBody>() {
                                    @Override
                                    public void onResponse(Call<PokemonDetailsBody> call, Response<PokemonDetailsBody> response) {
                                        PokemonDetailsBody detailsBody = response.body();

                                        pokeCo.searchPokemonSpecie(Integer.toString(pokemon2.getNumber())).enqueue(new Callback<Pokemon>() {
                                            @Override
                                            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                                                Pokemon specyDetails = response.body();

                                                String type2 = pdet.getTypes().get(0);
                                                if(pdet.getTypes().size() == 2){
                                                    type2 = pdet.getTypes().get(1);
                                                }
                                                String evol = "";
                                                for(int ind =0; ind < pdet.getFamily().getEvolutionLine().size(); ind++){
                                                    if(ind == 0){
                                                        evol = pdet.getFamily().getEvolutionLine().get(ind);
                                                    }
                                                    else{
                                                        evol = evol + ", "+pdet.getFamily().getEvolutionLine().get(ind);
                                                    }
                                                }
                                                FamilyEntity details = new FamilyEntity(pokemon2.getNumber(), specyDetails.getName(), specyDetails.getFrenchName().getName(),  specyDetails.getFrenchText().getFlavor_text(), pdet.getTypes().get(0), type2, detailsBody.getHeight(), detailsBody.getWeight(), evol);
                                                Completable.fromAction(() -> poke_db.familyDao().insertFamily(details))
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribeOn(Schedulers.single())
                                                        .subscribe();
                                                //pokemonIdListGen app = (pokemonIdListGen) context.getApplicationContext();
                                                intent.putExtra("number", pokemon2.getNumber());
                                                intent.putExtra("englishName", specyDetails.getName());
                                                intent.putExtra("frenchName", specyDetails.getFrenchName().getName());
                                                intent.putExtra("frenchText", specyDetails.getFrenchText().getFlavor_text());
                                                intent.putExtra("type1", pdet.getTypes().get(0));
                                                intent.putExtra("type2", type2);
                                                intent.putExtra("height", detailsBody.getHeight());
                                                intent.putExtra("weight", detailsBody.getWeight());
                                                intent.putExtra("evol", evol);
                                                context.startActivity(intent);
                                                //((Activity)context).finish();

                                            }
                                            @Override
                                            public void onFailure(Call<Pokemon> call, Throwable t) {
                                                System.out.println("fail to load pokemon: " + t.getMessage());
                                            }
                                        });
                                    }
                                    @Override
                                    public void onFailure(Call<PokemonDetailsBody> call, Throwable t) {
                                        System.out.println("fail to load pokemon2:: " + t.getMessage());
                                    }
                                });

                            }
                            @Override
                            public void onFailure(Call<List<PokemonDetails>> call, Throwable t) {
                                System.out.println("fail to load pokemon2:: " + t.getMessage());
                            }
                        });
                    }
                    else{
                        intent.putExtra("number", detailsDb.get(0).getNumber());
                        intent.putExtra("englishName", detailsDb.get(0).getEnglishName());
                        intent.putExtra("frenchName", detailsDb.get(0).getFrenchName());
                        intent.putExtra("frenchText", detailsDb.get(0).getDescription());
                        intent.putExtra("type1", detailsDb.get(0).getType1());
                        intent.putExtra("type2", detailsDb.get(0).getType2());
                        intent.putExtra("height", detailsDb.get(0).getTaille());
                        intent.putExtra("weight", detailsDb.get(0).getPoids());
                        intent.putExtra("evol", detailsDb.get(0).getEvolutions());
                        context.startActivity(intent);
                    }


                }
            });
        }

        if(mPokemon.get(position).size()>2) {
            PokemonEntity pokemon3 = mPokemon.get(position).get(2);
            ImageView imageUrl3 = holder.imageurl3;
           // System.out.println("pokeAdapt: " + pokemon1.getImageURL());
            Glide.with(context).load(pokemon3.getImageURL()).placeholder(R.drawable.pokedex).into(imageUrl3);
            imageUrl3.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), pokemonInfo.class);
                    PokemonRoomDatabase poke_db = PokemonRoomDatabase.getInstance(context);

                    List<FamilyEntity> detailsDb = poke_db.familyDao().getAllByPokeId(pokemon3.getNumber());

                    if(detailsDb.isEmpty())
                    {
                        PokeapiCoService pokeCo = new Retrofit.Builder()
                                .baseUrl(PokeapiCoService.ENDPOINT)
                                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(PokeapiCoService.class);

                        PokeapiGlitchService pokeGlitch = new Retrofit.Builder()
                                .baseUrl(PokeapiGlitchService.ENDPOINT)
                                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(PokeapiGlitchService.class);


                        pokeGlitch.getPokemonDetails(Integer.toString(pokemon3.getNumber())).enqueue(new Callback<List<PokemonDetails>>() {
                            @Override
                            public void onResponse(Call<List<PokemonDetails>> call, Response<List<PokemonDetails>> response) {
                                PokemonDetails pdet = response.body().get(0);
                                pokeCo.searchPokemon(Integer.toString(pokemon3.getNumber())).enqueue(new Callback<PokemonDetailsBody>() {
                                    @Override
                                    public void onResponse(Call<PokemonDetailsBody> call, Response<PokemonDetailsBody> response) {
                                        PokemonDetailsBody detailsBody = response.body();

                                        pokeCo.searchPokemonSpecie(Integer.toString(pokemon3.getNumber())).enqueue(new Callback<Pokemon>() {
                                            @Override
                                            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                                                Pokemon specyDetails = response.body();

                                                String type2 = pdet.getTypes().get(0);
                                                if(pdet.getTypes().size() == 2){
                                                    type2 = pdet.getTypes().get(1);
                                                }
                                                String evol = "";
                                                for(int ind =0; ind < pdet.getFamily().getEvolutionLine().size(); ind++){
                                                    if(ind == 0){
                                                        evol = pdet.getFamily().getEvolutionLine().get(ind);
                                                    }
                                                    else{
                                                        evol = evol + ", "+pdet.getFamily().getEvolutionLine().get(ind);
                                                    }
                                                }

                                                FamilyEntity details = new FamilyEntity(pokemon3.getNumber(), specyDetails.getName(), specyDetails.getFrenchName().getName(),  specyDetails.getFrenchText().getFlavor_text(), pdet.getTypes().get(0), type2, detailsBody.getHeight(), detailsBody.getWeight(), evol);
                                                Completable.fromAction(() -> poke_db.familyDao().insertFamily(details))
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribeOn(Schedulers.single())
                                                        .subscribe();
                                                //pokemonIdListGen app = (pokemonIdListGen) context.getApplicationContext();
                                                intent.putExtra("number", pokemon3.getNumber());
                                                intent.putExtra("englishName", specyDetails.getName());
                                                intent.putExtra("frenchName", specyDetails.getFrenchName().getName());
                                                intent.putExtra("frenchText", specyDetails.getFrenchText().getFlavor_text());
                                                intent.putExtra("type1", pdet.getTypes().get(0));
                                                intent.putExtra("type2", type2);
                                                intent.putExtra("height", detailsBody.getHeight());
                                                intent.putExtra("weight", detailsBody.getWeight());
                                                intent.putExtra("evol", evol);
                                                context.startActivity(intent);
                                                //((Activity)context).finish();

                                            }
                                            @Override
                                            public void onFailure(Call<Pokemon> call, Throwable t) {
                                                System.out.println("fail to load pokemon: " + t.getMessage());
                                            }
                                        });
                                    }
                                    @Override
                                    public void onFailure(Call<PokemonDetailsBody> call, Throwable t) {
                                        System.out.println("fail to load pokemon2:: " + t.getMessage());
                                    }
                                });

                            }
                            @Override
                            public void onFailure(Call<List<PokemonDetails>> call, Throwable t) {
                                System.out.println("fail to load pokemon2:: " + t.getMessage());
                            }
                        });
                    }
                    else{
                        intent.putExtra("number", detailsDb.get(0).getNumber());
                        intent.putExtra("englishName", detailsDb.get(0).getEnglishName());
                        intent.putExtra("frenchName", detailsDb.get(0).getFrenchName());
                        intent.putExtra("frenchText", detailsDb.get(0).getDescription());
                        intent.putExtra("type1", detailsDb.get(0).getType1());
                        intent.putExtra("type2", detailsDb.get(0).getType2());
                        intent.putExtra("height", detailsDb.get(0).getTaille());
                        intent.putExtra("weight", detailsDb.get(0).getPoids());
                        intent.putExtra("evol", detailsDb.get(0).getEvolutions());
                        context.startActivity(intent);
                    }


                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return mPokemon.size();
    }

    public  static  class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageurl;
        public ImageView imageurl2;
        public ImageView imageurl3;

        public ViewHolder(View itemView){
            super(itemView);

            imageurl = (ImageView) itemView.findViewById(R.id.pokesprite1);
            imageurl2 = (ImageView) itemView.findViewById(R.id.pokesprite2);
            imageurl3 = (ImageView) itemView.findViewById(R.id.pokesprite3);
        }
    }
}
