package com.example.vihang.ui.settings;

import static android.content.Context.MODE_PRIVATE;

import static com.example.vihang.MainActivity.PREFS_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.vihang.ChangeNameFragment;
import com.example.vihang.MainActivity;
import com.example.vihang.R;
import com.example.vihang.databinding.FragmentSettingsBinding;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SharedPreferences preferences;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferences = requireActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        binding.swNotifications.setChecked(preferences.getBoolean("pushNotifications", true));

        binding.swNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                if(requireActivity().checkSelfPermission
                        (android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

                    // Redirecting the user to the app settings page if permission is not granted
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setData(android.net.Uri.parse("package:" + requireActivity().getPackageName()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);

                    binding.swNotifications.setChecked(false);
                }
            }

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("pushNotifications", isChecked);
            editor.apply();

        });

        binding.tvChangename.setOnClickListener(view -> {

            ((MainActivity) requireActivity()).changeFragment(R.id.navigate_to_changeNameFragment);

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}