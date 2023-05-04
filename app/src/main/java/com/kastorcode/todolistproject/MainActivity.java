package com.kastorcode.todolistproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com.kastorcode.todolistproject.databinding.ActivityMainBinding;
import com.kastorcode.todolistproject.helpers.AlertHelper;
import com.kastorcode.todolistproject.helpers.SettingsHelper;
import com.kastorcode.todolistproject.models.SettingsModel;
import com.kastorcode.todolistproject.ui.main.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsModel settingsModel = SettingsHelper.readData(this);
        SettingsHelper.applySettings(this, settingsModel);
        ActivityMainBinding activityMain = ActivityMainBinding.inflate(getLayoutInflater());
        activityMain.mainAbout.setOnClickListener(view -> AlertHelper.about(this, settingsModel));
        setContentView(activityMain.getRoot());

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(this, getSupportFragmentManager());
        ViewPager mainViewPager = activityMain.mainViewPager;
        mainViewPager.setAdapter(mainPagerAdapter);

        TabLayout mainTabs = activityMain.mainTabs;
        mainTabs.setupWithViewPager(mainViewPager);
    }

}