package com.example.new_res;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddRestaurant extends AppCompatActivity {

    private EditText rest_name, rest_add;
    private TextView user;
    private Button add, logout, gotouser;
    private String username, type, restaurant_name, address;
    private DatabaseReference reff;
    private List<Restaurants> restaurantsList;
    private Long key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        Intent intent = getIntent();
        username = intent.getStringExtra("passed_username");
        type = intent.getStringExtra("passed_type");

        initComp();
        user.setText(username);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Restaurants current = snapshot.getValue(Restaurants.class);
                        restaurantsList.add(current);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurant_name = rest_name.getText().toString().trim();
                address = rest_add.getText().toString().trim();
                int n=restaurantsList.size();
                boolean flag = true;

                for (int i=0; i<n; i++){
                    Restaurants current = restaurantsList.get(i);
                    String tmp_inDB = current.getName().toUpperCase(), tmp_input=restaurant_name.toUpperCase();

                    if(tmp_inDB.equals(tmp_input)){
                        flag=false;
                        Toast.makeText(AddRestaurant.this, "Restaurant already exist with this name", Toast.LENGTH_LONG).show();
                        break;
                    }

                }

                if(flag) {
                    Restaurants restaurants = new Restaurants();

                    restaurants.setAddress(address);
                    restaurants.setName(restaurant_name);
                    restaurants.setOwner(username);
                    restaurants.setUser(0);
                    restaurants.setTotal_rating(0);
                    restaurants.setId(n + 1);
                    restaurants.setItem(0);

                    reff.push().setValue(restaurants);
                }
            }
        });

    }

    void initComp(){
        rest_add = (EditText) findViewById(R.id.addRestaurant_addressText);
        rest_name = (EditText) findViewById(R.id.addRestaurant_restaurantText);

        user = (TextView) findViewById(R.id.addRestaurent_usernameText);

        add = (Button) findViewById(R.id.addRestaurent_addButton);
        logout = (Button) findViewById(R.id.addRestaurant_logoutButton);
        gotouser = (Button) findViewById(R.id.addRestaurant_gotouserButton);

        reff = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        restaurantsList = new ArrayList<>();
    }
}
