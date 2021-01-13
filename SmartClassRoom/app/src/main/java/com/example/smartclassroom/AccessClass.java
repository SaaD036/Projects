package com.example.smartclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccessClass extends AppCompatActivity {
    private String user, stat, pass, mail, room;
    private TextView username, status, class101_1, class101_2, class102_1, class102_2, class103_1, class103_2, class104_1, class104_2, class105_1, class105_2, time;
    private Button logout;
    private ImageButton menu;
    private int eq_101=0;
    private DatabaseReference reff;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_class);

        initComp();

        mail = getIntent().getStringExtra("mail");
        user = getIntent().getStringExtra("user");
        stat = getIntent().getStringExtra("stat");
        pass = getIntent().getStringExtra("pass");
        room = getIntent().getStringExtra("room");
        date = Calendar.getInstance().getTime();;

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String aa = snapshot.getValue(String.class);

                    if(aa.equals("On")){
                        eq_101 = eq_101 + 1;
                    }
                }
                class101_2.setText(eq_101+"\nequipment\nswitched on");
                eq_101 = 0;

                if(eq_101!=0){
                    //class101_1.setTextColor('#ff0000');
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        //Toast.makeText(ViewClass.this, username+""+stat, Toast.LENGTH_LONG).show();

        String p = date.toString();
        username.setText(user);
        status.setText(stat);
        time.setText(p);

        class101_2.setText(eq_101+"\nequipment\nswitched on");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccessClass.this, MainActivity.class);
                startActivity(intent);

                finish();

                Toast.makeText(AccessClass.this, "Logging Out", Toast.LENGTH_LONG).show();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccessClass.this, Menu.class);

                intent.putExtra("user", user);
                intent.putExtra("stat", stat);
                intent.putExtra("mail", mail);
                intent.putExtra("pass", pass);
                intent.putExtra("room", room);

                startActivity(intent);
            }
        });

        class101_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stat == "Student"){
                    Intent intent = new Intent(AccessClass.this, View_Room_101.class);

                    intent.putExtra("user", user);
                    intent.putExtra("stat", stat);
                    intent.putExtra("mail", mail);
                    intent.putExtra("pass", pass);
                    intent.putExtra("room", room);

                    startActivity(intent);
                }

                else{
                    Intent intent = new Intent(AccessClass.this, Access_Class_101.class);

                    intent.putExtra("user", user);
                    intent.putExtra("stat", stat);
                    intent.putExtra("mail", mail);
                    intent.putExtra("pass", pass);
                    intent.putExtra("room", room);

                    startActivity(intent);
                }
            }
        });
        class101_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stat == "Student"){
                    Intent intent = new Intent(AccessClass.this, View_Room_101.class);

                    intent.putExtra("user", user);
                    intent.putExtra("stat", stat);
                    intent.putExtra("mail", mail);
                    intent.putExtra("pass", pass);
                    intent.putExtra("room", room);

                    startActivity(intent);
                }

                else{
                    Intent intent = new Intent(AccessClass.this, Access_Class_101.class);

                    intent.putExtra("user", user);
                    intent.putExtra("stat", stat);
                    intent.putExtra("mail", mail);
                    intent.putExtra("pass", pass);
                    intent.putExtra("room", room);

                    startActivity(intent);
                }
            }
        });
    }

    private void initComp(){
        username = (TextView) findViewById(R.id.accessClassUserView);
        status = (TextView) findViewById(R.id.accessClassStatusView);

        class101_1 = (TextView) findViewById(R.id.aceesclassroom101TextView_1);
        class101_2 = (TextView) findViewById(R.id.aceesclassroom101TextView_2);
        class102_1 = (TextView) findViewById(R.id.aceesclassroom101TextView_3);
        class102_2 = (TextView) findViewById(R.id.aceesclassroom101TextView_4);
        class103_1 = (TextView) findViewById(R.id.aceesclassroom101TextView_5);
        class103_2 = (TextView) findViewById(R.id.aceesclassroom101TextView_6);
        class104_1 = (TextView) findViewById(R.id.aceesclassroom101TextView_7);
        class104_2 = (TextView) findViewById(R.id.aceesclassroom101TextView_8);
        class105_1 = (TextView) findViewById(R.id.aceesclassroom101TextView_9);
        class105_2 = (TextView) findViewById(R.id.aceesclassroom101TextView_10);
        time = (TextView) findViewById(R.id.accessClassTimeView);

        logout = (Button) findViewById(R.id.accessClassLogout);
        menu = (ImageButton) findViewById(R.id.accessclassMenuButton);

        reff = FirebaseDatabase.getInstance().getReference().child("class101");

    }
}
