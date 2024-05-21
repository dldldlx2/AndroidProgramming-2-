package com.example.project_5;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        TextView tvSongTitle = findViewById(R.id.music_title);
        TextView tvSongDuration = findViewById(R.id.time);
        ImageView ivAlbumArt = findViewById(R.id.music_click_icon);
        ImageButton backButton = findViewById(R.id.back_button);
        SeekBar seekBar = findViewById(R.id.progress_bar);

        String title = getIntent().getStringExtra("title");
        String duration = getIntent().getStringExtra("duration");

        tvSongTitle.setText(title);
        tvSongDuration.setText(duration);

        seekBar.setProgress(0);
        seekBar.setMax(100);

        backButton.setOnClickListener(v -> finish());
    }
}