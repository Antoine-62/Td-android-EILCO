package com.example.pokemonproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemonproject.entity.FamilyEntity;
import com.example.pokemonproject.entity.PokemonEntity;
import com.example.pokemonproject.jsonObject.Pokemon;
import com.example.pokemonproject.jsonObject.PokemonDetails;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pokemonInfo extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_fiche);

        Intent intent = getIntent();

        int number = intent.getIntExtra("number",1);
        String englishName = intent.getStringExtra("englishName");
        String frenchName = intent.getStringExtra("frenchName");
        String frenchText = intent.getStringExtra("frenchText");
        String type1 = intent.getStringExtra("type1");
        String type2 = intent.getStringExtra("type2");
        int weight = intent.getIntExtra("weight",1);
        int height = intent.getIntExtra("height",1);
        String evol = intent.getStringExtra("evol");

        ImageView urlImageView = (ImageView) findViewById(R.id.imageView);
        String url = "https://pokeres.bastionbot.org/images/pokemon/" + number + ".png";
        Glide.with(this).load(url).placeholder(R.drawable.pokedex).into(urlImageView);

        TextView StringNom = (TextView) findViewById(R.id.textNom);
        StringNom.setText(frenchName);

        TextView StringDescription = (TextView) findViewById(R.id.textDescription);
        StringDescription.setText(frenchText);

        TextView StringTaille = (TextView) findViewById(R.id.textTaille);
        StringTaille.setText("Taille : " + height + " m");

        TextView StringPoids = (TextView) findViewById(R.id.textPoids);
        StringPoids.setText("Poids : " + weight + " kg");

        TextView StringType = (TextView) findViewById(R.id.textType);
        StringType.setText("Type : " + type1 + " - " + type2);

        TextView StringEvolutions = (TextView) findViewById(R.id.textEvolutions);
        StringEvolutions.setText("Chaîne d'évolutions : "+evol);



    }
}
