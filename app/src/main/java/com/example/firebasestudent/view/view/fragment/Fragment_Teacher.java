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
import com.example.firebasestudent.databinding.FragmentTeacherBinding;
import com.example.firebasestudent.model.Class;
import com.example.firebasestudent.model.Student;
import com.example.firebasestudent.view.view.activity.AddStudent;
import com.example.firebasestudent.view.view.activity.MainActivity;
import com.example.firebasestudent.view.view.adapter.StudentAdapter;
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

public class Fragment_Teacher extends androidx.fragment.app.Fragment {

    private FragmentTeacherBinding binding;
    private ArrayList<Student> students;
    private StudentAdapter adapter;
    private Class aClass;

    protected static Fragment_Teacher instance;

    public static Fragment_Teacher getInstance(){
        if (instance == null){
            instance = new Fragment_Teacher();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_teacher,container,false);

        final String jsonClass = getArguments().getString("Class");
        final String position = getArguments().getString("Position");

//        students = aClass.getStudents();

        Gson gson = new Gson();
        Type type = new TypeToken<Class>(){}.getType();
        aClass = gson.fromJson(jsonClass,type);
//        Log.d("Class", String.valueOf(aClass.students.size()));
//        students = aClass.students;
//        Log.d("Students", String.valueOf(students.size()));
        final DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("school").child("classes").child(position).child("students");
        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Students",dataSnapshot.toString());
                students = new ArrayList<>();
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Student student = data.getValue(Student.class);
                    Log.d("Size",student.getName());
                    students.add(student);
                }
                adapter = new StudentAdapter(getContext(),students);

                binding.recyclerViewStudent.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
                binding.recyclerViewStudent.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        binding.textViewTeacherName.setText(aClass.getTeacher());
        binding.textViewClassName.setText(aClass.getClassName());


        final Intent intent = new Intent(getActivity(), AddStudent.class);

        binding.buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Class",jsonClass);
                intent.putExtra("Position",position);
                startActivity(intent);
            }
        });

        final Intent intentOut = new Intent(getContext(), MainActivity.class);
        binding.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentOut);
            }
        });



        View view = binding.getRoot();
        return view;
    }
}
