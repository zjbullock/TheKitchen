package com.example.zjbullock.thekitchen;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zjbullock on 11/11/2017.
 */
 class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="https://zjbullock.000webhostapp.com/Register.php";
    private Map<String, String> params;

    RegisterRequest(String firstname, String lastname, String username, String password, String email, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
     }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}