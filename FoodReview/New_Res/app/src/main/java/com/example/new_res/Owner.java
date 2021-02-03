package com.example.new_res;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Owner extends AppCompatActivity {

    private TextView addRes, addItem, editProfile, userText;
    private String username, type;
    private Button logout, userMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        Intent intent = getIntent();
        username = intent.getStringExtra("passed_username");
        type = intent.getStringExtra("passed_type");

        initComp();
        userText.setText(username);

        addRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Owner.this, AddRestaurant.class);
                intent.putExtra("passed_username", username);
                intent.putExtra("passed_type", type);
                startActivity(intent);
            }
        });
    }

    void initComp(){
        addRes = (TextView) findViewById(R.id.owner_addRestaurantText);
        addItem = (TextView) findViewById(R.id.owner_addItemText);
        editProfile = (TextView) findViewById(R.id.owner_editProfileText);
        userText = (TextView) findViewById(R.id.owner_usernameText);

        logout = (Button) findViewById(R.id.owner_logoutButton);
        userMode = (Button) findViewById(R.id.owner_gotouserButton);
    }
}
