package com.example.vihang;

import static com.example.vihang.MainActivity.PREFS_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vihang.databinding.ActivityLoginBinding;

import java.util.Objects;

// A simple Login screen that prompts the user to enter their display name.
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Hide the action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Set focus on the display name text field
        binding.etDisplayname.requestFocus();

        // Name validation on button click
        binding.btnContinue.setOnClickListener(v -> {

            // Validate the display name
            if (Objects.requireNonNull(binding.etDisplayname.getText()).toString().isEmpty()) {
                binding.etDisplayname.setError("Display name is required");
            }
            else {
                // Save the display name in shared preferences
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("displayName", binding.etDisplayname.getText().toString());
                editor.apply();

                // Start the main activity and finish the login activity
                Intent intent = new Intent(this, RequestPermissionsActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

}