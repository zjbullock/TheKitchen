package com.example.zjbullock.thekitchen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Zjbullock on 12/4/2017.
 */

public class RecipeCreation extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        final EditText etRecipeName = (EditText) findViewById(R.id.recipeName);
        final EditText etServings = (EditText) findViewById(R.id.servings);
        final EditText etCookTime = (EditText) findViewById(R.id.cookTime);
        final EditText etIngredients = (EditText) findViewById(R.id.ingredients);
        final EditText etPreparation = (EditText) findViewById(R.id.preparation);
        final Button bCreateRecipe= (Button) findViewById(R.id.createRecipeButton);

        bCreateRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String recipename = etRecipeName.getText().toString();
                final int servings = Integer.parseInt(etServings.getText().toString());
                final int cooktime = Integer.parseInt(etCookTime.getText().toString());
                final String ingredients = etIngredients.getText().toString();
                final String preparation = etPreparation.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){  //Used for registering new users to the system
                        try {
                            JSONObject jsonResponse= new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent (RecipeCreation.this, UserArea.class);
                                RecipeCreation.this.startActivity(intent);
                                System.out.println("We reached succeed.");
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder (RecipeCreation.this);
                                builder.setMessage("Recipe Creation Failed").setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                };

                RecipeRequest recipeRequest = new RecipeRequest(recipename, servings, cooktime, ingredients, preparation, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RecipeCreation.this);
                queue.add(recipeRequest);

            }
        });


    }
    
}
