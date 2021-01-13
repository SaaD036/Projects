package com.example.smartclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Handler handler;
    private  Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComp();

        imageView.animate().alpha(4000).setDuration(0);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }

    private void initComp() {
        imageView = (ImageView) findViewById(R.id.mainActImageView);

        handler = new Handler();
    }


}
