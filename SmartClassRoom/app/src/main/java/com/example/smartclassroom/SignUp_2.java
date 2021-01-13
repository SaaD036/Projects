package com.example.smartclassroom;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SignUp_2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String mail, user, pass, status, room;
    private int code_number;

    private Button back, submit, next;
    private TextView fuckk;
    private Spinner spinner;
    private EditText verification;

    private DatabaseReference reff;
    private StorageReference sReff;
    private List<RequestedMember> memberList;
    RequestedMember member;

    private static final int img_req = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2);

        initComp();

        mail = getIntent().getStringExtra("emailFrom");
        user = getIntent(). getStringExtra("usernameFrom");
        pass = getIntent().getStringExtra("passwordFrom");

        String[] user_type = {"Teacher", "Stuff", "CR", "Student"};
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, user_type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        verification.addTextChangedListener(verificationWatcher);

        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (status.equals("CR") || status.equals("Stuff")) {
                    openDialog();

                    member.setEmail(mail);
                    member.setUser(user);
                    member.setPass(pass);
                    member.setStatus(status);
                    member.setRoom(room);
                }
                else{

                }

                Random random = new Random();
                code_number = random.ints(100000, 999999).findFirst().getAsInt();
                sendMail(code_number);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp_2.this, SignUp.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = Integer.parseInt(verification.getText().toString().trim());

                if(p == code_number){
                    reff.push().setValue(member);

                    Intent intent = new Intent(SignUp_2.this, LogIn.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SignUp_2.this, "Wrong verification code", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initComp() {
        back = (Button)findViewById(R.id.signupBackButton);
        submit = (Button)findViewById(R.id.signupSubmitButton);
        next = (Button)findViewById(R.id.signup2_nextButton);

        spinner = (Spinner)findViewById(R.id.signup2_stausSpinner);

        fuckk = (TextView)findViewById(R.id.signup2_statusView);

        verification = (EditText)findViewById(R.id.signup2_verificationText);

        reff = FirebaseDatabase.getInstance().getReference("RequestedMember");
        sReff = FirebaseStorage.getInstance().getReference("Member");

        memberList = new ArrayList<>();
        member = new RequestedMember();
    }

    private void openDialog(){
        Enter_Room_Controller enter_room_controller = new Enter_Room_Controller();
        enter_room_controller.show(getSupportFragmentManager(), "SaaD");
    }

    private void sendMail(int code){
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, "Verification Code", "User verification code is : " + code);
        javaMailAPI.execute();
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        status = parent.getItemAtPosition(position).toString();
        fuckk.setText(status);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    private TextWatcher verificationWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int verificatoin_code = Integer.parseInt(verification.getText().toString().trim());

            if (code_number == verificatoin_code){
                submit.setEnabled(true);
                submit.setBackgroundResource(R.drawable.loginbutton);
            }
            else {
                submit.setEnabled(false);
                submit.setBackgroundResource(R.drawable.button_disabled);
            }
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };
}
