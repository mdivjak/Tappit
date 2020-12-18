package com.tappit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tappit.R;
import com.tappit.databinding.ActivityFirebaseBinding;

public class FirebaseActivity extends AppCompatActivity {
    ActivityFirebaseBinding binding;
    DatabaseReference myRef;
    FirebaseDatabase database;

    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirebaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("time_clicked");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                binding.timeClicked.setText("Clicked seconds: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {

                binding.timeClicked.setText("Failed to read value.");
            }
        });

        runTimer();

    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
        myRef.setValue(binding.timeView.getText().toString());
    }

    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                timeView.setText(seconds + "");
                if (running) seconds++;
                handler.postDelayed(this, 1000);
            }
        });
    }
}