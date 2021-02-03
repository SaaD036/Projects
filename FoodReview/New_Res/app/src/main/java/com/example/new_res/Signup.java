package com.example.new_res;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText mail, user, pass, conPass;
    private Button login, signup;
    private Spinner type_u;
    private TextView typeView;
    private String email, username, password, conPassword, type;
    private DatabaseReference reff;
    private List<Member> memberList;
    private Long key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initComp();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_u.setAdapter(adapter);
        type_u.setOnItemSelectedListener(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Signup.this, key+""+memberList.size(), Toast.LENGTH_LONG).show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=true;

                email = mail.getText().toString().trim();
                username = user.getText().toString().trim();
                password = pass.getText().toString();
                conPassword = conPass.getText().toString();

                //checking for empty input
                if (email.equals("") || username.equals("") || password.trim().equals("")){
                    flag = false;
                    Toast.makeText(Signup.this, "You can't leave any field empty", Toast.LENGTH_LONG).show();
                }
                //not matching password and re password
                if (flag){
                    if (!password.equals(conPassword)){
                        flag = false;
                        Toast.makeText(Signup.this, "Password doesn't match", Toast.LENGTH_LONG).show();
                    }
                }

                //checking for  matched account
                if (flag){
                    for (int i=0; i<memberList.size(); i++){
                        Member current = memberList.get(i);

                        if(current.getEmail().equals(email) && !current.isApproved()){
                            flag = false;
                            Toast.makeText(Signup.this, "Account associated this email in request list !!", Toast.LENGTH_LONG).show();
                            break;
                        }
                        else if(current.getEmail().equals(email) && current.isApproved()){
                            flag = false;
                            Toast.makeText(Signup.this, "Account associated this email exists !!!", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                }

                //successfull signup
                if (flag){
                    Member member = new Member();

                    member.setEmail(email);
                    member.setPassword(password);
                    member.setUsername(username);
                    member.setType(type);
                    member.setApproved(false);

                    reff.push().setValue(member);

                    Toast.makeText(Signup.this, "Successfully signed up", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void initComp(){
        mail = (EditText)findViewById(R.id.signup_mail);
        user = (EditText)findViewById(R.id.signup_user);
        pass = (EditText)findViewById(R.id.signup_pass);
        conPass = (EditText)findViewById(R.id.signup_conPass);

        login = (Button) findViewById(R.id.signup_loginButton);
        signup = (Button)findViewById(R.id.signup_signupButton);

        typeView = (TextView)findViewById(R.id.signup_typeView);

        type_u = (Spinner)findViewById(R.id.signup_typeSpinner);

        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        memberList = new ArrayList<>();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = parent.getItemAtPosition(position).toString();
        typeView.setText(type);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
