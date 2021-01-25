package com.example.smartclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {
    private EditText email, username, password, con_password;
    private Button login, next;
    private DatabaseReference reff;
    private Long key;
    private String mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initComp();


        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    key = dataSnapshot.getChildrenCount();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Member member = snapshot.getValue(Member.class);
                        memberList.add(member);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){}
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                String user = username.getText().toString().trim();
                String pass = password.getText().toString();
                String con_pass = con_password.getText().toString();


                if(mail.equals("") || user.equals("") || pass.equals("") || con_pass.equals("")){
                    Toast.makeText(SignUp.this, "All info must be provided", Toast.LENGTH_LONG).show();
                }

                if(!mail.matches(mailPattern)){
                    Toast.makeText(SignUp.this, "Invalid email", Toast.LENGTH_LONG).show();
                }
                if(!pass.equals(con_pass)) {
                    Toast.makeText(SignUp.this, "Confirm Password", Toast.LENGTH_LONG).show();
                    con_password.setError("Password not match");
                }

                else{
                    int flag=0;

                    for (int i=0; i<memberList.size(); i++){
                        if(memberList.get(i).getEmail().equals(mail)){
                            flag = 1;
                            break;
                        }
                    }

                    if(flag == 0){
                        Intent intent = new Intent(SignUp.this, SignUp_2.class);

                        intent.putExtra("emailFrom", user);
                        intent.putExtra("usernameFrom", user);
                        intent.putExtra("passwordFrom", pass);

                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(SignUp.this, "Account associated with this email already exists", Toast.LENGTH_LONG).show();
                    }
                }

                email.setText("");
                username.setText("");
                password.setText("");
                con_password.setText("");
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComp() {
        email = (EditText) findViewById(R.id.signupMailText);
        username = (EditText) findViewById(R.id.signupUserText);
        password = (EditText) findViewById(R.id.signupPassText);
        con_password = (EditText) findViewById(R.id.signupConText);

        login = (Button) findViewById(R.id.signupLogInButton);
        next = (Button) findViewById(R.id.signupNextButton);

        memberList = new ArrayList<>();

        reff = FirebaseDatabase.getInstance().getReference().child("Member");
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
