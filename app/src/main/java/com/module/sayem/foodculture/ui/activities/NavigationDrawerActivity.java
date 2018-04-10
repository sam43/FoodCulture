package com.module.sayem.foodculture.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.module.sayem.foodculture.R;
import com.module.sayem.foodculture.storage.SessionManager;
import com.module.sayem.foodculture.ui.fragments.BaseFragment;
import com.module.sayem.foodculture.ui.fragments.HomeFragment;
import com.module.sayem.foodculture.utils.Constants;
import com.module.sayem.foodculture.utils.Utility;

import es.dmoral.toasty.Toasty;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseFragment.OnFragmentInteractionListener {

    String CURRENT_FRAGMENT = Constants.FRAGMENT.HOME;
    TextView hello, mTitle;
    Toolbar toolbar;
    String title = "Home";
    FrameLayout btn_contact_us;
    private SwitchCompat switcher;
    private NavigationView navigationView;
    SessionManager session;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TypefaceUtil.overrideFont(getApplicationContext(), "DEFAULT", "fonts/Ubuntu-Regular.ttf");
        setContentView(R.layout.activity_navigation_drawer);
        Initialize();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Check out new photos and reviews!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.nav_switch);
        View actionView = MenuItemCompat.getActionView(menuItem);
        switcher = actionView.findViewById(R.id.switcher);
        switcher.setOnCheckedChangeListener((compoundButton, b) -> {
            Snackbar.make(navigationView.getMenu().getItem(0).getActionView(), (switcher.isChecked()) ? "Location is ON" : "Location is OFF", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            if (switcher.isChecked()) {
                navigationView.getMenu().getItem(0).setChecked(true);
            } else {
                navigationView.getMenu().getItem(0).setChecked(false);
            }
        });
        /*switcher.setOnCheckedChangeListener((compoundButton, b) -> Snackbar.make(navigationView.getMenu().getItem(0).getActionView(), (switcher.isChecked()) ? "Location is ON" : "Location is OFF", Snackbar.LENGTH_SHORT).setAction("Action", null).show());
        if (switcher.isChecked()) {
            navigationView.getMenu().getItem(0).setChecked(true);
        } else {
            navigationView.getMenu().getItem(0).setCheckable(false);
        }*/
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void Initialize() {

        session = new SessionManager(this.getApplicationContext());
        Utility.ToastyConfig();
        toolbar = findViewById(R.id.toolbar);
        mTitle = toolbar.findViewById(R.id.toolbar_title);
        //hello = findViewById(R.id.tv_hello);
        btn_contact_us = findViewById(R.id.btn_contact_us);
        //app.setTypeface(hello);
        Utility.FontViewBold(getApplicationContext(), mTitle);
        btn_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.info(NavigationDrawerActivity.this, "This feature is coming soon...", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            if (session.isLoggedIn()) {
                session.logoutUser();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        // Handle navigation view item clicks here.

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        int id = item.getItemId();
        if (id == R.id.nav_switch) {
            //switcher.setChecked(!switcher.isChecked());

            /*hello.setVisibility(View.VISIBLE);
            hello.setText("Your profile settings\nComing Soon...");
             
            * */
            
            //Snackbar.make(item.getActionView(), (switcher.isChecked()) ? "is checked" : "not checked", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            if (switcher.isChecked())
            Toast.makeText(this, "Location Turned ON!", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Location is OFF!", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_offers) {
            // Handle the camera action
            fab.setVisibility(View.GONE);
            title = this.getString(R.string.str_offers);
            openFragment(title, Constants.FRAGMENT.ORDER);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_resturants) {
            //hello.setText("search Resturants\nComing Soon...");
            title = this.getString(R.string.str_restaurant);
            openFragment(title, null);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_profile) {
            title = this.getString(R.string.str_profile);
            openFragment(title, null);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_rate_review) {
            title = this.getString(R.string.str_review);
            openFragment(title, null);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_share) {
            title = this.getString(R.string.str_share);
            openFragment(title, Constants.FRAGMENT.HOME);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_settings) {
            title = this.getString(R.string.str_settings);
            openFragment(title, null);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_about) {
            title = this.getString(R.string.str_about);
            openFragment(title, null);
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    private void openFragment(String title, String fragmentID) {
        mTitle.setText(title);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        CURRENT_FRAGMENT = fragmentID;
        if (fragmentID != null) {
            try {
                fragment = (Fragment)(Class.forName("com.module.sayem.foodculture.ui.fragments."+fragmentID).newInstance());
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }

            fragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.flContent, fragment)
                    .commit();
        } else {
            //Toasty.error(this, "Fragment is null!", Toast.LENGTH_LONG).show();
            mTitle.setText(title);
            fragment = new HomeFragment();
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.flContent, fragment)
                    .commit();
        }
    }

    public void LogOut(MenuItem item) {
        finish();
        Toasty.success(this, "You've successfully logged out!\nCome Back Soon!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Calling BAseFragment", Toast.LENGTH_SHORT).show();
    }
}
