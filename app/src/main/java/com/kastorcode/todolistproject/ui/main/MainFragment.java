package com.kastorcode.todolistproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kastorcode.todolistproject.fragments.AddFragment;
import com.kastorcode.todolistproject.fragments.ViewFragment;

/**
 * A placeholder fragment containing a simple view.
 */

public class MainFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private final int position;
    private static MainViewModel mainViewModel;

    MainFragment (int position, MainViewModel mainViewModel) {
        this.position = position;
        MainFragment.mainViewModel = mainViewModel;
    }

    public static MainFragment newInstance (int position, MainViewModel mainViewModel) {
        MainFragment fragment = new MainFragment(position, mainViewModel);
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        switch (position) {
            case 0:
                return new ViewFragment(mainViewModel.toDoList, mainViewModel.toDoListAdapter).getRoot(inflater, container);
            case 1:
                return new AddFragment(mainViewModel.toDoList, mainViewModel.toDoListAdapter).getRoot(inflater, container);
        }
        throw new Error("Main pager position (" + position + ") out of range.");
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
    }

}