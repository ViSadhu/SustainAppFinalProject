package com.example.vihang.ui.guides;

import static android.os.Build.VERSION_CODES.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vihang.Guide;
import com.example.vihang.MainActivity;
import com.example.vihang.R;
import com.example.vihang.databinding.FragmentGuidesBinding;
import com.example.vihang.ui.SharedViewModel;

public class GuidesFragment extends Fragment {

    private FragmentGuidesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGuidesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.cardGeneralrecycling.setOnClickListener(v -> openGuide(Guide.GENERAL_RECYCLING));
        binding.cardPlasticrecycling.setOnClickListener(v -> openGuide(Guide.PLASTICS));
        binding.cardAcceptableitems.setOnClickListener(v -> openGuide(Guide.ACCEPTABLE_ITEMS));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void openGuide(Guide guide) {
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.selectedItem(guide);

        ((MainActivity)requireActivity()).changeFragment(com.example.vihang.R.id.navigate_to_guideDetailsFragment);
    }
}