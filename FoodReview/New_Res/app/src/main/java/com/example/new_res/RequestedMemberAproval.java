package com.example.new_res;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestedMemberAproval extends AppCompatActivity {

    private String username, type;
    private ImageView userImage;
    private TextView user;
    private ListView listView;
    private DatabaseReference reff;
    private List<Member> MemberList;
    private MemberApprovalAdapter memberApprovalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_member_aproval);

        Intent intent = getIntent();
        username = intent.getStringExtra("passed_username");
        type = intent.getStringExtra("passed_type");

        initComp();
        user.setText(username);

    }

    void initComp(){
        listView = (ListView)findViewById(R.id.requestedMemberAproval_listview);

        userImage = (ImageView)findViewById(R.id.requestedMemberAproval_userImage);
        user = (TextView)findViewById(R.id.requestedMemberAproval_usernameText);

        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        MemberList = new ArrayList<>();

        memberApprovalAdapter = new MemberApprovalAdapter(RequestedMemberAproval.this, MemberList);
    }

    @Override
    protected void onStart() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MemberList.clear();

                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Member current = snapshot.getValue(Member.class);

                        if (!current.isApproved())
                            MemberList.add(current);
                    }
                    listView.setAdapter(memberApprovalAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        super.onStart();
    }
}
