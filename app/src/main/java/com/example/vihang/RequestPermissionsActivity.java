package com.example.vihang;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vihang.databinding.ActivityRequestPermissionsBinding;

import java.util.Objects;

public class RequestPermissionsActivity extends AppCompatActivity {

    ActivityRequestPermissionsBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRequestPermissionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        // Request permissions
        binding.btnGrantpermissions.setOnClickListener(v ->
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101)
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // start the main activity after receiving the permission result
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}