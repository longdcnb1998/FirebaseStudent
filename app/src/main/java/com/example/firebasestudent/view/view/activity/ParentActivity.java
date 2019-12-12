package com.example.firebasestudent.view.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ActivityParentBinding;
import com.example.firebasestudent.model.Notice;
import com.example.firebasestudent.view.view.adapter.NotificationAdapter;
import com.example.firebasestudent.view.view.adapter.ViewPagerAdapter;
import com.example.firebasestudent.view.view.fragment.Fragment_NotificationGeneral;
import com.example.firebasestudent.view.view.fragment.Fragment_NotificationPrivate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ParentActivity extends AppCompatActivity {

    ActivityParentBinding binding ;
    private NotificationAdapter adapter;
    private ArrayList<Notice> notices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_parent);

        String studentName = this.getIntent().getStringExtra("StudentName");
        String className = this.getIntent().getStringExtra("ClassName");
        String positionClass = this.getIntent().getStringExtra("PositionClass");
        String position = this.getIntent().getStringExtra("Position");


//        Log.d("LongDinh",jsonNoticeGen + jsonNoticePri);
//
//
//        binding.textViewClassName.setText(className);
//        binding.textViewStudentName.setText(studentName);
//        Bundle bundle = new Bundle();
//        bundle.putString("General",jsonNoticeGen);
//        general = new Fragment_NotificationGeneral();
//        general.setArguments(bundle);
//
//        Bundle bundle1 = new Bundle();
//        bundle1.putString("Private",jsonNoticePri);
//        Fragment_NotificationPrivate aPrivate = new Fragment_NotificationPrivate();
//        aPrivate.setArguments(bundle1);
//        notices = new ArrayList<>();
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference NotiRef = firebaseDatabase.getReference().child("school").child("classes").child(positionClass).child("students").child(position).child("notice");
//        NotiRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.d("Hello",dataSnapshot.toString());
//                notices = new ArrayList<>();
//                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
//                    Notice notice = snapshot.getValue(Notice.class);
//                    notices.add(notice);
//                }
//                adapter = new NotificationAdapter(getApplicationContext(),notices);
//                binding.recyclerViewNotification.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));
//                binding.recyclerViewNotification.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        binding.imageViewBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}
