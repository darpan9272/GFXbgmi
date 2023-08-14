package com.PubgBgmi.gfxbgmi;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.PubgBgmi.gfxbgmi.databinding.FragmentFaqBinding;

public class faqFragment extends Fragment {

FragmentFaqBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFaqBinding.inflate(inflater, container, false);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.buttonLuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), privacypolicy.class);
                startActivity(intent);

            }

        });



        return binding.getRoot();

    }
}