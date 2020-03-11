package com.example.navigationdrawer.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.navigationdrawer.Fragments.AlertFragment;
import com.example.navigationdrawer.Fragments.EmailFragment;
import com.example.navigationdrawer.Fragments.InfoFragment;
import com.example.navigationdrawer.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                switch (menuItem.getItemId()){
                    case R.id.menu_mail:
                        fragment = new EmailFragment();
                        break;
                    case R.id.menu_aler:
                        fragment = new AlertFragment();
                        break;
                    case R.id.menu_info:
                        fragment = new InfoFragment();
                        break;
                     default:
                         Toast.makeText(MainActivity.this,"Item no encontrado", Toast.LENGTH_SHORT).show();
                         break;
                }

                if(fragment != null){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_frame, fragment)
                            .commit();
                }

                menuItem.setChecked(true);

                getSupportActionBar().setTitle(menuItem.getTitle());

                // Close the navigation drawer
                drawerLayout.closeDrawers();

                return true;
            }
        });
    }

    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

