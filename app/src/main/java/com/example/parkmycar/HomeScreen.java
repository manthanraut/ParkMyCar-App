package com.example.parkmycar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;

public class HomeScreen extends AppCompatActivity implements OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawer, toolbar, R.string.navgation_drawer_open, R.string.navgation_drawer_close);
        this.drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void onBackPressed() {
        if (this.drawer.isDrawerOpen((int) GravityCompat.START)) {
            this.drawer.closeDrawer((int) GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setSupportActionBar(android.widget.Toolbar toolbar) {
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.about /*2131230726*/:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new about1()).commit();
                break;
            case R.id.guide /*2131230843*/:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new guide1()).commit();
                break;
            case R.id.history /*2131230844*/:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new myhistory1()).commit();
                break;
            case R.id.nav_message /*2131230876*/:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new profile()).commit();
                break;
            case R.id.nav_share /*2131230877*/:
                Toast.makeText(this, "Share", 0).show();
                break;
            case R.id.sendfeedback /*2131230929*/:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new feedback()).commit();
                break;
        }
        this.drawer.closeDrawer((int) GravityCompat.START);
        return true;
    }
}
