package com.example.vihang;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.vihang.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    NavController navController;
    SharedPreferences preferences;

    public static final String PREFS_NAME = "Vihang";
    protected static String displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_guides, R.id.navigation_settings)
                .build();
        // Declaring the host fragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);

        // Check if the host fragment is null
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Hide the action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // fetch the user's name from SharedPreferences
        displayName = preferences.getString("displayName", "");

        // Check if the user is logged in
        checkLogin();

        // Schedule the notifications
        scheduleNotifications();
    }

    public void scheduleNotifications() {

        Log.d("Vihang", "Scheduling notifications...");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Check if the user has granted the notification permission
            if(notificationPermissionGranted() && isPushNotificationEnabled()) {

                // Create an intent to start the ScheduledNotificationReceiver
                Intent intent = new Intent(MainActivity.this, ScheduledNotificationReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast
                        (MainActivity.this, 1, intent, PendingIntent.FLAG_IMMUTABLE);

                // Using the alarm system service to schedule the notification
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                /*
                Notification Testing code:

                long triggerTime = System.currentTimeMillis() + 10000;
                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
                 */

                // Schedule the notification to run every day using system time
                long triggerTime = System.currentTimeMillis() + AlarmManager.INTERVAL_DAY;
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerTime, AlarmManager.INTERVAL_DAY, pendingIntent);
            }
            else {
                Log.d("Vihang", "Notification permission not granted");
            }
        }
    }

    // Check if the user has granted the notification permission
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private boolean notificationPermissionGranted() {
        return checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
    }

    private void checkLogin() {

        // If the display name is empty, redirect to the login activity
        if (displayName.isEmpty()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else {
            // Display a welcome message with the user's name
            Snackbar.make(binding.getRoot(),
                    "Welcome " + displayName + "!", Snackbar.LENGTH_LONG)
                    .setAnchorView(R.id.nav_view).show();
        }
    }

    // NavController,
    // Android Developers Documentation
    // https://developer.android.com/reference/androidx/navigation/NavController#navigate(int)

    // method to change fragments
    public void changeFragment(int id) { navController.navigate(id); }

    // method to return to the previous fragment
    public void returnToPreviousFragment() { navController.popBackStack(); }

    // Check if the user has enabled push notifications
    private boolean isPushNotificationEnabled() {
        return preferences.getBoolean("pushNotifications", true);
    }
}