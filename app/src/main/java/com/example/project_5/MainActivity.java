package com.example.project_5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 1;

    private MediaPlayer mediaPlayer;

    private ListView listView;
    private String[] musicTitles = {"musicTitles1", "musicTitles2", "musicTitles3", "musicTitles4", "musicTitles5", "musicTitles6"};
    private String[] musicDurations = {"3:45", "4:12", "2:58", "5:01", "3:33", "4:20"}; // 샘플 데이터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();
        

        listView = findViewById(R.id.music_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list, R.id.music_title, musicTitles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TitleActivity.class);
                intent.putExtra("title", musicTitles[position]);
                intent.putExtra("duration", musicDurations[position]);
                startActivity(intent);
            }
        });

    }

    private void requestReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "외부 저장소 읽기 권한이 허용되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "외부 저장소 읽기 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}