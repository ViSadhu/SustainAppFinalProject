package com.example.vihang.ui.settings;

import static android.content.Context.MODE_PRIVATE;

import static com.example.vihang.MainActivity.PREFS_NAME;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.vihang.ChangeNameFragment;
import com.example.vihang.R;
import com.example.vihang.databinding.FragmentSettingsBinding;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferences = Objects.requireNonNull(getActivity()).getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        binding.swNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("pushNotifications", isChecked);
            editor.apply();

        });

        binding.tvChangename.setOnClickListener(view -> {

            ChangeNameFragment changeNameFrag= new ChangeNameFragment();

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(((ViewGroup) Objects.requireNonNull(getView()).getParent()).getId() , changeNameFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}