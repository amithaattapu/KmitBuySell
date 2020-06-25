package com.example.kmitbuysell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_actions,menu);
        return true;
    }


    public void sell(View v)
    {
        Intent intent=new Intent(Home.this,SellActivity.class);
        startActivity(intent);
    }
    public void books(View v)
    {
        Intent intent=new Intent(Home.this,BooksActivity.class);
        startActivity(intent);
    }

}
