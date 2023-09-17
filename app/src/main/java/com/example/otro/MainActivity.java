package com.example.otro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.util.Log;

import com.example.otro.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ContadorViewModel contadorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UUID uuid = UUID.randomUUID();

        binding.button.setOnClickListener(view -> {

            Data dataBuilder = new Data.Builder()
                    .putInt("numero", 104)
                    .build();

            WorkRequest workRequest = new OneTimeWorkRequest.Builder(ContadorWorker.class)
                    .setId(uuid)
                    .setInputData(dataBuilder)
                    .build();

            WorkManager.getInstance(binding.getRoot().getContext())
                    .enqueue(workRequest);
        });

        WorkManager.getInstance(binding.getRoot().getContext())
                .getWorkInfoByIdLiveData(uuid)
                .observe(MainActivity.this, workInfo -> {
                    if(workInfo != null){
                        Data progress = workInfo.getProgress();
                        int contador = progress.getInt("contador", 0);
                        Log.d("msg-test", "progress: " + contador);
                        binding.textView1.setText(String.valueOf(contador));
                    }else{
                        Log.d("msg-test", "work info == null ");
                    }
                });



    }

 }
