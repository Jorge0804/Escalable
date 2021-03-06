package com.example.escalable.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.escalable.Class.Data;
import com.example.escalable.Fragments.BlogsFragment;
import com.example.escalable.Fragments.CoursesFragment;
import com.example.escalable.Fragments.InfoCourseFragment;
import com.example.escalable.Fragments.MainFragment;
import com.example.escalable.Fragments.MyCoursesFragment;
import com.example.escalable.Fragments.MypaysFragment;
import com.example.escalable.Fragments.PlainsFragment;
import com.example.escalable.Fragments.PurchaseFragment;
import com.example.escalable.Fragments.UserFragment;
import com.example.escalable.R;

public class SidebarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CoursesFragment.OnFragmentInteractionListener,
        BlogsFragment.OnFragmentInteractionListener, UserFragment.OnFragmentInteractionListener , MypaysFragment.OnFragmentInteractionListener, MainFragment.OnFragmentInteractionListener, PlainsFragment.OnFragmentInteractionListener, PurchaseFragment.OnFragmentInteractionListener,
        MyCoursesFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setBackgroundColor(getResources().getColor(R.color.blue));

        getSupportFragmentManager().beginTransaction().replace(R.id.content, new MainFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sidebar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Boolean selected_fragment = false;

        if (id == R.id.nav_courses) {
            selected_fragment = true;
            fragment = new CoursesFragment();
        } else if (id == R.id.nav_blog) {
            selected_fragment = true;
            fragment = new BlogsFragment();
        } else if (id == R.id.nav_mycourses) {
            selected_fragment = true;
            fragment = new MyCoursesFragment();
        } else if (id == R.id.nav_user) {
            selected_fragment = true;
            fragment = new UserFragment();
        } else if (id == R.id.courses_container) {
            selected_fragment = true;
            fragment = new InfoCourseFragment();
        } else if (id == R.id.nav_plains) {
            selected_fragment = true;
            fragment = new PlainsFragment();
        } else if (id == R.id.nav_mypays){
            selected_fragment = true;
            fragment = new MypaysFragment();
        }





        if(selected_fragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
