package com.example.firebasestudent.view.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ActivityAddNotificationBinding;
import com.example.firebasestudent.model.Notice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddNotification extends AppCompatActivity {


    ActivityAddNotificationBinding binding;
    private ArrayList<Notice> notices;
    private String currentDateTimeString ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);

        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_notification);

        String posotion = this.getIntent().getStringExtra("Position");


        binding.imagebtnClassBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference NotiRef = firebaseDatabase.getReference().child("school").child("classes").child(posotion).child("notice");
        NotiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Notification_LongDinh",dataSnapshot.toString());
                notices = new ArrayList<>();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Notice notice = snapshot.getValue(Notice.class);
                    notices.add(notice);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title =  binding.editTextTitle.getText().toString();
                String content = binding.editTextContent.getText().toString();
                currentDateTimeString = DateFormat.getDateInstance().format(new Date());
                notices.add(new Notice(1,content,currentDateTimeString));

                NotiRef.setValue(notices);
                finish();
            }
        });


    }
}
