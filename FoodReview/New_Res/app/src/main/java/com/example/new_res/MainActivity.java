package com.example.new_res;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView img1, img2, img3, img4;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComp();

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_down);
        img1.setAnimation(animation);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right);
        img2.setAnimation(animation);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left);
        img3.setAnimation(animation);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_up);
        img4.setAnimation(animation);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(6000);

                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);

                    finish();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                super.run();
            }
        };

        thread.start();
    }

    void initComp(){
        img1 = (ImageView)findViewById(R.id.main_1);
        img2 = (ImageView)findViewById(R.id.main_2);
        img3 = (ImageView)findViewById(R.id.main_3);
        img4 = (ImageView)findViewById(R.id.main_4);
    }
}
