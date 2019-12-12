package com.example.firebasestudent.view.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ActivityAddStudentBinding;
import com.example.firebasestudent.model.Class;
import com.example.firebasestudent.model.Notice;
import com.example.firebasestudent.model.Student;
import com.example.firebasestudent.view.view.fragment.Fragment_Teacher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddStudent extends AppCompatActivity {

    ActivityAddStudentBinding binding;
    private Class aClass;
    private ArrayList<Notice> classNotice,studentNotice;
    private String currentDateTimeString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        getSupportActionBar().hide();
//
      String jsonClass = this.getIntent().getStringExtra("Class");
      String position = this.getIntent().getStringExtra("Position");
      currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        Gson gson = new Gson();
        Type type = new TypeToken<Class>(){}.getType();
        aClass = gson.fromJson(jsonClass,type);
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("school").child("classes").child(position);
        classNotice = aClass.getNotice();
        studentNotice = new ArrayList<>();
        studentNotice.add(new Notice(1,"Xin Chào Mừng Bạn Tham Gia Hệ Thống",currentDateTimeString));

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_student);

        binding.buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String studentName = binding.editTextAddNameStudent.getText().toString().trim();
                 String studentUserName = binding.editTextAddUser.getText().toString().trim();
                 String studentPass = binding.editTextAddPassword.getText().toString().trim();


                classNotice.add(new Notice(1,"Đã Thêm Học Sinh : "+studentName,currentDateTimeString));
                Student student = new Student(0,0,studentName,studentNotice,studentUserName,studentPass);
                if (aClass.students == null){
                    aClass.students = new ArrayList<>();
                }
                aClass.students.add(student);
                Log.d("ListSize", String.valueOf(aClass.students.size()) + classNotice.size());

                myRef.child("notice").setValue(classNotice);
                myRef.child("students").setValue(aClass.students);
                binding.editTextAddNameStudent.getText().clear();
                binding.editTextAddPassword.getText().clear();
                binding.editTextAddUser.getText().clear();
            }
        });
//
        binding.imagebtnStudentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        final Intent intentBack = new Intent(this, Fragment_Teacher.class);
        binding.imagebtnStudentComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
