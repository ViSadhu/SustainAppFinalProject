package com.example.vihang;

import static com.example.vihang.MainActivity.PREFS_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vihang.databinding.FragmentChangeNameBinding;

import java.util.Objects;

public class ChangeNameFragment extends Fragment {

    FragmentChangeNameBinding binding;
    SharedPreferences sharedPreferences;

    public ChangeNameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChangeNameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Retrieve the display name from shared preferences
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String displayName = sharedPreferences.getString("displayName", "");
        binding.etChangename.setText(displayName);

        // Request focus on the EditText
        binding.etChangename.requestFocus();

        // Set a click listener for the button
        binding.btnChangeName.setOnClickListener(bview -> validateAndSave() );

        return root;
    }

    private void validateAndSave() {

        // Validate the display name
        // Display name cannot be empty
        if (Objects.requireNonNull(binding.etChangename.getText()).toString().isEmpty()) {
            binding.etChangename.setError("Display name is required");
        }
        // Display name cannot contain numbers
        if (Objects.requireNonNull(binding.etChangename.getText()).toString().matches(".*\\\\d.*")) {
            binding.etChangename.setError("Display name cannot contain numbers");
        }
        else {
            // Save the display name in shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("displayName", binding.etChangename.getText().toString());
            editor.apply();

            // Navigate back to the previous fragment
            ((MainActivity) requireActivity()).returnToPreviousFragment();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}