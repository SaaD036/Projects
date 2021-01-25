package com.example.smartclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class View_Room_101 extends AppCompatActivity {
    private TextView username, status, view1, view2, view3, view4;
    private DatabaseReference reff1, reff2, reff3, reff4, reff5;
    private long key;
    private String eq11, eq22, eq33, eq44, eq55, user, stat;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__room_101);

        initComp();

        user = getIntent().getStringExtra("username");
        stat = getIntent().getStringExtra("status");
        username.setText(user);
        status.setText(stat);

        SetStatusToTextView();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(View_Room_101.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(View_Room_101.this, "Logging out", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initComp(){
        view1 = (TextView) findViewById(R.id.viewClass101View2);
        view2 = (TextView) findViewById(R.id.viewClass101View4);
        view3 = (TextView) findViewById(R.id.viewClass101View6);
        view4 = (TextView) findViewById(R.id.viewClass101View8);

        username = (TextView) findViewById(R.id.userView101);
        status = (TextView) findViewById(R.id.statusView101);

        logout = (Button) findViewById(R.id.logoutButton);

        reff1 = FirebaseDatabase.getInstance().getReference().child("class101").child("eq1");
        reff2 = FirebaseDatabase.getInstance().getReference().child("class101").child("eq2");
        reff3 = FirebaseDatabase.getInstance().getReference().child("class101").child("eq3");
        reff4 = FirebaseDatabase.getInstance().getReference().child("class101").child("eq4");
    }

    private void SetStatusToTextView(){
        reff1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eq11 = dataSnapshot.getValue().toString();
                view1.setText(eq11);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){}
        });
        reff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eq22 = dataSnapshot.getValue().toString();
                view2.setText(eq22);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){}
        });
        reff3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eq33 = dataSnapshot.getValue().toString();
                view3.setText(eq33);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){}
        });
        reff4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eq44 = dataSnapshot.getValue().toString();
                view4.setText(eq44);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){}
        });
    }
}
