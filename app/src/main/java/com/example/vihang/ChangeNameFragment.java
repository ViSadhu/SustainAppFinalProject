package com.example.vihang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vihang.databinding.FragmentChangeNameBinding;

public class ChangeNameFragment extends Fragment {

    FragmentChangeNameBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChangeNameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }
}