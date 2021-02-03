package com.example.new_res;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {

    String username, type;

    private Button gotoApprovalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent intent = getIntent();
        username = intent.getStringExtra("passed_username");
        type = intent.getStringExtra("passed_type");

        initComp();

        gotoApprovalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, RequestedMemberAproval.class);
                intent.putExtra("passed_username", username);
                intent.putExtra("passed_type", type);
                startActivity(intent);
            }
        });
    }

    void initComp(){
        gotoApprovalList = (Button)findViewById(R.id.admin_approvalList);
    }
}
