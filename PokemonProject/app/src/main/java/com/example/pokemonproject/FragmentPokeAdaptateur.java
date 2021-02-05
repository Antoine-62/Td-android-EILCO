package com.example.pokemonproject;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pokemonproject.frag.FragmentList;

public class FragmentPokeAdaptateur extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    pokemonIdListGen app;


    public FragmentPokeAdaptateur(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        //app = (pokemonIdListGen) context.getApplicationContext();
    }

    @Override
    public Fragment getItem(int position) {
        //app.setInit(true)
        position= position -1;
        if(position<0){
            position = 0;
        }
        return new FragmentList(position);

    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

}
