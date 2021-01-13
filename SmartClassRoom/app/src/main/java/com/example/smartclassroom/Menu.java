package com.example.smartclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Menu extends AppCompatActivity {
    private String mail, user, stat, pass, room;
    private TextView username, status, editProfile, memberRequest, goHome;
    private Button logout;
    private ImageView proPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initComp();

        mail = getIntent().getStringExtra("mail");
        user = getIntent().getStringExtra("user");
        stat = getIntent().getStringExtra("stat");
        pass = getIntent().getStringExtra("pass");
        room = getIntent().getStringExtra("room");


        username.setText(user);
        status.setText(stat);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(Menu.this, "Logging out", Toast.LENGTH_LONG);
            }
        });


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, EditProfile.class);

                intent.putExtra("user", user);
                intent.putExtra("stat", stat);
                intent.putExtra("mail", mail);
                intent.putExtra("pass", pass);
                intent.putExtra("room", room);

                startActivity(intent);
            }
        });
        memberRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stat.equals("Teacher")){
                    Intent intent = new Intent(Menu.this, RequestedMemberAproval.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Menu.this, "You can't access requested Member", Toast.LENGTH_LONG);
                }
            }
        });
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, AccessClass.class);

                intent.putExtra("user", user);
                intent.putExtra("stat", stat);
                intent.putExtra("mail", mail);
                intent.putExtra("pass", pass);
                intent.putExtra("room", room);

                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initComp(){
        username = (TextView) findViewById(R.id.menupageUserTextView);
        status = (TextView) findViewById(R.id.menupageStatusTextView);

        editProfile = (TextView) findViewById(R.id.menupageEditprofileTextView);
        memberRequest = (TextView) findViewById(R.id.menupageRequestTextView);
        goHome = (TextView) findViewById(R.id.menupageGohomeTextView);

        logout = (Button) findViewById(R.id.menupageLogoutButton);

        proPic = (ImageView) findViewById(R.id.menupagePropicImageView);

    }
}
