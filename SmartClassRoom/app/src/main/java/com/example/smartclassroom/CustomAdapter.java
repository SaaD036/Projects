package com.example.smartclassroom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Member>{
    private Activity context;
    private List<Member> memberList;


    public CustomAdapter(Activity context, List<Member> memberList) {
        super(context, R.layout.resquested_list_shower, memberList);

        this.context = context;
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.resquested_list_shower, null, true);

        Member member = memberList.get(position);

        final TextView mail = view.findViewById(R.id.requestMailText);
        TextView user = view.findViewById(R.id.requestUserText);
        TextView phone = view.findViewById(R.id.requestPhoneText);

        Button approve = view.findViewById(R.id.requestedApproveButton);
        Button delete = view.findViewById(R.id.requestedDeleteButton);;

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Approve for "+mail.getText(), Toast.LENGTH_LONG);
                mail.setText("Hallo");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Delete for "+mail.getText(), Toast.LENGTH_LONG);
            }
        });

        mail.setText(member.getEmail());
        user.setText(member.getUser());
        phone.setText(member.getStatus());

        return view;
    }
}
