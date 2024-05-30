package com.example.vihang.ui.guides;

import static android.os.Build.VERSION_CODES.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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

        animateCards();

        // Set click listeners for each card
        binding.cardGeneralrecycling.setOnClickListener(v -> openGuide(Guide.GENERAL_RECYCLING));
        binding.cardPlasticrecycling.setOnClickListener(v -> openGuide(Guide.PLASTICS));
        binding.cardAcceptableitems.setOnClickListener(v -> openGuide(Guide.ACCEPTABLE_ITEMS));
        binding.cardUntiedNationsSdg.setOnClickListener(v -> openGuide(Guide.SDG));

        return root;
    }

    private void animateCards() {
        // fade in animation
        // Tutlane, https://www.tutlane.com/tutorial/android/android-fade-in-out-animations-with-examples
        Animation animFadeIn = AnimationUtils.loadAnimation(requireContext(),
                com.example.vihang.R.anim.fade_in);
        binding.gridGuides.startAnimation(animFadeIn);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void openGuide(Guide guide) {
        // Pass the selected guide to the ViewModel
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.selectedItem(guide);

        // Navigate to the guide details fragment
        ((MainActivity)requireActivity()).changeFragment(com.example.vihang.R.id.navigate_to_guideDetailsFragment);
    }
}