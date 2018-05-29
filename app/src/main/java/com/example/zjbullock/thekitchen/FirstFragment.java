package com.example.zjbullock.thekitchen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Zjbullock on 12/3/2017.
 */

public class FirstFragment extends Fragment implements SearchView.OnQueryTextListener{
    // Declare Variables
    private ListView list;
    private ListViewAdapter adapter;
    private SearchView editsearch;
    private String[] recipeList = new String[]{"Siracha Shrimp","Teriyaki Chicken","Lasagna","Spaghetti"}; // Generate sample data
    public static ArrayList<recipeNames> recipeNamesArrayList = new ArrayList<recipeNames>();



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainrecipefeed, container, false);



        // Locate the ListView in listview_main.xml
        list = (ListView) view.findViewById(R.id.lvRecipes);


        recipeNamesArrayList = new ArrayList<>();

        for (int i = 0; i < recipeList.length; i++) {
            recipeNames recipeNames = new recipeNames(recipeList[i]);
            // Binds all strings into an array
            recipeNamesArrayList.add(recipeNames);
        }

        // Pass results to ListViewAdapter Class
       adapter = new ListViewAdapter(getContext());



        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in mainrecipefeed.xml
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), recipeNamesArrayList.get(position).getRecipename(), Toast.LENGTH_SHORT).show();
            }
        });

        final TextView createRecipe = (TextView) view.findViewById(R.id.makerecipe);
        createRecipe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent createRecipeIntent = new Intent(getContext(), RecipeCreation.class);
                getContext().startActivity(createRecipeIntent);
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
        adapter.filter(text);
        return false;
    }

}
