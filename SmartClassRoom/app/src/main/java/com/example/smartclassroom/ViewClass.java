package com.example.smartclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewClass extends AppCompatActivity {
    private String username, stat;
    private TextView user, status;
    private Button class101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class);

        initComp();

        //MainActivity main = new MainActivity();
        username = getIntent().getStringExtra("username");
        stat = getIntent().getStringExtra("status");

        //Toast.makeText(ViewClass.this, username+""+stat, Toast.LENGTH_LONG).show();

        user.setText(username);
        status.setText(stat);

        class101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewClass.this, View_Room_101.class);

                intent.putExtra("username", username);
                intent.putExtra("status", stat);

                startActivity(intent);
            }
        });
    }

    private void initComp() {
        user = (TextView) findViewById(R.id.userView);
        status = (TextView) findViewById(R.id.statusView);

        class101 = (Button) findViewById(R.id.class101Button);
    }
}
