package com.example.smartclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccessClass extends AppCompatActivity {
    private String user, stat;
    private TextView username, status;
    private Button accessClass101, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_class);

        initComp();

        user = getIntent(). getStringExtra("username");
        stat = getIntent().getStringExtra("status");

        //Toast.makeText(ViewClass.this, username+""+stat, Toast.LENGTH_LONG).show();

        username.setText(user);
        status.setText(stat);

        accessClass101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccessClass.this, Access_Class_101.class);

                intent.putExtra("username", user);
                intent.putExtra("status", stat);

                startActivity(intent);
            }
        });

        /*logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccessClass.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void initComp(){
        username = (TextView) findViewById(R.id.accessClassUserView);
        status = (TextView) findViewById(R.id.accessClassStatusView);

        accessClass101 = (Button) findViewById(R.id.accessClassButton101);
        logout = (Button) findViewById(R.id.accessClassLogout);

    }
}
