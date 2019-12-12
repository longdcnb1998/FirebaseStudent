package com.example.firebasestudent.view.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ActivityMainBinding;
import com.example.firebasestudent.model.Class;
import com.example.firebasestudent.model.Notice;
import com.example.firebasestudent.model.School;
import com.example.firebasestudent.model.Student;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private ActivityMainBinding binding;
    private ArrayList<Class> classes;
    public ArrayList<Student> students;
    private String userName,passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        getSupportActionBar().hide();


        classes = new ArrayList<>();
        students = new ArrayList<>();


        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Intent
        final Intent intentClasses = new Intent(this,ClassesActivity.class);
        final Intent intentForgotPass = new Intent(this,ForgotPassword.class);
        final Intent intentTeacher = new Intent(this,TeacherActivity.class);
        final Intent intentParent = new Intent(this,ParentActivity.class);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference schoolRef = firebaseDatabase.getReference().child("school");
        schoolRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("AAA",dataSnapshot.toString());
                  School school = dataSnapshot.getValue(School.class);
                userName = school.getUsername();
                passWord = school.getPassword();
                classes = school.getClasses();
                Log.d("DMM", String.valueOf(classes.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference userRef = database.getReference().child("school").child("UserName");
//
//        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.d("UserName",dataSnapshot.toString());
//                userName = (String) dataSnapshot.getValue();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        DatabaseReference passRef = FirebaseDatabase.getInstance().getReference().child("school").child("Password");
//        passRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                passWord = dataSnapshot.getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = binding.editTextUserLogin.getText().toString().trim();
                String pass = binding.editTextPassLogin.getText().toString().trim();

                if (user.equals(userName) && pass.equals(passWord)){
//                    intentClasses.putExtra("Classes",)
//                    Gson gson = new Gson();
//                    String jsonClasses = gson.toJson(classes);
//                    Log.d("School",jsonClasses);
                    startActivity(intentClasses);
                }
                for (int i = 0; i <classes.size() ; i++) {
                    Gson gson = new Gson();
                    if (user.equals(classes.get(i).getUsername()) && pass.equals(classes.get(i).getPassword())){
//                        intentTeacher.putExtra("Teacher",)
                        String jsonClass = gson.toJson(classes.get(i));
                        intentTeacher.putExtra("Class",jsonClass);
                        intentTeacher.putExtra("Position",String.valueOf(i));
                        startActivity(intentTeacher);
                    }

//                    Log.d("Size",students.toString());
                    if (classes.get(i).getStudents() != null){
                        students = classes.get(i).getStudents();
                        for (int j = 0; j < students.size() ; j++) {
                            if (user.equals(students.get(j).getUsername()) && pass.equals(students.get(j).getPassword())){
                                intentParent.putExtra("StudentName",students.get(j).getName());
                                intentParent.putExtra("ClassName",classes.get(i).getClassName());
                                intentParent.putExtra("PositionClass",String.valueOf(i));
                                intentParent.putExtra("Position",String.valueOf(j));
                                startActivity(intentParent);
                            }
                        }
                    }
                }
            }
        });


//         //

//        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("school");
//
//        ArrayList<Notice> notice = new ArrayList<>();
//        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
//
//        notice.add(new Notice(1,"Xin Chào Tất Cả Các Bạn",currentDateTimeString));
//
//        Student student = new Student(1,2,"Long Dinh",notice,"DinhLong98","123456");
//
//        ArrayList<Student> students = new ArrayList<>();
//        students.add(student);
//
//        Class mClass = new Class("4A",notice,students,"Pham Hoang Duy","DuyPham","654321");
//        ArrayList<Class> classes = new ArrayList<>();
//        classes.add(mClass);
//
//        myRef.child("classes").setValue(classes);
//
//        myRef.child("username").setValue("Long Dinh");
//        myRef.child("password").setValue("123456");


    }
}
