package com.dorin.c_style.Fregments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dorin.c_style.R;

public class ClosetFragment extends Fragment {



    private AppCompatActivity activity;

    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public ClosetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_calender, container, false);

        findViews(view);


        return view;
    }

    private void findViews(View view) {


    }
}