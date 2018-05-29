package com.example.zjbullock.thekitchen;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etFirstName = (EditText) findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText) findViewById(R.id.etLastName);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstname = etFirstName.getText().toString();
                final String password = etPassword.getText().toString();
                final String lastname = etLastName.getText().toString();
                final String username = etUsername.getText().toString();
                final String email = etEmail.getText().toString();

                if(!(inputReady(username, password)))
                    return;

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){  //Used for registering new users to the system
                        try {
                            JSONObject jsonResponse= new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent (Register.this, Login.class);
                                Register.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder (Register.this);
                                builder.setMessage("Register Failed").setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                };

                RegisterRequest registerRequest = new RegisterRequest(firstname, lastname, username, password, email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(registerRequest);

            }
        });
    }

    private boolean inputReady(String username, String password){
        if(username.length() < 3){
            AlertDialog.Builder builder = new AlertDialog.Builder (Register.this);
            builder.setMessage("Username length too short.  It must be greater than 3 characters.").setNegativeButton("Ok", null)
                    .create()
                    .show();
            return false;
        }
        if(password.length() < 5){
            AlertDialog.Builder builder = new AlertDialog.Builder (Register.this);
            builder.setMessage("Password length too short.  It must be 6 characters or greater.").setNegativeButton("Ok", null)
                    .create()
                    .show();
            return false;
        }

        return true;
    }
}
