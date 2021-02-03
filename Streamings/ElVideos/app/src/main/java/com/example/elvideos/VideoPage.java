package com.example.elvideos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class VideoPage extends AppCompatActivity {
    private LinearLayout free, premium;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_page);

        free = findViewById(R.id.videoPage_freeLayout);
        premium = findViewById(R.id.videoPage_premiumLayout);

        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoPage.this, ShowVideos.class);
                intent.putExtra("type", "free");
                startActivity(intent);
            }
        });
        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoPage.this, ShowVideos.class);
                intent.putExtra("type", "paid");
                startActivity(intent);
            }
        });
    }
}
