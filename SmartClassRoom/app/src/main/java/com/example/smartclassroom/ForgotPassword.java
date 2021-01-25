package com.example.smartclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {
    private Button submitButton;
    private EditText code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initComp();


    }

    private void initComp(){
        submitButton = (Button) findViewById(R.id.forgotPasswordSubmitButton);

        code = (EditText) findViewById(R.id.forgotPasswordCodeText);
    }
}
