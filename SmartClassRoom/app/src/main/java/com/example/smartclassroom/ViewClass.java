package com.example.smartclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewClass extends AppCompatActivity {

    private String email, username, status, password, room;
    private LinearLayout class101;
    private ImageView userStatus;
    private TextView user, class101_eq01, class101_eq02, getClass101_eq03, getClass101_eq04, getClass101_eq05;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class);

        initComp();

        email = getIntent().getStringExtra("mail");
        username = getIntent().getStringExtra("user");
        status = getIntent().getStringExtra("stat");
        password = getIntent().getStringExtra("pass");
        room = getIntent().getStringExtra("room");

        user.setText(username);
        if (status.equals("Teacher")){
            userStatus.setImageResource(R.mipmap.teacher);
        }
        else if(status.equals("Student")) {
            userStatus.setImageResource(R.mipmap.student);
        }
        else{
            userStatus.setImageResource(R.mipmap.stuff);
        }

        class101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equals("CR") || status.equals("Stuff") && room.equals("101")){
                    Intent intent = new Intent(ViewClass.this, Access_Class_101.class);

                    intent.putExtra("user", username);
                    intent.putExtra("stat", status);
                    intent.putExtra("mail", email);
                    intent.putExtra("pass", password);
                    intent.putExtra("room", room);

                    startActivity(intent);
                }

                else{
                    Intent intent = new Intent(ViewClass.this, View_Room_101.class);

                    intent.putExtra("user", username);
                    intent.putExtra("stat", status);
                    intent.putExtra("mail", email);
                    intent.putExtra("pass", password);
                    intent.putExtra("room", room);

                    startActivity(intent);
                }
            }
        });

    }

    private void initComp() {
        userStatus = (ImageView)findViewById(R.id.viewclass_userstatusImageView);
        user = (TextView)findViewById(R.id.viewclass_usernameView);
        class101 = findViewById(R.id.viewClass_class101Layout);
    }
}
