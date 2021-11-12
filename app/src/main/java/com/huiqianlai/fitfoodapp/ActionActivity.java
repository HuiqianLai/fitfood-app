package com.huiqianlai.fitfoodapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class ActionActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);


        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            UserFragment userFragment = new UserFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            userFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, userFragment).commit();
        }

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .title("Heart")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
                        .title("Cup")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .title("Diploma")
                        .build());

        navigationTabBar.setModels(models);
    }

}