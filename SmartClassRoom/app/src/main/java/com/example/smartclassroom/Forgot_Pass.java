package com.example.smartclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.Charset;
import java.util.*;

public class Forgot_Pass extends AppCompatActivity {

    private String email, password;
    int code_number;

    private Button submit, go;
    private EditText mail, verification;
    private TextView confirm;
    private LinearLayout linearLayout;

    private  DatabaseReference reff;
    private List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__pass);

        initComp();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Member current = snapshot.getValue(Member.class);
                        memberList.add(current);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        mail.addTextChangedListener(mailWatcher);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = false;
                email = mail.getText().toString().trim();
                int n = memberList.size();

                for (int i=0; i<n; i++){
                    Member member = memberList.get(i);

                    if(member.getEmail().equals(email)){
                        password = member.getPass();
                        flag = true;
                        break;
                    }
                }

                if (flag){
                    Random random = new Random();
                    code_number = random.nextInt()%9 + 1;
                    code_number = code_number * 101374 + random.nextInt()%87633;

                    sendMail(code_number);

                    confirm.setText("A verification code has been sent to your email.");
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(Forgot_Pass.this, "No account with this email", Toast.LENGTH_LONG).show();
                }
            }
        });

        verification.addTextChangedListener(verificationWatcher);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Forgot_Pass.this, "Your password is - "+password, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initComp(){
        submit = (Button)findViewById(R.id.forgotPassSubmitButton);
        go = (Button)findViewById(R.id.forgotpass_goButton);

        mail = (EditText)findViewById(R.id.forgotPassEmailText);
        verification = (EditText)findViewById(R.id.forgotpass_verificationcodeText);

        confirm = (TextView)findViewById(R.id.forgotpass_confirmText);

        linearLayout = (LinearLayout)findViewById(R.id.forgotpass_verficationInputLayout);

        reff = FirebaseDatabase.getInstance().getReference().child("Member");

        memberList = new ArrayList<>();
    }

    private void sendMail(int code){
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, email, "Smart Classroom", "Your verification code is : \n        "+ code);
        javaMailAPI.execute();
    };

    private TextWatcher mailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            email = mail.getText().toString().trim();

            if(!email.isEmpty()){
                submit.setBackgroundResource(R.drawable.loginbutton);
                submit.setEnabled(true);
            }
            else{
                submit.setBackgroundResource(R.drawable.button_disabled);
                submit.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };
    private TextWatcher verificationWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int code = Integer.parseInt(verification.getText().toString().trim());

            if(code == code_number){
                go.setEnabled(true);
                go.setBackgroundResource(R.drawable.loginbutton);
            }
            else{
                go.setEnabled(false);
                go.setBackgroundResource(R.drawable.button_disabled);
            }
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };
}
