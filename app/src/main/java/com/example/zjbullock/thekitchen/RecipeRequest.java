package com.example.zjbullock.thekitchen;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zjbullock on 5/30/2018.
 */
 class RecipeRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="https://zjbullock.000webhostapp.com/Recipe.php";
    private Map<String, String> params;

    RecipeRequest(String recipename, int servings, int cooktime, String ingredients, String preparation, Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("recipename", recipename);
        params.put("servings", servings+"");
        params.put("cooktime", cooktime+"");
        params.put("ingredients", ingredients);
        params.put("preparation", preparation);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
