package com.example.vihang.ui.settings;

import static android.content.Context.MODE_PRIVATE;

import static com.example.vihang.MainActivity.PREFS_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.vihang.MainActivity;
import com.example.vihang.R;
import com.example.vihang.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SharedPreferences preferences;
    boolean isEnabled;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferences = requireActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        isEnabled = preferences.getBoolean("pushNotifications", true);

        animateCards();

        // update the card as per the shared preferences
        if (isEnabled) {
            setNotificationsOn();
        }
        else {
            setNotificationsOff();
        }

        binding.cardPushnotifications.setOnClickListener(view -> {

            // if notifications are set to off and card is clicked
            if (!isEnabled) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    // if permission is not granted
                    if(!isNotificationPermissionGranted()) {

                        launchSettings();

                        setNotificationsOff();
                    }
                    // if permission is granted
                    else {
                        setNotificationsOn();
                    }
                }
            }
            // if notifications are set to on and card is clicked
            else {
                setNotificationsOff();
            }

            // updating shared preferences
            updateNotificationPreference();

        });

        binding.cardChangename.setOnClickListener(view ->
            ((MainActivity) requireActivity()).changeFragment(R.id.navigate_to_changeNameFragment)
        );

        return root;
    }

    private void animateCards() {
        // fade in animation
        // Tutlane, https://www.tutlane.com/tutorial/android/android-fade-in-out-animations-with-examples
        Animation animFadeIn = AnimationUtils.loadAnimation(requireContext(),
                com.example.vihang.R.anim.fade_in);
        binding.gridSettings.startAnimation(animFadeIn);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private boolean isNotificationPermissionGranted() {
        return requireActivity().checkSelfPermission
                (android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
    }

    private void updateNotificationPreference() {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("pushNotifications", isEnabled);
        editor.apply();

    }

    private void launchSettings() {
        // Redirecting the user to the app settings page if permission is not granted
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(android.net.Uri.parse("package:" + requireActivity().getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    private void setNotificationsOff() {

        // setting the flag
        isEnabled = false;

        // setting the card background color and icon
        binding.cardPushnotifications.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.ivNotifications.setImageResource(R.drawable.outline_circle_notifications_24);

    }

    private void setNotificationsOn() {

        // setting the flag
        isEnabled = true;

        // setting the card background color and icon
        binding.cardPushnotifications.setCardBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_secondary90));
        binding.ivNotifications.setImageResource(R.drawable.round_circle_notifications_24);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}