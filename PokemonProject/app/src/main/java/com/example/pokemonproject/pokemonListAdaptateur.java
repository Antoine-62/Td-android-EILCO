package com.example.pokemonproject;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemonproject.entity.PokemonEntity;

import java.util.ArrayList;
import java.util.List;

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
        Glide.with(context).load(pokemon1.getImageURL()).into(imageUrl);

        if(mPokemon.get(position).size()>1){
            PokemonEntity pokemon2 = mPokemon.get(position).get(1);
            ImageView imageUrl2 = holder.imageurl2;
            // System.out.println("pokeAdapt: " +pokemon1.getImageURL());
            Glide.with(context).load(pokemon2.getImageURL()).into(imageUrl2);
        }

        if(mPokemon.get(position).size()>2) {
            PokemonEntity pokemon3 = mPokemon.get(position).get(2);
            ImageView imageUrl3 = holder.imageurl3;
           // System.out.println("pokeAdapt: " + pokemon1.getImageURL());
            Glide.with(context).load(pokemon3.getImageURL()).into(imageUrl3);
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
