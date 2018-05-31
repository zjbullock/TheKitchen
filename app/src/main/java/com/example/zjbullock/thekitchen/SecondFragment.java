package com.example.zjbullock.thekitchen;

import android.os.StrictMode;
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

public class SecondFragment extends Fragment implements SearchView.OnQueryTextListener {
    private ListView favlistView;
    private ListViewAdapter favadapter;
    private SearchView editsearch;
    private String address="https://zjbullock.000webhostapp.com/GetRecipes.php";
    private String line=null;
    private String result=null;
    private InputStream iStream=null;

    public static ArrayList<recipeNames> favrecipeNamesArrayList = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favorites,container,false);

        // Locate the ListView in listview_main.xml
        favlistView = (ListView) view.findViewById(R.id.favRecipes);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        getData();


        // Pass results to ListViewAdapter Class
        favadapter = new ListViewAdapter(favrecipeNamesArrayList,getContext());

        // Binds the Adapter to the ListView
        favlistView.setAdapter(favadapter);

        // Locate the EditText in searchFav.xml
        editsearch = (android.widget.SearchView) view.findViewById(R.id.searchFav);
        editsearch.setOnQueryTextListener(this);

        favlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), favrecipeNamesArrayList.get(position).getRecipename(), Toast.LENGTH_SHORT).show();
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
            JSONObject jObject;



            for(int i=jArray.length()-1; i>0;i--){

                jObject=jArray.getJSONObject(i);
                recipeNames recipeNames = new recipeNames(jObject.getString("recipename"));
                favrecipeNamesArrayList.add(recipeNames);
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
        favadapter.filter(newText);
        return false;
    }
}
