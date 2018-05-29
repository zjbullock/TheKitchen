package com.example.zjbullock.thekitchen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Zjbullock on 12/4/2017.
 */

public class RecipeCreation extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        final Button bCreateRecipe= (Button) findViewById(R.id.createRecipeButton);

        bCreateRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RecipeCreation.this, UserArea.class);
                RecipeCreation.this.startActivity(loginIntent);
            }
        });
    }
}
