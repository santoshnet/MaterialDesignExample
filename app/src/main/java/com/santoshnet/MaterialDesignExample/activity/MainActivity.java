package com.santoshnet.MaterialDesignExample.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.santoshnet.MaterialDesignExample.R;
import com.santoshnet.MaterialDesignExample.fragment.GalleryFragment;
import com.santoshnet.MaterialDesignExample.fragment.HomeFragment;
import com.santoshnet.MaterialDesignExample.fragment.MovieFragment;
import com.santoshnet.MaterialDesignExample.fragment.NotificationFragment;
import com.santoshnet.MaterialDesignExample.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    View navHeader;
    ImageView navHeaderImage, profile_image;
    TextView navHeaderText, navSubHeaderText, profile_name;
    Bitmap icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);
        navHeaderImage = (ImageView) navHeader.findViewById(R.id.nav_header_image);
        navHeaderText = (TextView) navHeader.findViewById(R.id.nav_header_text);
        navSubHeaderText = (TextView) navHeader.findViewById(R.id.nav_sub_header_text);
        navHeaderText.setText(getString(R.string.header_name));
        navSubHeaderText.setText(getString(R.string.nav_header_sub_name));
        icon = BitmapFactory.decodeResource(getApplicationContext()
                        .getResources(),
                R.drawable.profile_image);
        navHeaderImage.setImageBitmap(icon);
        navHeaderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewProfileImage();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //add this line to display menu1 when the activity is loaded
        displaySelectedScreen(R.id.nav_home);
    }

    private void viewProfileImage() {
        final Dialog imageDialog = new Dialog(MainActivity.this);
        imageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        imageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        imageDialog.setContentView(R.layout.profile_image_view_dialog);
        /*imageDialog.setCancelable(false);
        imageDialog.setCanceledOnTouchOutside(false);*/

        profile_name = (TextView) imageDialog.findViewById(R.id.prf_name);
        profile_image = (ImageView) imageDialog.findViewById(R.id.prf_image);
        profile_image.setImageBitmap(icon);
        profile_name.setText(navHeaderText.getText().toString());

        imageDialog.show();
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
        getMenuInflater().inflate(R.menu.main, menu);
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

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_photos:
                fragment = new GalleryFragment();
                break;
            case R.id.nav_movies:
                fragment = new MovieFragment();
                break;
            case R.id.nav_notifications:
                fragment = new NotificationFragment();
                break;
            case R.id.nav_settings:
                fragment = new SettingFragment();
                break;
            case R.id.nav_about_us:
                startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
                break;
            case R.id.nav_help:
                startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }


}
