package com.example.firebasestudent.view.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.FragmentParentNotificationBinding;
import com.example.firebasestudent.model.Notice;
import com.example.firebasestudent.view.view.adapter.NotificationAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentParentNotification extends Fragment {

    FragmentParentNotificationBinding binding;
    private ArrayList<Notice> notices;
    private NotificationAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_parent_notification,container,false);

        String studentName = getArguments().getString("StudentName");
        String className =  getArguments().getString("ClassName");
        String positionClass =  getArguments().getString("PositionClass");
        String position =  getArguments().getString("Position");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference NotilRef = firebaseDatabase.getReference().child("school").child("classes").child(positionClass).child("notice");
        NotilRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("HocSinh",dataSnapshot.toString());
                notices = new ArrayList<>();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Notice notice = snapshot.getValue(Notice.class);
                    notices.add(notice);
                }
                adapter = new NotificationAdapter(getContext(),notices);
                binding.recyclerViewStudent.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
                binding.recyclerViewStudent.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        binding.textViewStudentName.setText(studentName);
        binding.textViewClassName.setText(className);



        View view = binding.getRoot();
        return view;
    }
}
