package com.example.new_res;

import android.app.Activity;
import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import com.google.firebase.database.*;
import java.util.*;

public class MemberApprovalAdapter extends ArrayAdapter<Member> {

    private TextView mail, user, type_u;
    private String key;
    private Button delete, approve;
    private DatabaseReference reff;
    private Activity context;
    private List<Member> MemberList;
    private List<Member> member_list;

    public MemberApprovalAdapter(Activity context, List<Member> MemberList) {
        super(context, R.layout.every_member_in_aproval_list, MemberList);
        this.context = context;
        this.MemberList = MemberList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.every_member_in_aproval_list, null, true);

        Member member = MemberList.get(position);

        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        member_list = new ArrayList<>();

        mail = view.findViewById(R.id.everymember_emailText);
        user = view.findViewById(R.id.everymember_usernameText);
        type_u = view.findViewById(R.id.everymember_typeText);
        approve = view.findViewById(R.id.everymember_approveButton);
        delete = view.findViewById(R.id.everymember_deleteButton);

        mail.setText(member.getEmail());
        user.setText(member.getUsername());
        type_u.setText(member.getType());

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Member member = snapshot.getValue(Member.class);

                        if (member.getEmail().equals(mail.getText().toString().trim())) {
                            key = snapshot.getKey();
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff.child(key).child("approved").setValue(true);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff.child(key).removeValue();
            }
        });

        return view;
    }
}
