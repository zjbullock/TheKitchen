package com.example.zjbullock.thekitchen;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Zjbullock on 12/3/2017.
 */

public class SecondFragment extends Fragment implements SearchView.OnQueryTextListener {
    private String[] favList = new String[]{"Siracha Shrimp","Teriyaki Chicken","Lasagna","Spaghetti"};
    private ListView favlist;
    private ListViewAdapter favadapter;
    public static ArrayList<recipeNames> favrecipeNamesArrayList = new ArrayList<recipeNames>();
    private SearchView editsearch;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favorites,container,false);

        // Locate the ListView in listview_main.xml
        favlist = (ListView) view.findViewById(R.id.favRecipes);


        favrecipeNamesArrayList = new ArrayList<>();

        for (int i = 0; i < favList.length; i++) {
            recipeNames favNames = new recipeNames(favList[i]);
            // Binds all strings into an arraylist
            favrecipeNamesArrayList.add(favNames);
        }

        // Pass results to ListViewAdapter Class
        favadapter = new ListViewAdapter(getContext());



        // Binds the Adapter to the ListView
        favlist.setAdapter(favadapter);

        // Locate the EditText in searchFav.xml
        editsearch = (android.widget.SearchView) view.findViewById(R.id.searchFav);
        editsearch.setOnQueryTextListener(this);

        favlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), favrecipeNamesArrayList.get(position).getRecipename(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        favadapter.filter(text);
        return false;
    }
}
