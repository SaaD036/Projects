package com.example.new_res;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    private String email, password, username, type;
    private EditText mail, pass;
    private Button login, signup;
    private DatabaseReference reff;
    private Long key;
    private List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intiComp();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    key = dataSnapshot.getChildrenCount();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Member current = snapshot.getValue(Member.class);
                        memberList.add(current);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag1 = false, flag2=false, flag3=true;
                email = mail.getText().toString().trim();
                password = pass.getText().toString();

                //checking the input with database
                for (int i=0; i<memberList.size(); i++){
                    Member member = memberList.get(i);

                    if (member.getEmail().equals(email)){
                        flag1 = true;
                        flag3 = member.isApproved();

                        if (member.getPassword().equals(password)){
                            flag2 = true;
                            username = member.getUsername();
                            type = member.getType();
                        }
                        break;
                    }
                }

                if (flag1 && flag2 && flag3){

                    if (type.equals("Admin")){
                        Intent intent = new Intent(Login.this, Admin.class);
                        intent.putExtra("passed_username", username);
                        intent.putExtra("passed_type", type);
                        startActivity(intent);
                    }
                    else if(type.equals("Owner")){
                        Intent intent = new Intent(Login.this, Owner.class);
                        intent.putExtra("passed_username", username);
                        intent.putExtra("passed_type", type);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(Login.this, User.class);
                        intent.putExtra("passed_username", username);
                        intent.putExtra("passed_type", type);
                        startActivity(intent);
                    }
                }
                else if(flag1 && flag2 && !flag3){
                    Toast.makeText(Login.this, "Request for membership isn't approved yet.", Toast.LENGTH_LONG).show();
                }
                if(flag1 && !flag2){
                    Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_LONG).show();
                }
                if(!flag1){
                    Toast.makeText(Login.this, "No account with this email", Toast.LENGTH_LONG).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });
    }

    void intiComp(){
        mail = (EditText)findViewById(R.id.login_emailText);
        pass = (EditText)findViewById(R.id.login_passwordText);

        login = (Button)findViewById(R.id.login_loginButton);
        signup = (Button)findViewById(R.id.login_signupButton);

        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        memberList = new ArrayList<>();
    }
}
