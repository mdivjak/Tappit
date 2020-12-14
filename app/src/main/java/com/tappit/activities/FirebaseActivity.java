package com.tappit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tappit.databinding.ActivityFirebaseBinding;

public class FirebaseActivity extends AppCompatActivity {
    ActivityFirebaseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirebaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}