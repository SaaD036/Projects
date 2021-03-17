package com.example.smartclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Access_Class_101 extends AppCompatActivity {
    private TextView username, status;
    private Button accessClass1, accessClass2, accessClass3, accessClass4, logout;
    private DatabaseReference reff1, reff2, reff3, reff4;
    private String eq11, eq22, eq33, eq44, eq55, mail, user, stat, pass, room;
    private ImageButton menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access__class_101);

        initComp();

        mail = getIntent().getStringExtra("mail");
        user = getIntent().getStringExtra("user");
        stat = getIntent().getStringExtra("stat");
        pass = getIntent().getStringExtra("pass");
        room = getIntent().getStringExtra("room");

        username.setText(user);
        status.setText(stat);

        ShowingContitionToUI();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Access_Class_101.this, MainActivity.class);
                startActivity(intent);

                finish();

                Toast.makeText(Access_Class_101.this, "Logging Out", Toast.LENGTH_LONG).show();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Access_Class_101.this, Menu.class);

                intent.putExtra("user", user);
                intent.putExtra("stat", stat);
                intent.putExtra("mail", mail);
                intent.putExtra("pass", pass);
                intent.putExtra("room", room);

                startActivity(intent);
            }
        });



        accessClass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = accessClass1.getText().toString();

                if(p.equals("On")){
                    accessClass1.setText("Off");
                    reff1.setValue("Off");
                }
                else{
                    accessClass1.setText("On");
                    reff1.setValue("On");
                }
            }
        });
        accessClass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = accessClass2.getText().toString();

                if(p.equals("On")){
                    accessClass2.setText("Off");
                    reff2.setValue("Off");
                }
                else{
                    accessClass2.setText("On");
                    reff2.setValue("On");
                }
            }
        });
        accessClass3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = accessClass3.getText().toString();

                if(p.equals("On")){
                    accessClass3.setText("Off");
                    reff3.setValue("Off");
                }
                else{
                    accessClass3.setText("On");
                    reff3.setValue("On");
                }
            }
        });
        accessClass4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = accessClass4.getText().toString();

                if(p.equals("On")){
                    accessClass4.setText("Off");
                    reff4.setValue("Off");
                }
                else{
                    accessClass4.setText("On");
                    reff4.setValue("On");
                }
            }
        });

    }

    private void initComp(){
        username = (TextView) findViewById(R.id.accessClass101userView);
        status = (TextView) findViewById(R.id.accessClass101StatusView);

        accessClass1 = (Button) findViewById(R.id.accessClassButton1);
        accessClass2 = (Button) findViewById(R.id.accessClassButton2);
        accessClass3 = (Button) findViewById(R.id.accessClassButton3);
        accessClass4 = (Button) findViewById(R.id.accessClassButton4);

        logout = (Button) findViewById(R.id.logoutaccess101);

        menu = (ImageButton) findViewById(R.id.accessclass101MenuButton);

        reff1 = FirebaseDatabase.getInstance().getReference().child("class101").child("eq1");
        reff2 = FirebaseDatabase.getInstance().getReference().child("class101").child("eq2");
        reff3 = FirebaseDatabase.getInstance().getReference().child("class101").child("eq3");
        reff4 = FirebaseDatabase.getInstance().getReference().child("class101").child("eq4");
    }

    private void ShowingContitionToUI(){
        reff1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eq11 = dataSnapshot.getValue().toString();
                accessClass1.setText(eq11);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){}
        });
        reff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eq22 = dataSnapshot.getValue().toString();
                accessClass2.setText(eq22);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){}
        });
        reff3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eq33 = dataSnapshot.getValue().toString();
                accessClass3.setText(eq33);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){}
        });
        reff4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eq44 = dataSnapshot.getValue().toString();
                accessClass4.setText(eq44);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){}
        });
    }
}
