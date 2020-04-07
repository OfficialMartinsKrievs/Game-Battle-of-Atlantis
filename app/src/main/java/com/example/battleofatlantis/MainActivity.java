package com.example.battleofatlantis;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.title);

        mediaPlayer.start();

        setContentView(R.layout.activity_main);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mediaPlayer.stop();
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
    }
}