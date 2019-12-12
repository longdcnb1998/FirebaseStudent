package com.example.firebasestudent.view.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ActivityAddClassBinding;
import com.example.firebasestudent.model.Class;
import com.example.firebasestudent.model.Notice;
import com.example.firebasestudent.model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddClassActivity extends AppCompatActivity {


    ActivityAddClassBinding binding;
    private String mTeacheraName,mUserName,mPassword,mClassName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_class);

        Intent intent1 = this.getIntent();
        final String count = intent1.getStringExtra("count");


        final ArrayList<Student> students = new ArrayList<>();
//        students.add(stundent);

        final Intent intent = new Intent(this,ClassesActivity.class);

        binding.imagebtnClassComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference classRef = FirebaseDatabase.getInstance().getReference().child("school");
                mTeacheraName = binding.editTextAddNameTeach.getText().toString().trim();
                mClassName = binding.editTextAddClass.getText().toString().trim();
                mUserName = binding.editTextAddUser.getText().toString().trim();
                if (mUserName.length() >0){
                    mUserName = "GV_"+mUserName;
                }
                mPassword = binding.editTextAddPassword.getText().toString().trim();

                binding.editTextAddClass.getText().clear();
                binding.editTextAddNameTeach.getText().clear();
                binding.editTextAddPassword.getText().clear();
                binding.editTextAddUser.getText().clear();

                if (mTeacheraName.length() ==0 ||mClassName.length() == 0 || mPassword.length() == 0 || mUserName.length() ==0){
                    Toast.makeText(AddClassActivity.this, "Xin Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    final ArrayList<Notice> notice = new ArrayList<>();
                    String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
                    notice.add(new Notice(1,"Xin Chào Tất Cả Các Bạn",currentDateTimeString));

                    Log.d("ClassResult",mClassName+" "+notice+" "+ students +" "+mTeacheraName+" "+mUserName+" "+mPassword);

                    Class aClass = new Class(mClassName,notice, students,mTeacheraName,mUserName,mPassword);

//                HashMap map = new HashMap();
//                map.put("classes",aClass);
//                classRef.updateChildren(map);

                    classRef.child("classes").child(count+"").setValue(aClass);
                    startActivity(intent);
                }
            }
            }
        );

        binding.imagebtnClassBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddClassActivity.super.onBackPressed();
            }
        });
    }
}
