package com.example.smartclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LogIn extends AppCompatActivity {
    private EditText email, password;
    private Button login, signup;
    private TextView forgotPass;
    private DatabaseReference reff;
    private String mail, pass, user, stat, room;
    private Long key;
    private List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        initComp();


        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    key = dataSnapshot.getChildrenCount();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Member member = snapshot.getValue(Member.class);
                        memberList.add(member);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //Log In button working code
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = email.getText().toString();
                pass = password.getText().toString();
                int sizee = memberList.size(), flag = 0;

                for (int i = 0; i < sizee; i++) {
                    if (memberList.get(i).getEmail().equals(mail)) {
                        if (memberList.get(i).getPass().equals(pass)) {
                            user = memberList.get(i).getUser();
                            stat = memberList.get(i).getStatus();
                            room = memberList.get(i).getRoom();

                            flag = 2;
                        }
                        else { flag = 1; }
                        break;
                    }
                }

                if (flag == 0) {
                    Toast.makeText(LogIn.this, "No account", Toast.LENGTH_LONG).show();
                }

                else if (flag == 1) {
                    Toast.makeText(LogIn.this, "Wrong Password", Toast.LENGTH_LONG).show();
                }

                else if (flag == 2) {
                    if (stat.equals("Teacher")) {
                        Intent intent = new Intent(LogIn.this, AccessClass.class);

                        intent.putExtra("user", user);
                        intent.putExtra("stat", stat);
                        intent.putExtra("mail", mail);
                        intent.putExtra("pass", pass);
                        intent.putExtra("room", room);

                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(LogIn.this, ViewClass.class);

                        intent.putExtra("user", user);
                        intent.putExtra("stat", stat);
                        intent.putExtra("mail", mail);
                        intent.putExtra("pass", pass);
                        intent.putExtra("room", room);

                        startActivity(intent);
                    }
                }
            }
        });

        //Sign UP button working code
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Forgot_Pass.class);
                startActivity(intent);
            }
        });
    }

    private void initComp() {
        //Initializing EditText
        email = (EditText)findViewById(R.id.emailText);
        password = (EditText)findViewById(R.id.passwordText);

        forgotPass = (TextView) findViewById(R.id.mainActivityForgotPassword);

        //Initializing Button
        login = (Button)findViewById(R.id.loginButton);
        signup = (Button)findViewById(R.id.signupButton);

        //Initializing Database object
        reff = FirebaseDatabase.getInstance().getReference("Member");

        //Initializing memberList
        memberList = new ArrayList<>();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
