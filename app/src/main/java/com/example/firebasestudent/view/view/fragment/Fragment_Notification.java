package com.example.firebasestudent.view.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.FragmentNotificationBinding;
import com.example.firebasestudent.model.Class;
import com.example.firebasestudent.model.Notice;
import com.example.firebasestudent.view.view.activity.AddNotification;
import com.example.firebasestudent.view.view.adapter.NotificationAdapter;
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

public class Fragment_Notification extends androidx.fragment.app.Fragment {


    FragmentNotificationBinding binding;
    private NotificationAdapter adapter;
    private ArrayList<Notice> notices;
    private Class aClass;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_notification,container,false);

        String jsonClass = getArguments().getString("Class");
        final String position = getArguments().getString("Position");

        notices = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<Class>(){}.getType();
        aClass = gson.fromJson(jsonClass,type);


        final DatabaseReference NotiRef = FirebaseDatabase.getInstance().getReference().child("school").child("classes").child(position).child("notice");
        NotiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Notification_LongDinh",dataSnapshot.toString());
                notices = new ArrayList<>();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Notice notice = snapshot.getValue(Notice.class);
                    notices.add(notice);
                }
                adapter = new NotificationAdapter(getContext(),notices);

                binding.recyclerViewNotification.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
                binding.recyclerViewNotification.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        binding.textViewClassName.setText(aClass.getClassName());
//        binding.textViewClassName.setText(className);
        binding.textViewTeacherName.setText(aClass.getTeacher());

        final Intent intent = new Intent(getActivity(), AddNotification.class);

        binding.buttonAddNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Position",position);
                startActivity(intent);
            }
        });




        View view = binding.getRoot();
        return view;
    }
}
