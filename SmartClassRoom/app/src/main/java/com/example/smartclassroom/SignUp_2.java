package com.example.smartclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUp_2 extends AppCompatActivity {
    private  String mail, user, pass, status="";
    private Button back, submit;
    private ImageButton uploadImage;
    private RadioGroup radio;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2);

        initComp();

        mail = getIntent().getStringExtra("emailFrom");
        user = getIntent(). getStringExtra("usernameFrom");
        pass = getIntent().getStringExtra("passwordFrom");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getID = radio.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(getID);
                status = radioButton.getText().toString();

                if(status.equals("Staff") || status.equals("CR")){
                    openDialog();
                }

                //Toast.makeText(SignUp_2.this, "" + getID, Toast.LENGTH_LONG);
            }
        });

    }

    private void initComp() {
        back = (Button) findViewById(R.id.signupBackButton);
        submit = (Button)  findViewById(R.id.signupSubmitButton);
        uploadImage = (ImageButton) findViewById(R.id.signupImageButton);

        radio = (RadioGroup)findViewById(R.id.statusGroup);
    }
    private void openDialog(){
        Enter_Room_Controller enter_room_controller = new Enter_Room_Controller();
        enter_room_controller.show(getSupportFragmentManager(), "SaaD");
    }
}
