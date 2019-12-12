package com.example.firebasestudent.view.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ActivityForgotPasswordBinding;

public class ForgotPassword extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);


        Intent intent = this.getIntent();
        String userName = intent.getStringExtra("UserName");

        binding.editTextUserName.setText(userName);

        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
