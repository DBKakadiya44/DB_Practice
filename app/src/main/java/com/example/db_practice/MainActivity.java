package com.example.db_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawer = findViewById(R.id.drawerlayout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawer,toolbar,R.string.open_string,R.string.close_string);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        addfragment(new Show_fragment(MainActivity.this));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.addnew){
                    addfragment(new Add_fragment(MainActivity.this));
                    drawer.close();
                }
                if(item.getItemId()==R.id.update){
                    addfragment(new Update_fragment(MainActivity.this));
                    drawer.close();
                }
                if(item.getItemId()==R.id.display){
                    addfragment(new Show_fragment(MainActivity.this));
                    drawer.close();
                }

                return true;
            }
        });
    }

    private void addfragment(Fragment fragment)
    {
        FragmentManager fm =  getSupportFragmentManager();
        FragmentTransaction transaction =fm.beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }
}