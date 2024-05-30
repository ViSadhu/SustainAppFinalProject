package com.example.vihang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.vihang.databinding.FragmentGuideDetailsBinding;
import com.example.vihang.ui.SharedViewModel;

public class GuideDetailsFragment extends Fragment {

    FragmentGuideDetailsBinding binding;
    private SharedViewModel viewModel;
    public GuideDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGuideDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        final WebView webView = binding.wvGuide;

        viewModel.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            webView.loadUrl(item.getUrl());
        });

        return root;
    }
}