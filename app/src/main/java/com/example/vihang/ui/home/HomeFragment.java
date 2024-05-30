package com.example.vihang.ui.home;

import static android.content.Context.MODE_PRIVATE;
import static com.example.vihang.MainActivity.PREFS_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vihang.DBHelper;
import com.example.vihang.R;
import com.example.vihang.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private DBHelper dbHelper;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // initialize DBHelper
        dbHelper = new DBHelper(getContext());

        showFact();
        showDailyTip();
        animateCards();

        // personalized greeting message
        binding.tvHometitle.setText(getString(R.string.home_welcome, preferences.getString("displayName", "User")));

        // open nearby recycling centers in Google Maps
        binding.cardRecyclingcenters.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.ca/maps/search/recycling+center/"));

            startActivity(intent);

        });

        return root;
    }

    private void showFact() {

        // if there are no facts in the database,
        // add testing facts to the database
        if (dbHelper.getFactsCount() == 0) {
            dbHelper.addFacts();
        }

        String randomFact = dbHelper.getRandomFact();
        binding.tvFunfactcontent.setText(randomFact);

    }

    private void showDailyTip() {

        // if there are no tips in the database,
        // add testing tips to the database
        if (dbHelper.getTipsCount() == 0) {
            dbHelper.addTips();
        }

        String randomTip = dbHelper.getRandomTip();
        binding.tvTipscontent.setText(randomTip);

    }

    private void animateCards() {
        // fade in animation
        // Tutlane, https://www.tutlane.com/tutorial/android/android-fade-in-out-animations-with-examples
        Animation animFadeIn = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in);
        binding.cardFunfact.startAnimation(animFadeIn);
        binding.cardDailytips.startAnimation(animFadeIn);
        binding.cardRecyclingcenters.startAnimation(animFadeIn);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}