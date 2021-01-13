package com.example.smartclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestedMemberAproval extends AppCompatActivity {
    private ListView requested;
    private DatabaseReference reff;
    private List<RequestedMember> memberList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_member_aproval);

        initComp();
        customAdapter = new CustomAdapter(RequestedMemberAproval.this, memberList);
    }

    private void initComp(){
        requested = (ListView) findViewById(R.id.listView);
        reff = FirebaseDatabase.getInstance().getReference("RequestedMember");
        memberList = new ArrayList<>();
    }

   @Override
    protected void onStart() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                memberList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    RequestedMember member = snapshot.getValue(RequestedMember.class);
                    memberList.add(member);
                }
               requested.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        super.onStart();
    }
}
