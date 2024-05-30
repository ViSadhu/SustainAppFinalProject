package com.example.vihang;

import static com.example.vihang.MainActivity.PREFS_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vihang.databinding.FragmentChangeNameBinding;

import java.util.Objects;

public class ChangeNameFragment extends Fragment {

    FragmentChangeNameBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChangeNameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.etChangename.requestFocus();

        binding.btnChangeName.setOnClickListener(view -> {

            if (Objects.requireNonNull(binding.etChangename.getText()).toString().isEmpty()) {
                binding.etChangename.setError("Display name is required");
            }
            else {
                // Save the display name in shared preferences
                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("displayName", binding.etChangename.getText().toString());
                editor.apply();

                // Navigate back to the previous fragment
                ((MainActivity) requireActivity()).returnToPreviousFragment();
            }

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}