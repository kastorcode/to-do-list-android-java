package com.kastorcode.todolistproject.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kastorcode.todolistproject.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

@SuppressWarnings("deprecation")
public class MainPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.view, R.string.add};
    private final Context context;
    private static MainViewModel mainViewModel;

    public MainPagerAdapter (Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        mainViewModel = new MainViewModel(this.context);
    }

    @NonNull
    @Override
    public Fragment getItem (int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return MainFragment.newInstance(position, mainViewModel);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle (int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount () {
        return TAB_TITLES.length;
    }

}