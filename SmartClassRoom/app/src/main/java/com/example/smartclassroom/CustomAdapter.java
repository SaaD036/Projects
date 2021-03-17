package com.example.smartclassroom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<RequestedMember> {
    private Activity context;
    private List<RequestedMember> requestedMemberList;
    private String key, mail;
    private DatabaseReference reff, refff;

    public CustomAdapter(Activity context, List<RequestedMember> requestedMemberList) {
        super(context, R.layout.resquested_list_shower, requestedMemberList);

        this.context = context;
        this.requestedMemberList = requestedMemberList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.resquested_list_shower, null, true);
        final RequestedMember requestedMember = requestedMemberList.get(position);

        final TextView email = view.findViewById(R.id.requestMailText);
        TextView username = view.findViewById(R.id.requestUserText);
        TextView status = view.findViewById(R.id.requestPhoneText);

        Button delete = view.findViewById(R.id.requestedDeleteButton);
        Button aproove = view.findViewById(R.id.requestedApproveButton);



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = requestedMember.getEmail();
                reff = FirebaseDatabase.getInstance().getReference("RequestedMember");

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            RequestedMember member = snapshot.getValue(RequestedMember.class);
                            if(member.getEmail().equals(mail)){
                                reff.child(snapshot.getKey()).removeValue();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        aproove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference refff = FirebaseDatabase.getInstance().getReference("Member");
                Member member = new Member();

                member.setEmail(requestedMember.getEmail());
                member.setUser(requestedMember.getUser());
                member.setPass(requestedMember.getPass());
                member.setStatus(requestedMember.getStatus());
                member.setRoom(requestedMember.getRoom());

                refff.push().setValue(member);


                mail = requestedMember.getEmail();
                reff = FirebaseDatabase.getInstance().getReference("RequestedMember");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            RequestedMember member = snapshot.getValue(RequestedMember.class);
                            if(member.getEmail().equals(mail)){
                                reff.child(snapshot.getKey()).removeValue();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });

        email.setText(requestedMember.getEmail());
        status.setText(requestedMember.getStatus());
        username.setText(requestedMember.getUser());

        return view;
    }
}
