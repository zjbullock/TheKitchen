package com.example.zjbullock.thekitchen;

import android.content.Intent;
import android.os.StrictMode;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Zjbullock on 12/3/2017.
 */

public class FirstFragment extends Fragment implements SearchView.OnQueryTextListener{
    // Declare Variables
    private ListView list;
    private ListViewAdapter adapter;
    private SearchView editsearch;
    private String address="https://zjbullock.000webhostapp.com/GetRecipes.php";
    private String line=null;
    private String result=null;
    private InputStream iStream=null;


    public static ArrayList<recipeNames> recipeNamesArrayList= new ArrayList<>();;



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainrecipefeed, container, false);

        if(recipeNamesArrayList != null){
            recipeNamesArrayList.clear();
        }

        // Locate the ListView in listview_main.xml
        list = (ListView) view.findViewById(R.id.lvRecipes);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        getData();


        // Pass results to ListViewAdapter Class
        adapter=new ListViewAdapter(getContext());

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


    private void getData(){
        try {
            URL url = new URL(address);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();

            con.setRequestMethod("GET");

            iStream=new BufferedInputStream(con.getInputStream());
        }
        catch(Exception e){
            e.printStackTrace();
        }


        try{
            BufferedReader bReader=new BufferedReader(new InputStreamReader(iStream));
            StringBuilder sBuilder=new StringBuilder();

            while((line=bReader.readLine()) != null){
                sBuilder.append(line+"\n");
            }
            iStream.close();
            result=sBuilder.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            JSONArray jArray=new JSONArray(result);
            JSONObject jObject=null;



            for(int i=0; i<jArray.length();i++){

                jObject=jArray.getJSONObject(i);
                recipeNames recipeNames = new recipeNames(jObject.getString("recipename"));
                recipeNamesArrayList.add(recipeNames);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
