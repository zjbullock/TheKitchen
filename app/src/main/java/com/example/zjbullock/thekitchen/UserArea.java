package com.example.zjbullock.thekitchen;





import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class UserArea extends  AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

      /*  final EditText etUsername=(EditText) findViewById(R.id.etUsername);

        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

        Intent intent=getIntent();

        String username = intent.getStringExtra("username");

        String message = "Welcome to your user area "+username;
        welcomeMessage.setText(message);
        etUsername.setText(username);
*/

        createDrawer();

        displaySelectedScreen(R.id.recipeFeed);

    }

    public void createDrawer(){
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_navigation_drawer, menu);
        return true;
    }

    private void displaySelectedScreen(int id){
        Fragment fragment = null;
        switch(id){
            case R.id.recipeFeed:
                fragment = new FirstFragment();
                break;
            case R.id.Favorites:
                fragment = new SecondFragment();
                break;
            case R.id.ShopList:
                fragment = new ThirdFragment();
                break;
            case R.id.Settings:
                fragment = new FourthFragment();
                break;
            case R.id.mainAccount:
                fragment = new SixthFragment();
                break;
            case R.id.LogOut:
                finish();
                fragment = new FifthFragment();
                break;

        }
        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,fragment);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }





}
