package com.example.wonono;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.midi.MidiDeviceService;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //recuperation de la bouton start
    ImageButton imageButton;
    ProgressBar progressBar;
    Handler handler;
    int progress =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(progressBar.getProgress()<50){
                            progressBar.setProgress(progress);
                            progress++;
                            handler.postDelayed(this ,50);
                            Intent inten1 = new Intent(MainActivity.this , Admin.class);
                            startActivity(inten1);
                            finish();
                        }
                    }
                },50);


            }
        });

    }
}
